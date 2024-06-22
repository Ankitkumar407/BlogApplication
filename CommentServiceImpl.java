package com.blog.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payLoad.CommentDto;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService 
{
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) 
	{
//		 Find post by id
	     Post post = this.postRepository.findById(postId)
	    		 .orElseThrow(()-> new ResourceNotFoundException("Post","PostId",postId));
	     
//	     convert commentDto to comment
	     Comment comment = this.modelMapper.map(commentDto,Comment.class);
	     
//		 set comment in post
	     comment.setPost(post);
	     
//	     save comment
	     Comment saveComment = this.commentRepository.save(comment);
	     
//	     convert comment to commentDto
	     CommentDto saveCommentDto = this.modelMapper.map(saveComment,CommentDto.class);
	     
//		return commentDto
		return saveCommentDto;
	}

	
	
	@Override
	public void deleteComment(Integer commentId) 
	{
//		find comment by id
		Comment comment = this.commentRepository.findById(commentId)
				.orElseThrow(()-> new ResourceNotFoundException("Comment","CommentId",commentId));
		
//		delete comment
		this.commentRepository.delete(comment);
	}

}
