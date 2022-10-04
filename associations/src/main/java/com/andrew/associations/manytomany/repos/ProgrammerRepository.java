package com.andrew.associations.manytomany.repos;

import com.andrew.associations.manytomany.entities.Programmer;
import org.springframework.data.repository.CrudRepository;

public interface ProgrammerRepository  extends CrudRepository<Programmer, Integer> {

}
