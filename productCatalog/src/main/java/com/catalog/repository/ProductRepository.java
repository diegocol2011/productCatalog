package com.catalog.repository;

import org.springframework.data.repository.CrudRepository;

import com.catalog.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

}
