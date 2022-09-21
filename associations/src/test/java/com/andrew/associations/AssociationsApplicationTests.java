package com.andrew.associations;

import com.andrew.associations.onetomany.entities.Customer;
import com.andrew.associations.onetomany.entities.PhoneNumber;
import com.andrew.associations.onetomany.repos.CustomerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;

@SpringBootTest
class AssociationsApplicationTests {

    @Autowired
    CustomerRepository repository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testCreateCustomer() {

        Customer customer = new Customer();
        customer.setName("Chris Rock");

        PhoneNumber ph1 = new PhoneNumber();
        ph1.setNumber("08037353331");
        ph1.setType("mobile");

        PhoneNumber ph2 = new PhoneNumber();
        ph2.setNumber("01-2701889");
        ph2.setType("landline");
        ph2.setCustomer(customer);

        customer.addPhoneNumber(ph1);
        customer.addPhoneNumber(ph2);

        repository.save(customer);
    }

    @Test
    @Transactional
    public void testLoadCustomer() {
        Optional<Customer> customer = repository.findById(152L);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  " + customer.get().getName().toUpperCase());

        Set<PhoneNumber> numbers = customer.get().getNumbers();
        numbers.forEach(number -> System.out.println("numbers ============= " + number.getNumber()));
    }

    @Test
    public void testUpdateCustomer() {
        Optional<Customer> customer = repository.findById(152L);
        customer.get().setName("Philip Tim");

        Set<PhoneNumber> numbers = customer.get().getNumbers();
        numbers.forEach(number -> number.setType("cell"));

        repository.save(customer.get());
    }

    @Test
    public void testDelete() {
        repository.deleteById(152L);
    }

}
