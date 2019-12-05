package com.catalog.repository;

import org.springframework.data.repository.CrudRepository;

import com.catalog.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
