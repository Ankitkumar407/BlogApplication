package com.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payLoad.CategoryDto;
import com.blog.repository.CategoryRepository;
import com.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) 
	{
//		convert categoryDto To category
		Category category = this.modelMapper.map(categoryDto,Category.class);
		
//		save category in database
		Category saveCategory = this.categoryRepository.save(category);
		
//		convert category To categoryDto
		CategoryDto saveCategoryDto= this.modelMapper.map(saveCategory,CategoryDto.class);
		
//		return categoryDto
		return saveCategoryDto;
	}
	
	
	
	
	

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) 
	{
//		search category
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category" , "categoryId" , categoryId)); 
		
//		set property in category
		category.setTitle(categoryDto.getTitle());
		category.setDescription(categoryDto.getDescription());
		
//		save category
		Category updateCategory = this.categoryRepository.save(category);
		
//		convert category To categoryDto
		CategoryDto updateCategoryDto = this.modelMapper.map(updateCategory,CategoryDto.class);
		
//		return categoryDto
		return updateCategoryDto;
	}

	
	
	
	
	
	@Override
	public void deleteCategory(Integer categoryId) 
	{
//		search category
		Category category = this.categoryRepository.findById(categoryId).
		                                    orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
//		delete category
		this.categoryRepository.delete(category);
		
	}
	
	
	
	
	

	@Override
	public CategoryDto getCategoryById(Integer categoryId) 
	{
//		search category
		Category category = this.categoryRepository.findById(categoryId).
				                       orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		
//		convert category To categoryDto
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		
//		return categoryDto
		return categoryDto;
	}

	
	
	
	
	
	
	@Override
	public List<CategoryDto> getAllCategory() 
	{
//		find list of category 
		List<Category> categoryList = this.categoryRepository.findAll();
		
//		convert list of category To list of categoryDto
		List<CategoryDto> categoryDtoList = categoryList.stream().
				      map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		
//		return list of categoryDto
		return categoryDtoList;
	}
	
	
	

}
