package com.assignment.Service;

import java.util.List;

import com.assignment.Entity.Category;

public interface CategoryService {

	public Category addCategory(Category category) throws Exception;

	public Category removeCategory(String ctegoryName) throws Exception;

	public List<Category> getAllCategory() throws Exception;

	public Category getCategoryById(Integer catId) throws Exception;

}
