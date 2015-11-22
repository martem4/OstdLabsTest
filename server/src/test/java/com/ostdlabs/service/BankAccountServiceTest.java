package com.ostdlabs.service;

import com.ostdlabs.model.BankAccount;
import com.ostdlabs.repository.BankAccountRepository;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Yoghurt_92 on 21.11.2015.
 */

public class BankAccountServiceTest {

    @Mock
    private BankAccount account;

    @Mock
    private List<BankAccount> accounts;

    @InjectMocks
    private BankAccountServiceImpl service;

    @Mock
    private BankAccountRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(account.getId()).thenReturn(999);
        when(account.getiBan()).thenReturn("999");
        when(account.getBik()).thenReturn("999999999");
    }

    @Test
    public void testFindAll() {
        when(accounts.size()).thenReturn(10);
        when(repository.findAll()).thenReturn(accounts);

        List<BankAccount> bankAccounts = service.findAll();
        assertThat(bankAccounts.size(), not(0));
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testFindOne() {
        when(repository.findOne(1)).thenReturn(account);
        BankAccount bankAccount = service.findOne(1);

        assertNotNull(bankAccount);
        verify(repository, times(1)).findOne(1);
    }

    @Test
    public void testSave() {
        when(repository.save(account)).thenReturn(account);

        account = service.save(account);

        assertNotNull(account);
        verify(repository, times(1)).save(account);
    }

    @Test
    @Ignore
    public void testUpdate() {
        service.update(account);
        verify(repository, times(1)).save(account);
    }

    @Test
    public void testDeleteById() {
        service.delete(1);
        verify(repository, times(1)).delete(1);
    }

    @Test
    public void testDelete() {
        service.delete(account);
        verify(repository, times(1)).delete(account);
    }
}
