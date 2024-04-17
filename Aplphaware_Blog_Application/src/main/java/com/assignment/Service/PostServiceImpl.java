package com.assignment.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.assignment.Entity.Category;
import com.assignment.Entity.Post;
import com.assignment.Entity.User;
import com.assignment.Repository.CategoryRepository;
import com.assignment.Repository.PostRepository;
import com.assignment.Repository.UserRepository;

public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Post createPost(Post post, Integer categoryId) throws Exception {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Optional<Category> opt = categoryRepository.findById(categoryId);
		
		
		
		User existingUser = userRepository.findByEmail(authentication.getName()).get();
		
		post.setUser(existingUser);
		
		
		if(opt.isEmpty()) {
			throw new Exception("Category Does not exists");
		}
		else {
			Category cat = opt.get();
			post.setCategory(cat);
		}
		
		post.setCreatedAt(LocalDateTime.now());
		post.setUpdatedAt(LocalDateTime.now());
		
		return postRepository.save(post);
	}

	@Override
	public Post updatePost(Post post, Integer postId) throws Exception {
		
		Optional<Post> opt = postRepository.findById(postId);
		
		if(opt.isEmpty()) {
			throw new Exception("Post does not exists");
		}
		else {
			Post existingPost = opt.get();
			
			if(!post.getContent().equalsIgnoreCase(existingPost.getContent())) {
				existingPost.setContent(post.getContent());
			}
			if(!post.getTitle().equalsIgnoreCase(existingPost.getTitle())) {
				existingPost.setTitle(post.getTitle());
			}
			
			existingPost.setUpdatedAt(LocalDateTime.now());
			
			postRepository.save(existingPost);
			return existingPost;
			
		}
		
		
		
	}

	@Override
	public List<Post> getPosts() throws Exception {
		return postRepository.findAll();
	}

	@Override
	public Post getPostbyPostId(Integer postId) throws Exception {
		Optional<Post> opt = postRepository.findById(postId);
		if(opt.isEmpty()) {
			throw new Exception("Post does not exists");
		}
		else {
			return opt.get();
		}
	}

}
