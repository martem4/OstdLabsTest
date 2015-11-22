package com.ostdlabs.repository;

import com.ostdlabs.model.BankAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Yoghurt_92 on 21.11.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/server-config.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class BankAccountRepositoryTest {

    @Autowired
    private BankAccountRepository repository;

    @Test
    public void testFindAll() {
        List<BankAccount> bankAccounts = repository.findAll();
        assertThat(bankAccounts.size(), not(0));
    }

    @Test
    public void testFindOne() {
        BankAccount bankAccount = repository.findOne(1);
        assertNotNull(bankAccount);
    }

    @Test
    public void testSave() {
        BankAccount bankAccount = getBankAccount();

        bankAccount = repository.save(bankAccount);
        assertNotNull(bankAccount);
    }

    @Test
    public void testUpdate() {
        BankAccount bankAccount = repository.findOne(1);
        assertThat(bankAccount.getiBan(), equalTo("1"));

        bankAccount.setiBan("-99");
        bankAccount = repository.save(bankAccount);

        assertThat(bankAccount.getiBan(), equalTo("-99"));
    }

    @Test
    public void testDeleteById() {
        BankAccount bankAccount = repository.findOne(1);

        assertNotNull(bankAccount);
        repository.delete(1);

        bankAccount = repository.findOne(1);
        assertNull(bankAccount);
    }

    @Test
    public void testDelete() {
        BankAccount bankAccount = repository.findOne(1);

        assertNotNull(bankAccount);
        repository.delete(bankAccount);

        bankAccount = repository.findOne(1);
        assertNull(bankAccount);
    }

    private BankAccount getBankAccount() {
        BankAccount account = new BankAccount();
        account.setId(11);
        account.setBik("11");
        account.setBik("000000000");

        return account;
    }
}
