package com.assignment.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.assignment.Entity.Category;
import com.assignment.Entity.User;
import com.assignment.Entity.UserType;
import com.assignment.Repository.CategoryRepository;
import com.assignment.Repository.UserRepository;

public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category category) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByEmail(auth.getName()).get();
		
		if(user.getUserType().equals(UserType.ROLE_ADMIN)) {
			return categoryRepository.save(category);
		}
		else {
			throw new Exception("Not authorized");
		}
	}

	@Override
	public Category removeCategory(String ctegoryName) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByEmail(auth.getName()).get();
		
		if(user.getUserType().equals(UserType.ROLE_ADMIN)) {
			Category existingCategory = categoryRepository.findByCategoryName(ctegoryName);
			categoryRepository.delete(existingCategory);
			return existingCategory;
		}
		else {
			throw new Exception("Not authorized");
		}
	}

	@Override
	public List<Category> getAllCategory() throws Exception {
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}

	@Override
	public Category getCategoryById(Integer catId) throws Exception {
		Optional<Category> opt = categoryRepository.findById(catId);
		if(opt.isEmpty()) {
			throw new Exception("Invalid Id");
		}
		else {
			return opt.get();
		}
	}

}
