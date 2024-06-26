package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;


public interface PostRepository extends JpaRepository<Post, Integer> 
{
	
	List<Post> getByUser(User user);
	
	List<Post> getByCategory(Category category);
	
    List<Post> findByTitleContaining(String title);
    
}
