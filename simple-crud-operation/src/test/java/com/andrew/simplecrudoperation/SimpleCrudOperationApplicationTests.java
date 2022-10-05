package com.andrew.simplecrudoperation;

import com.andrew.simplecrudoperation.entities.Product;
import com.andrew.simplecrudoperation.repos.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SimpleCrudOperationApplicationTests {

    @Autowired
    ProductRepository repository;

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
        List<Product> products = repository.findByIdIn(Arrays.asList(3, 4, 5));
        products.forEach(p -> System.out.println(p.getName()));
    }

}
