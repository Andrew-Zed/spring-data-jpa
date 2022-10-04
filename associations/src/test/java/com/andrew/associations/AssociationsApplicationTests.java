package com.andrew.associations;

import com.andrew.associations.manytomany.entities.Programmer;
import com.andrew.associations.manytomany.entities.Project;
import com.andrew.associations.manytomany.repos.ProgrammerRepository;
import com.andrew.associations.onetomany.entities.Customer;
import com.andrew.associations.onetomany.entities.PhoneNumber;
import com.andrew.associations.onetomany.repos.CustomerRepository;
import com.andrew.associations.onetoone.entities.License;
import com.andrew.associations.onetoone.entities.Person;
import com.andrew.associations.onetoone.repos.LicenseRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
class AssociationsApplicationTests {

    @Autowired
    CustomerRepository repository;

    @Autowired
    ProgrammerRepository programmerRepository;

    @Autowired
    LicenseRepository licenseRepository;

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

    @Test
    public void testManyToManyCreateProgrammer(){
        Programmer programmer = new Programmer();
        programmer.setName("Jon Jones");
        programmer.setSal(10000);

        HashSet<Project>  projects = new HashSet<>();
        Project project = new Project();
        project.setName("Wakacast Projects");
        projects.add(project);

        programmer.setProjects( projects);

        programmerRepository.save(programmer);
    }

    @Test
    @Transactional
    public void testManyToManyFindProgrammer(){
        Optional<Programmer> programmer = programmerRepository.findById(52);
        System.out.println(programmer);
        System.out.println(programmer.get().getProjects());
    }

    @Test
    public  void testOneToOneCreateLicense(){
        License license = new License();
        license.setType("CAR");
        license.setValidFrom(new Date());
        license.setValidTo(new Date());

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Clinton");
        person.setAge(30);

        license.setPerson(person);

        licenseRepository.save(license);

    }

}
