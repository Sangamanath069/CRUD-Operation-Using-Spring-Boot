package com.crud.sts.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.sts.entity.Products;
import com.crud.sts.insertinto.excel.InsertIntoExcel;
import com.crud.sts.repository.ProductRepository;
import com.crud.sts.service.ProductService;

@RestController
@RequestMapping("/home")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private EntityManager entityManager;
	
	static ArrayList<Products> a11 = new ArrayList();
	
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping("/create/one/prod")
	public void saveOneProduct(@Valid @RequestBody Products product) {
		  productRepository.save(product);
	}
	
	@PostMapping("/create/multiple/prod")
	public List<Products> saveProducts(@Valid @RequestBody List<Products> product) {
		 return productService.saveProducts(product);
	}
//-------------------------------------------------------------------------------------
	@GetMapping("/saveProds7")
	public void saveProdsExel() throws IOException {
		File file = new File("E://DailyWork//StoringEmpData.xlsx");
		productService.insertEmployeesFromExcel(file);
		
		
		//		System.out.println("-------------------------------------");
//		List<ArrayList<Object>> readDataFromExcel = ReadDataFromExcelToDatabase.readDataFromExcel();
//		return readDataFromExcel;
		
		//System.out.println("readDataFromExcel --> "+readDataFromExcel.toString());
//		for(Iterator iterator= readDataFromExcel.iterator(); iterator.hasNext();) {
//
//			System.out.println(iterator.next());
//
//		}
		
//		for(Iterator iterator= readDataFromExcel.iterator(); iterator.hasNext();) {
//			Object[] next = (Object[]) iterator.next();
//			for(int i=0; i<=next.length-1; i++) {
//				System.out.println(next[i]+" ");
//			}
//			//System.out.println("--> "+iterator.next().toString());
//		}
//		System.out.println("List<ArrayList<Object>> --> "+readDataFromExcel);
//		System.out.println("readDataFromExcel--> "+readDataFromExcel);
	}
//-------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
	//***************************************************************************************
	@GetMapping("/getting/one/prod/{id}")
	public Products getProductById(@PathVariable int id) throws Exception {
		Products product = productService.getProductById(id);
//		ArrayList<Products> a = new ArrayList();
//		a.add(product);
		int prodId = product.getProdId();
		String prodName = product.getProdName();
		String isActive = product.getIsActive();
		double prodPrice = product.getProdPrice();
		int prodQuantity = product.getProdQuantity();
		System.out.println("Products --> "+product.toString());
		ArrayList<Products> dataFrom = ProductController.dataFrom(prodId, prodName,prodQuantity,prodPrice,isActive);
		InsertIntoExcel.insertDataToExcel(dataFrom);
		return product;
	}
	private static ArrayList<Products> dataFrom(int prodId, String prodName, int prodQuantity, double prodPrice, String isActive) {
		
		ArrayList<Products> a = new ArrayList();
		a.add(new Products(prodId, prodName, prodQuantity, prodPrice, isActive));
		System.out.println("a from controller--> "+a);
		return a;
		
	}

//*******************************************************************************************
	
	
	@GetMapping("/getting/all/prod")
	public List<Products> getAllProduct() {
		List<Products> product = productService.getAllProduct();
		ArrayList<Products> dataFrom=null;
		dataFrom = ProductController.dataFrom1(product);

		InsertIntoExcel.insertAllDataToExcel(dataFrom);
		return product;
	}
	
	private static ArrayList<Products> dataFrom1(List<Products> product) {
		
		for(Iterator<Products> iterator = product.iterator();iterator.hasNext();) {
		Products next = iterator.next();
		int prodId = next.getProdId();
		String prodName = next.getProdName();
		String isActive = next.getIsActive();
		double prodPrice = next.getProdPrice();
		int prodQuantity = next.getProdQuantity();
		a11.add(new Products(prodId, prodName, prodQuantity, prodPrice, isActive));
     //a11.add(dataFrom);
	}
		
		System.out.println("a from controller--> "+a11);
		return a11;
		
	}
	//**************************************************************************************
	
	
	@GetMapping("/getting/all/prods")
	public List<Products> getAllProducts() {
		List<Products> product = productService.getAllProduct();
		
		return product;
	}
	
	@GetMapping("/getting/all/prod1")
	 public List<Map<String, Object>> getColumnValue() {
	        String sql = "SELECT prod_name FROM products";
	        List<Map<String, Object>> prodName = jdbcTemplate.queryForList(sql);
	        return prodName;
	    }
	
	@GetMapping("/getting/all/prod2/{price}")
	 public List<Map<String, Object>> getColumnValue1(@PathVariable double price) {
	        String sql = "SELECT prod_name, prod_id, prod_price, prod_quantity FROM products WHERE is_active = 'A' AND prod_price > ?";
	        System.out.println(sql);
	        List<Map<String, Object>> prods = jdbcTemplate.queryForList(sql,price);
	       System.out.println(prods);
	        return prods;
	    }
	
	
	@GetMapping("/getting/all/prod3/{price}")
	 public  Object getColumnValue2(@PathVariable double price) {
	        String sql = "SELECT prod_name, prod_id, prod_price, prod_quantity FROM products WHERE is_active = 'A' AND prod_price > 2000";
	        System.out.println(sql);
	        Session session = entityManager.unwrap(Session.class);
	        NativeQuery createNativeQuery = session.createNativeQuery(sql);
			List resultList = createNativeQuery.getResultList();
			System.out.println("resultList --> "+resultList);
			
			//creating an ArrayList
			List<Map<String, Object>> list = new ArrayList<>();
			String[] ob = {"prod_name", "prod_id", "prod_price", "prod_quantity"};
			for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
				Map<String, Object> map = new LinkedHashMap<>();
				Object[] object = (Object[]) iterator.next();
				for (int i = 0; i < object.length; i++) {
					Object value = object[i];
				
					map.put(ob[i], value);
				}
				list.add(map);
			}
//	        return resultList;
			return list;
//	        Query query = entityManager.createQuery(sql);
//	        List<Map<String, Object>> res = query.getResultList();
//	        return  res;
	    }
	
	@GetMapping("/getting/all/prod3")
	public List getProductUsingEntity() {
		
		String sql = " SELECT * from products";
		Session session = entityManager.unwrap(Session.class);	
		NativeQuery createNativeQuery = session.createNativeQuery(sql);
		List resultList = createNativeQuery.getResultList();
		String[] heading = {"prod_name", "is_active","prod_id", "prod_price", "prod_quantity"};
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map = null;
		for(Iterator iterator =resultList.iterator(); iterator.hasNext();) {
			Object[] nextValue = (Object[]) iterator.next();
			map = new LinkedHashMap<>();
			for(int i=0; i<nextValue.length; i++) {
				 
				map.put(heading[i], nextValue[i]);
			}
			
			list.add(map);
		}
		return list;
	}
	//Session session = sessionFactory.getCurrentSession();
	/*Criteria criteria = session.createCriteria(Person.class);
	List<Person> persons = criteria.list();
	*/
		
		/*@GetMapping("/getting/all/prod4")
		 public List<Map<String, Object>> getColumnValue4() {

			//Session session = sessionFactory.getCurrentSession();
	        
	            
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(Products.class);
	        
			
			List list = criteria.list();
			System.out.println(list);
			List<Map<String, Object>> list1 = new ArrayList<>();
			String[] ob = {"prod_name", "prod_id", "prod_price", "prod_quantity"};
			
			for(Iterator iterator = list.iterator(); iterator.hasNext();){
				Object[] next = (Object[]) iterator.next();
				Map<String, Object> map = new LinkedHashMap();
				for(int i=0; i<=ob.length-1; i++) {
					map.put(ob[i], next);
				}
				list1.add(map);
			}
		    return list1;
		       
		}*/
	
	@PutMapping("/update/prod/{id}")
	public Products updateProduct(@PathVariable int id, @RequestBody Products product) throws Exception {	
	    Products prod = productService.updateProduct(id,product);
		return prod;
		
	}
	
	@DeleteMapping("/delete/prod/{id}")
	public String deleteProduct(@PathVariable int id) throws Exception {
		return productService.deleteProduct(id);
	}


}
