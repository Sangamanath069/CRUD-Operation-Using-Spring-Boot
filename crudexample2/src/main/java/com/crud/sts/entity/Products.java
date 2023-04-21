package com.crud.sts.entity;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;


@Entity
public class Products {

	@Column(name="prod_id")
	@GeneratedValue
	@Id
	int prodId;
	
	@Column(name="prod_name")
	@Size(min=2, message="product name should be at least 2 character")
	String prodName;
	
	@Column(name="prod_quantity")
	int prodQuantity;
	
	@Column(name="prod_price")
	double prodPrice;
	
	@Column(name="is_active")
	String isActive;

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Products(int prodId, @Size(min = 2, message = "product name should be at least 2 character") String prodName,
			int prodQuantity, double prodPrice, String isActive) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodQuantity = prodQuantity;
		this.prodPrice = prodPrice;
		this.isActive = isActive;
	}

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public int getProdQuantity() {
		return prodQuantity;
	}

	public void setProdQuantity(int prodQuantity) {
		this.prodQuantity = prodQuantity;
	}

	public double getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(double prodPrice) {
		this.prodPrice = prodPrice;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String string) {
		this.isActive = string;
	}

	@Override
	public String toString() {
		return "Products [prodId=" + prodId + ", prodName=" + prodName + ", prodQuantity=" + prodQuantity
				+ ", prodPrice=" + prodPrice + ", isActive=" + isActive + "]";
	}


	
}
