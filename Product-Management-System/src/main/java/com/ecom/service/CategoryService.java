package com.ecom.service;

import java.util.List;
import com.ecom.exceptions.AdminNotFoundException;
import com.ecom.exceptions.CategoryNotFoundException;
import com.ecom.model.Category;
public interface CategoryService {

	
	public Category addNewCategory(String adminKey,Category category)
			throws AdminNotFoundException,CategoryNotFoundException;
	
	public Category updateCategory(String adminKey, Long categoryId, Category category)
			throws AdminNotFoundException,CategoryNotFoundException;
	
	public String   deleteCategory(String adminKey, Long categoryId)
			throws AdminNotFoundException,CategoryNotFoundException;
	
	public List<Category> getAllCategory(String adminKey)
			throws AdminNotFoundException,CategoryNotFoundException;
	
	public Category findByCategoryId(String adminKey, Long categoryId)
			throws AdminNotFoundException,CategoryNotFoundException;
}
