package com.crud.sts.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.sts.entity.Products;
import com.crud.sts.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Products saveOneProduct1(Products product) {
		 return productRepository.save(product);
	}
	
	public List<Products> saveProducts(List<Products> product) {
		 return productRepository.saveAll(product);
	}
	//------------------------------------------------------------------
	public void insertEmployeesFromExcel(File file) throws IOException {
		FileInputStream file1 = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(file1);
        //Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
            	continue; // skip header row
            }else {
            Products prod = new Products();
//            prod.setProdId((int)row.getCell(0).getNumericCellValue());
            prod.setProdName((String)row.getCell(1).getStringCellValue());
            prod.setProdQuantity((int)row.getCell(2).getNumericCellValue());
            prod.setProdPrice((double)row.getCell(3).getNumericCellValue());
            prod.setIsActive((String)row.getCell(4).getStringCellValue());        
            productRepository.save(prod);
            }
        }
        workbook.close();
    }
	//------------------------------------------------------------------
	
	public Products getProductById(int id) throws Exception {
		Optional<Products> product = productRepository.findById(id);
		if(product.isEmpty()) {
			throw new Exception("Product number "+id+" not present");
		}
		return product.get();
	}
	
	public List<Products> getAllProduct() {
		List<Products> product = productRepository.findByIsActive("A");
		
		return product;
	}
	
	public Products updateProduct(int id, Products product) throws Exception {
		
		Optional<Products> prod = productRepository.findById(id);
		if(prod.isEmpty()) {
			throw new Exception("Product number "+id+" not present to update");
		}
		prod.get().setProdQuantity(product.getProdQuantity());
		prod.get().setProdPrice(product.getProdPrice());
		
		return productRepository.save(prod.get());
		
	}
	
	public String deleteProduct(int id) throws Exception {
		Optional<Products> product = productRepository.findById(id);
		if(product.isEmpty()) {
			throw new Exception("Product id "+id+ " is wrong not present");
		}
		
		if(id == product.get().getProdId()) {
			product.get().setIsActive("D");
			productRepository.save(product.get());
		}
		
		return "Product "+id+" is deleted";
	}

}
