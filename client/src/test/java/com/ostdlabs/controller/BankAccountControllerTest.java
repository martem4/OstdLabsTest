package com.ostdlabs.controller;

import com.ostdlabs.model.BankAccount;
import com.ostdlabs.service.BankAccountServiceImpl;
import com.ostdlabs.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.ostdlabs.utils.TestUtils.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Yoghurt_92 on 21.11.2015.
 */

public class BankAccountControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BankAccountServiceImpl service;

    @InjectMocks
    private BankAccountController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testHandleConflict() throws Exception {
        when(service.findAll()).thenThrow(Exception.class);

        mockMvc.perform(
                get("/accounts/list"))
                .andExpect(status()
                        .isExpectationFailed())
                .andExpect(content()
                        .contentType(APPLICATION_JSON_UTF8));
    }

    @Test
    public void testGetAccountList() throws Exception {
        when(service.findAll()).thenReturn(getListAccountMock());

        mockMvc.perform(
                get("/accounts/list"))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(APPLICATION_JSON_UTF8))
                .andReturn();

        verify(service, times(1)).findAll();
    }

    @Test
    public void testGetAccountById() throws Exception {
        when(service.findOne(1)).thenReturn(getAccountMock());

        mockMvc.perform(
                get("/accounts/get/{id}", 1))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(APPLICATION_JSON_UTF8))
                .andReturn();

        verify(service, times(1)).findOne(1);
    }

    @Test
    public void testSaveAccount() throws Exception {
        BankAccount mock = getAccountMock();

        when(service.save(mock)).thenReturn(mock);
        String request = getJsonString(mock);

        mockMvc.perform(
                post("/accounts/save")
                        .content(request)
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(APPLICATION_JSON_UTF8))
                .andReturn();

        verify(service, times(1)).save(mock);
    }

    @Test
    public void testUpdateAccount() throws Exception {
        BankAccount mock = getAccountMock();

        when(service.update(mock)).thenReturn(mock);
        String request = getJsonString(mock);

        mockMvc.perform(
                post("/accounts/update")
                        .content(request)
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType(APPLICATION_JSON_UTF8))
                .andReturn();

        verify(service, times(1)).update(mock);
    }

    @Test
    public void testDeleteAccount() throws Exception {
        BankAccount mock = getAccountMock();
        String request = getJsonString(mock);

        mockMvc.perform(
                delete("/accounts/delete")
                        .content(request)
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status()
                        .isOk());

        verify(service, times(1)).delete(mock);
    }

    @Test
    public void testDeleteAccountById() throws Exception {
        mockMvc.perform(
                delete("/accounts/delete/{id}", 1))
                .andExpect(status()
                        .isOk());

        verify(service, times(1)).delete(1);
    }
}
