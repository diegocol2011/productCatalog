package com.catalog.model;

import java.sql.Blob;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Product")
public class Product {

	@Id
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
	
	@Column(name="Image1")
	private String image1;
	
	@Column(name="Image2")
	private String image2;
	
	@Column(name="Image3")
	private String image3;
	
	@ManyToOne(targetEntity=Category.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Category category;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
