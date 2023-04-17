package com.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exceptions.AdminNotFoundException;
import com.ecom.exceptions.CategoryNotFoundException;
import com.ecom.model.AdminCurrentSession;
import com.ecom.model.Category;
import com.ecom.repository.CategoryRepository;
import com.ecom.repository.CurrentAdminSessionDao;

@Service
public class CategoryServiceImpl  implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CurrentAdminSessionDao currentAdminSessionDao;
	@Override
	public Category addNewCategory(String adminKey, Category category)
			throws AdminNotFoundException, CategoryNotFoundException {
		AdminCurrentSession isPresent = currentAdminSessionDao.findByAdminKey(adminKey);
		if(isPresent==null) {
			throw new AdminNotFoundException("Admin not Found With this admin key, please put valid Admin key..!");
		}
		Category category2 = categoryRepository.findByType(category.getType());
		if(category2!=null) {
			throw new CategoryNotFoundException("Category Already Exists..");
		}
		return	categoryRepository.save(category);
	}
	
	@Override
	public Category updateCategory(String adminKey, Long categoryId, Category category)
			throws AdminNotFoundException, CategoryNotFoundException {
		AdminCurrentSession isPresent = currentAdminSessionDao.findByAdminKey(adminKey);
		if(isPresent==null) {
			throw new AdminNotFoundException("Admin not Found With this admin key. please put valid Admin key..!");
		}
		Optional<Category> category2 = categoryRepository.findById(categoryId);
		if(!category2.isPresent()) {
			throw new CategoryNotFoundException("Category not found with this id, please put valid id");
		}
		 Category existingCategory = category2.get();
		  existingCategory.setType(category.getType());
		return  categoryRepository.save(existingCategory);
		
	}
	@Override
	public String deleteCategory(String adminKey, Long categoryId)
			throws AdminNotFoundException, CategoryNotFoundException {
		AdminCurrentSession isPresent = currentAdminSessionDao.findByAdminKey(adminKey);
		if(isPresent==null) {
			throw new AdminNotFoundException("Admin not Found With this admin key. please put valid Admin key..!");
		}
		Optional<Category> category2 = categoryRepository.findById(categoryId);
		if(category2.isPresent()) {
		categoryRepository.delete(category2.get());
		return "Category deleted Successfully with this id - " + categoryId;
		}else {
			throw new CategoryNotFoundException("No Category found with this id");
		}
	
	}
	@Override
	public List<Category> getAllCategory(String adminKey) throws AdminNotFoundException, CategoryNotFoundException {
		AdminCurrentSession isPresent = currentAdminSessionDao.findByAdminKey(adminKey);
		 
		if(isPresent==null) {
			throw new AdminNotFoundException("Admin with this key is not Present,please put valid admin key..");
		} 
		
		List<Category> categories = categoryRepository.findAll();
		if(categories.isEmpty()) {
			throw new CategoryNotFoundException("No Category found or Category is empty.!");
		}
		return categories;
		
	}
	@Override
	public Category findByCategoryId(String adminKey, Long categoryId)
			throws AdminNotFoundException, CategoryNotFoundException {
		AdminCurrentSession isPresent = currentAdminSessionDao.findByAdminKey(adminKey);
		 
		if(isPresent==null) {
		throw new AdminNotFoundException("Admin with this key is not Present,please put valdi admin key..");
	 } 
		Optional<Category> category2 = categoryRepository.findById(categoryId);
		if(!category2.isPresent()) {
			throw new CategoryNotFoundException("Category not found with this id, please put valid id");
		}
		return category2.get();

	}
	
}
