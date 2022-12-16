package com.itc.shoppingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itc.shoppingapp.model.Products;
import com.itc.shoppingapp.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	 private ProductRepository repository;
	 
	 /*
	  * TODO: Get the List of Shops
	  */
	 public List<Products> getAllPro(){
	 List<Products> list =  (List<Products>)repository.findAll();
	 return list;
	 }
	 
	 /*
	  * TODO: Get Shop By keyword
	  */
	 public List<Products> getByKeyword(String keyword){
	  return repository.findByKeyword(keyword);
	 }
}
