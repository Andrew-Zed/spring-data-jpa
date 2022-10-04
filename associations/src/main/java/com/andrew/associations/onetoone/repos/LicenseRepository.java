package com.andrew.associations.onetoone.repos;

import com.andrew.associations.onetoone.entities.License;
import org.springframework.data.repository.CrudRepository;

public interface LicenseRepository  extends CrudRepository<License, Long> {
}
