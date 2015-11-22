'use strict'

angular.module('app', ['ngGrid'])

    .factory('accountsService', ['$http',
        function ($http) {
            var service = {}

            service.getAll = function() {
                return $http.get(window.location + 'rest/accounts/list');
            };

            service.delete = function(id) {
                return $http.delete(window.location + 'rest/accounts/delete/' + id);
            }

            service.update = function(account) {
                return $http.post(window.location + 'rest/accounts/update/', account);
            }

            service.save = function(account) {
                return $http.post(window.location + 'rest/accounts/save/', account);
            }

            service.send = function(accounts) {
                return $http.post(window.location + 'rest/accounts/send/', accounts);
            }

            return service;
        }
    ])

    .controller('accountsCtrl', ['$scope', '$http', '$rootScope', 'accountsService',
        function ($scope, $http, $rootScope, accountsService) {

            $scope.accounts = [];
            $scope.selectedRow = [];

            $scope.account = { iBan: null, bik: null };

            accountsService.getAll()
            .success(
                function (data) {
                    $scope.accounts = data;
                }
            );

            $scope.send = function() {
                accountsService.send($scope.accounts)
                .success(
                    function(status) {
                        console.log(status)
                    }
                )
            }

            $scope.$on('update', function() {
                accountsService.getAll()
                .success(
                    function (data) {
                        $scope.accounts = data;
                        $scope.account.iBan = null;
                        $scope.account.bik = null;
                    }
                );
            });

            $scope.setErrorClass = function() {
                $("#IBanForm").addClass("has-error");
                $("#BikForm").addClass("has-error");
            }

            $scope.deleteIfExistErrorClass = function() {
                if ($("#IBanForm").hasClass("has-error")) {
                    $("#IBanForm").removeClass("has-error")
                }

                if ($("#BikForm").hasClass("has-error")) {
                    $("#BikForm").removeClass("has-error")
                }
            }

            $scope.save = function() {
                if ($scope.account.iBan &&  $scope.account.bik) {

                    $scope.deleteIfExistErrorClass();

                    $('#addModal').modal('hide')

                    accountsService.save($scope.account)
                    .success(
                        function () {
                            $rootScope.$broadcast('update');
                        }
                    )
                } else {
                    $scope.setErrorClass();
                }
            };

            $scope.add = function() {
                $("#addModal").modal("show")
            };

            $scope.delete = function() {
                if ($scope.selectedRow.length) {
                    var account = $scope.selectedRow[0]
                    accountsService.delete(account.id).
                    success(
                        function() {
                            accountsService.getAll()
                            .success(
                                function (data) {
                                    $scope.accounts = data;
                                }
                            );
                        }
                    )
                }
            };

            $scope.$on('ngGridEventEndCellEdit', function(evt){
                var account = evt.targetScope.row.entity;
                console.log(account);
                accountsService.update(account)
                .success(
                    function() {
                        accountsService.getAll()
                            .success(
                            function (data) {
                                $scope.accounts = data;
                            }
                        );
                    }
                )

            });

            $scope.cellInputEditableTemplate = '<input ng-class="\'colt\' + col.index" ng-input="COL_FIELD" ng-model="COL_FIELD" ng-blur="updateEntity(row)"/>';

            $scope.gridOptions = {
                data: 'accounts',
                selectedItems: $scope.selectedRow,
                multiSelect: false,
                enableColumnResize : true,
                enableCellSelection: true,
                enableCellEdit: true,
                columnDefs:[
                    { field: 'iBan', displayName: 'IBan', editableCellTemplate: $scope.cellInputEditableTemplate},
                    { field: 'bik', displayName: 'BIK', editableCellTemplate: $scope.cellInputEditableTemplate}
                ]
            };
        }
    ])