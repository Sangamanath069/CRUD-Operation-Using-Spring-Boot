package com.crud.sts.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.MultidimensionalCounter.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.crud.sts.entity.Products;
import com.crud.sts.gettingdatafrom.excel.ReadDataFromExcelToDatabase;


public class ProductJdbcCommandLineRunner {

	@Autowired
	private DBConnection repository;
	
		
	//@Override
//	public void run(String... args) throws Exception {
//		
//		Products products = new Products();
//		List<ArrayList<Object>> readDataFromExcel = ReadDataFromExcelToDatabase.readDataFromExcel();
//		System.out.println("readDataFromExcel--> "+readDataFromExcel);
//		for(java.util.Iterator<ArrayList<Object>> iterator = readDataFromExcel.iterator(); iterator.hasNext();) {
//			ArrayList<Object> next = (ArrayList<Object>) iterator.next();
//			ArrayList al = new ArrayList();
//			al.add(next);
//			int i=0;
//			for(Object s:al) {
//				
//				switch(i){
//					case 0: products.setProdId((int)s);
//					break;
//					case 1: products.setProdName((String)s);
//					break;
//					case 2: products.setProdQuantity((int)s);
//					break;
//					case 3: products.setProdPrice((double)s);
//					break;
//					case 4: products.setIsActive((char)s);
//					break;
//				}
//				i++;
//			}
//			 repository.insert(products);
//		}
//		
//		
//	}

	

}
