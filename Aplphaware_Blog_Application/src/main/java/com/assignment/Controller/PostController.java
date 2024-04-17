package com.assignment.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.Entity.Post;
import com.assignment.Service.PostService;

import jakarta.validation.Valid;

@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/api/posts/create/{id}")
	public ResponseEntity<Post> createpostHandler(@Valid @RequestBody Post post, @PathVariable("id") Integer catId) throws Exception {
		
		return new ResponseEntity<Post>(postService.createPost(post, catId), HttpStatus.OK);
		
	}
	
	@PutMapping("/api/posts/update/{id}")
	public ResponseEntity<Post> updatePostHandler(@RequestBody Post post, @PathVariable("id") Integer postId) throws Exception{
		
		return new ResponseEntity<>(postService.updatePost(post, postId), HttpStatus.OK);
		
	}
	
	@GetMapping("/api/posts/get")
	public ResponseEntity<List<Post>> getPostHandler() throws Exception{
		
		return new ResponseEntity<>(postService.getPosts(), HttpStatus.OK);
		
	}
	
	
	@GetMapping("/api/posts/get/{id}")
	public ResponseEntity<Post> getPostByIDHandler(@PathVariable("id") Integer Id) throws Exception{
		
		return new ResponseEntity<>(postService.getPostbyPostId(Id), HttpStatus.OK);
		
	}
	
}
