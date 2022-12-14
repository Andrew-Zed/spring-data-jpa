package com.andrew.simplecrudoperation;

import com.andrew.simplecrudoperation.entities.Product;
import com.andrew.simplecrudoperation.repos.ProductRepository;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SimpleCrudOperationApplicationTests {

    @Autowired
    ProductRepository repository;

//    @Autowired
    EntityManager entityManager;

    @Test
    void contextLoads() {
    }

    @Test
    public void testCreate(){
        Product product = new Product();
        product.setId(1);
        product.setName("Iphone");
        product.setDesc("Awesome product");
        product.setPrice(1000d);

        repository.save(product);
    }

    @Test
    public void testRead(){
        Product product = repository.findById(1).get();
        assertNotNull(product);
        assertEquals("Iphone", product.getName());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + product.getDesc());
    }

    @Test
    public void testUpdate(){
        Product product = repository.findById(1).get();
        product.setPrice(1200d);

        repository.save(product);
    }

    @Test
    public void testDelete(){
        if (repository.existsById(1)) {
            repository.deleteById(1);
        }
    }

    @Test
    public void testCount(){
        System.out.println("Total FRecords >>>>>>>>>>> " +repository.count() + " <<<<<<<<<<<>>>>>>>>>");
    }

    @Test
    public void testFindByName() {
        List<Product> products = repository.findByName("IWatch");
        products.forEach(p -> System.out.println(p.getPrice()) );
    }

    @Test
    public void testFindByNameAndDesc() {
        List<Product> products = repository.findByNameAndDesc("TV", "From Samsung");
        products.forEach(p -> System.out.println(p.getPrice()) );
    }

    @Test
    public void testFindByPriceGreaterThan() {
        List<Product> products = repository.findByPriceGreaterThan(1000d);
        products.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindByDesContains() {
        List<Product> products = repository.findByDescContains("Apple");
        products.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindByPriceBetween() {
        List<Product> products = repository.findByPriceBetween(500d, 1500d);
        products.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindByDesLike() {
        List<Product> products = repository.findByDescLike("%LG%");
        products.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindByIdsIn() {
        Pageable pageable = PageRequest.of(0, 2);
        List<Product> products = repository.findByIdIn(Arrays.asList(1, 2, 3, 4, 5), pageable);
        products.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindAllPaging() {
        Pageable pageable = PageRequest.of(2, 2);

        Page<Product> results = repository.findAll(pageable);
        results.forEach(product -> System.out.println(product.getName()));
    }

    @Test
    public void testFindAllSorting() {
        repository.findAll(Sort.by(new Sort.Order(Sort.Direction.ASC, "name")))
                .forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindAllSortingMultiple() {
        repository.findAll(Sort.by(new Sort.Order(Sort.Direction.DESC, "name"),
                        new Sort.Order(Sort.Direction.DESC, "price")))
                .forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindAllPagingAndSorting() {
        Pageable pageable = PageRequest.of(0, 2, Sort.Direction.DESC, "name");
        repository.findAll(pageable).forEach(product -> System.out.println(product.getName()));
    }

    @Test
    @Transactional
    public void testCaching() {
        Session session = entityManager.unwrap(Session.class);
        Optional<Product> product = repository.findById(1);
        repository.findById(1);
//        session.evict(1);
        repository.findById(1);
    }
}
