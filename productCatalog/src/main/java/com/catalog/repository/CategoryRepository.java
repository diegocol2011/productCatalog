package com.catalog.repository;

import org.springframework.data.repository.CrudRepository;

import com.catalog.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
