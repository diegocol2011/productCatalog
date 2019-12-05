package com.catalog.model;

import java.sql.Blob;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Product")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Id")
	private Integer id;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Weight")
	private String weight;
	
	@Column(name="Price")
	private String price;
	
	@Column(name="Image")
	private Blob image;
	
	@ManyToOne(targetEntity=Category.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Category category;
}
