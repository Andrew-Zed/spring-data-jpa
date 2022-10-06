package com.andrew.transactionmanagement;

import com.andrew.transactionmanagement.services.BankAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionManagementApplicationTests {

    @Autowired
    BankAccountService service;

    @Test
    void contextLoads() {
    }

    @Test
    public void testTransfer() {
        service.transfer(500);
    }

}
