package com.ostdlabs.service;

import com.ostdlabs.model.BankAccount;
import com.ostdlabs.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yoghurt_92 on 21.11.2015.
 */

@Service
@Repository
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository repository;

    @Override
    public BankAccount findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<BankAccount> findAll() {
        return repository.findAll();
    }

    @Override
    public BankAccount save(BankAccount bankAccount) {
        return repository.save(bankAccount);
    }

    @Override
    public BankAccount update(BankAccount bankAccount) {
        return repository.save(bankAccount);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Override
    public void delete(BankAccount bankAccount) {
        repository.delete(bankAccount);
    }
}
