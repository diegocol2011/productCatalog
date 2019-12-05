package com.catalog.model;

import java.sql.Blob;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Id")
	private Integer id;

	@Column(name="Name")
	private String name;
	
	@Column(name="Image")
	private Blob image;
	
	@OneToMany(targetEntity=Product.class,fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="category")
	private List<Product> listProducts;
}