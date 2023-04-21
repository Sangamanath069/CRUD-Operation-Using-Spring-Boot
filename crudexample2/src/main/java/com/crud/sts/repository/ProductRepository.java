package com.crud.sts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.sts.entity.Products;


public interface ProductRepository extends JpaRepository<Products, Integer>{

	public List<Products> findByIsActive(String isActive);
}
