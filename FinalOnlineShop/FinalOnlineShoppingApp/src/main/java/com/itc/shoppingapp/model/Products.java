package com.itc.shoppingapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//To make this class has JPA entity and map this class as table in database
@Entity
//To provide Table Details + add unique key for that table
//In here table name is the class name, if we don't give it by default it will take class name as itself
@Table(name = "products_dtls")
public class Products {
	//to make id as primary key
	@Id
	//value of pk generated automatically
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "product_name")
	private String productName;
	
	private String description;
	private String price;
	private String quantity;

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Products(String productName, String description, String price, String quantity) {
		super();
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Products [id=" + id + ", productName=" + productName + ", description=" + description + ", price="
				+ price + ", quantity=" + quantity + "]";
	}

}
