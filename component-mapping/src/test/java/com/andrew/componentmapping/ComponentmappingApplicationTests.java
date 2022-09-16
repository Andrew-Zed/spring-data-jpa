package com.andrew.componentmapping;

import com.andrew.componentmapping.entities.Address;
import com.andrew.componentmapping.entities.Employee;
import com.andrew.componentmapping.repos.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ComponentmappingApplicationTests {

    @Autowired
    EmployeeRepository repository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testCreate() {
        Employee employee = new Employee();
        employee.setId(123);
        employee.setName("Andrew");
        Address address = new Address();
        address.setStreetaddress("Broad Street");
        address.setCity("Lagos");
        address.setState("Lagos State");
        address.setZipcode("100223");
        address.setCountry("Nigeria");
        employee.setAddress(address);
        repository.save(employee);
    }

}
