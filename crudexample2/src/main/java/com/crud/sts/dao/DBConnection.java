package com.crud.sts.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.crud.sts.entity.Products;

@Repository
public class DBConnection {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static String INSERT_QUERY = 
			"""
			insert into products(prod_id, prod_name, prod_qunatity, prod_price, is_active)
			values(?, ?, ?, ?, ?);
			
			""";
	
	@Transactional
	public void insert(Products product) {
		jdbcTemplate.update(INSERT_QUERY, product.getProdId(), product.getProdName(),
				product.getProdQuantity(), product.getProdPrice(), product.getIsActive());
	}

	

}
