package com.itc.shoppingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itc.shoppingapp.model.Products;

//It indicates that the class provides mechanism for crud operation
@Repository

//JPA is java persistence api it interact with databases and perform db opration
//it internally implements hibernate
public interface ProductRepository extends JpaRepository<Products, Long> {
	
	//custom native query is created by using @Query to search for the product_name field and price filed.
	@Query(value = "select * from products_dtls s where s.product_name like %:keyword% or s.price like %:keyword%", nativeQuery = true)
	
	//@Param used to bind method parameters to a query.
	 List<Products> findByKeyword(@Param("keyword") String keyword);

}