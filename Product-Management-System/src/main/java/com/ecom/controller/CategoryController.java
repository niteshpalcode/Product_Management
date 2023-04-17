package com.ecom.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom.exceptions.AdminNotFoundException;
import com.ecom.exceptions.CategoryNotFoundException;
import com.ecom.model.Category;
import com.ecom.service.CategoryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Repository
@RequestMapping("/ecom/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/save/{adminKey}")
	public ResponseEntity<Category> insertNewCategoryHandler(@PathVariable("adminKey") String key, Category category)
			throws AdminNotFoundException,CategoryNotFoundException{
		
		return new ResponseEntity<Category>(categoryService.addNewCategory(key, category),HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{adminKey}/{categoryId}")
	public ResponseEntity<Category> updateCategoryHandler
	(@PathVariable("adminKey") String key,@PathVariable("categoryId") Long categoryId,@RequestBody Category category)
			
			throws AdminNotFoundException,CategoryNotFoundException{
		
		return new ResponseEntity<Category>(categoryService.updateCategory(key, categoryId,category),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{adminKey}/{categoryId}")
	public ResponseEntity<String>deleteCategoryHandler(@PathVariable("adminKey") String key,@PathVariable("categoryId") Long categoryId)
	
	throws AdminNotFoundException,CategoryNotFoundException{
		
		return new ResponseEntity<String>(categoryService.deleteCategory(key, categoryId),HttpStatus.OK);
	}
	
	
	@GetMapping("getAll/{adminKey}")
	public ResponseEntity<List<Category>> getAllCategoryHandler(@PathVariable("adminKey") String key)
			throws AdminNotFoundException,CategoryNotFoundException{
		
		return new ResponseEntity<List<Category>>(categoryService.getAllCategory(key),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("getById/{adminKey}/{categoryId}")
	public ResponseEntity<Category> viewByCategoryIdHandler(@PathVariable("adminKey") String key,@PathVariable("categoryId") Long categoryId)
			throws AdminNotFoundException,CategoryNotFoundException{
		
		return new ResponseEntity<Category>(categoryService.findByCategoryId(key, categoryId),HttpStatus.OK);
	}
	
	
}
