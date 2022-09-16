package com.andrew.componentmapping.repos;

import com.andrew.componentmapping.entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
