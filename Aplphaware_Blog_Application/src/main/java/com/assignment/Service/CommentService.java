package com.assignment.Service;

import java.util.List;

import com.assignment.Entity.Comment;

public interface CommentService {
	
	public Comment addComment(Comment comment, Integer postId) throws Exception;
	
	public Comment deleteComment(Integer ComId) throws Exception;
	
	public List<Comment> getAllComment() throws Exception;
	
}
