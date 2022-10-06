package com.andrew.transactionmanagement.repos;

import com.andrew.transactionmanagement.entities.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccount, Integer> {

}
