package com.ostdlabs.repository;

import com.ostdlabs.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yoghurt_92 on 21.11.2015.
 */

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

}
