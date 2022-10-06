package com.andrew.transactionmanagement.services;

import com.andrew.transactionmanagement.entities.BankAccount;
import com.andrew.transactionmanagement.repos.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService{

    BankAccountRepository repository;

    public BankAccountServiceImpl(BankAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void transfer(int amount) {
        Optional<BankAccount>  obamasAccount = repository.findById(1);
        obamasAccount.get().setBal(obamasAccount.get().getBal() - amount);
        repository.save(obamasAccount.get());

//        if (true) {
//        throw new  RuntimeException();
//        }

        Optional<BankAccount>  trumpsAccount = repository.findById(2);
        trumpsAccount.get().setBal(trumpsAccount.get().getBal() + amount);
        repository.save(trumpsAccount.get());

    }
}
