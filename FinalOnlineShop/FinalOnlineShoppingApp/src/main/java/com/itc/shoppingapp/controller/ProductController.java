package com.itc.shoppingapp.controller;

import java.util.List;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itc.shoppingapp.model.Products;
import com.itc.shoppingapp.repository.ProductRepository;
import com.itc.shoppingapp.service.ProductService;

//it will indicate this class as spring mvc controller
//it can handel web request
@Controller
public class ProductController {


	// automatic dependency injection
	// spring wil create object/bean for this interface
	@Autowired
	private ProductRepository productRepo;

	// it will handel http get request with "/profo" url & return index.html
	@GetMapping("/profo")
	public String home(Model m) {

		// this returs list of all product by using findAll()
		List<Products> list = productRepo.findAll();
		// Model attriute used to pass data from controller to view by using model obj
		m.addAttribute("all_products", list);
		return "index";
	}

	// it will handel http get request with "/profo2" url & return index2.html
	@GetMapping("/profo2")
	public String home2(Model m) {
		// this returs list of favourite product by using findAll()
		List<Products> list = productRepo.findAll();
		m.addAttribute("all_products", list);
		return "index2";
	}

	// it will handel http get request with "load_form" url & return add.html to add
	// produt
	@GetMapping("/load_form")
	public String loadForm() {
		return "add";
	}

	// it will handel http get request with "/edit_form/{id}" url to edit produt& return edit.html
	
	@GetMapping("/edit_form/{id}")
	// @PathVariable takes value from html template and assign it to method variable
	// i.e.id
	public String editForm(@PathVariable(value = "id") long id, Model m) {

		// to avoid abnormal termination i.e. it handels nullpointerexception we used
		// optional return type
		// optional deal with cases where value is present or absent
		Optional<Products> product = productRepo.findById(id);

		//if product is prsent pass it to edit form using object of model class
		Products pro = product.get();
		m.addAttribute("product", pro);

		return "edit";
	}

	// it will handel http post request with "/save_products" url to save produt & redirect to "/load_form" url
	
	@PostMapping("/save_products")
	public String saveProducts(@ModelAttribute Products products, HttpSession session) {

		productRepo.save(products);
		//to display msg on view
		session.setAttribute("msg", "Product Added Sucessfully..");

		return "redirect:/load_form";
	}
	
	//it will handel http post request with "/update_products" url to update produt & redirect to "/profo" url
	
	@PostMapping("/update_products")
	
	//@ModelAttribute will take forms data into product object
	
	public String updateProducts(@ModelAttribute Products products, HttpSession session) {
		//after getting data in product object save it using productRepo save() method
		productRepo.save(products);
		session.setAttribute("msg", "Product Update Sucessfully..");

		return "redirect:/profo";
	}

	////it will handel http get request with "/delete/{id}" url to delete produt & redirect to "/profo" url
	@GetMapping("/delete/{id}")
	// @PathVariable takes value from html template and assign it to method variable i.e.id
		
	public String deleteProducts(@PathVariable(value = "id") long id, HttpSession session) {
		//then delete product with the id using productRepo deleteById()
		productRepo.deleteById(id);
		session.setAttribute("msg", "Product Delete Sucessfully..");

		return "redirect:/profo";

	}
	
	//for searching product
	@Autowired
	 private ProductService service;
	 @RequestMapping(path = {"/profo","/search"})
	 public String home(Products product, Model model, String keyword) {
	  if(keyword!=null) {
	   List<Products> list = service.getByKeyword(keyword);
	   model.addAttribute("all_products", list);
	  }else {
	  List<Products> list = service.getAllPro();
	  model.addAttribute("all_products", list);}
	  return "index";
	 }

}
