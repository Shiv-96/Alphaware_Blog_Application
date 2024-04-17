package com.assignment.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.Entity.Category;
import com.assignment.Service.CategoryService;

import jakarta.validation.Valid;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/api/category/create")
	public ResponseEntity<Category> addCategoryHandler(@Valid @RequestBody Category cat) throws Exception {
		
		return new ResponseEntity<Category>(categoryService.addCategory(cat), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/api/category/remove/{name}")
	public ResponseEntity<Category> removeCategoryHandler(@PathVariable("name") String catname) throws Exception{
		
		return new ResponseEntity<>(categoryService.removeCategory(catname), HttpStatus.OK);
		
	}
	
	@GetMapping("/api/category/get")
	public ResponseEntity<List<Category>> getCategoryHandler() throws Exception{
		
		return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
		
	}
	
	
	@GetMapping("/api/category/get/{id}")
	public ResponseEntity<Category> getCategoryByIDHandler(@PathVariable("id") Integer id) throws Exception{
		
		return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
		
	}
	
	
}
