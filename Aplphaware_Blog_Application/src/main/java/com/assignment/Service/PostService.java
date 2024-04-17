package com.assignment.Service;

import java.util.List;


import com.assignment.Entity.Post;

public interface PostService {
	
	public Post createPost(Post post, Integer categoryId) throws Exception;
	
	public Post updatePost(Post post, Integer postId) throws Exception;
	
	public List<Post> getPosts() throws Exception;
	
	public Post getPostbyPostId(Integer postId) throws Exception;
	
}
