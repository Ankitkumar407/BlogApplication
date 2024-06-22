package com.blog.service;

import java.util.List;

import com.blog.entity.Post;
import com.blog.payLoad.PostDto;
import com.blog.payLoad.PostResponse;


public interface PostService {
	
	PostDto createPost(PostDto postDto , Integer userId , Integer categoryId);
	
	PostDto updatePost(PostDto postDto , Integer postId);
	
	void deletePost(Integer postId);
	
	PostDto getPostById(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber ,Integer pageSize,String sortBy,String sortDir);
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	List<PostDto> getPostByUser(Integer userId);
	
	List<PostDto> searchPosts(String keyword);

	

}
