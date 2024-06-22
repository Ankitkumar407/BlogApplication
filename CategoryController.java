package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payLoad.ApiResponse;
import com.blog.payLoad.CategoryDto;
import com.blog.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto saveCategoryDto = this.categoryService.createCategory(categoryDto);
		
	     return new ResponseEntity<>(saveCategoryDto,HttpStatus.CREATED);	
		
	}
	
	
	
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto , @PathVariable Integer categoryId)
	{
		CategoryDto updateCategoryDto = this.categoryService.updateCategory(categoryDto, categoryId);
		
		return new ResponseEntity<>(updateCategoryDto,HttpStatus.OK);
	}
	
	
	

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId)
	{
		this.categoryService.deleteCategory(categoryId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category delete Successfully " ,true),HttpStatus.OK);
		
	}
	
	
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId)
	{
		CategoryDto getCategoryDto = this.categoryService.getCategoryById(categoryId);
		
		return new ResponseEntity<>(getCategoryDto,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory()
	{
		List<CategoryDto> allCategoryDto = this.categoryService.getAllCategory();
		
		return new ResponseEntity<>(allCategoryDto,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
}
