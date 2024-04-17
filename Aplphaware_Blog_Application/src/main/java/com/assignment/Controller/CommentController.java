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

import com.assignment.Entity.Comment;
import com.assignment.Service.CommentService;

import jakarta.validation.Valid;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/api/comment/create/{id}")
	public ResponseEntity<Comment> addCommentHandler(@Valid @RequestBody Comment com, @PathVariable("id") Integer postId) throws Exception {
		
		return new ResponseEntity<Comment>(commentService.addComment(com, postId), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/api/comment/remove/{id}")
	public ResponseEntity<Comment> removeCommentHandler(@PathVariable("id") Integer comid) throws Exception{
		
		return new ResponseEntity<>(commentService.deleteComment(comid), HttpStatus.OK);
		
	}
	
	@GetMapping("/api/comment/get")
	public ResponseEntity<List<Comment>> getCommentHandler() throws Exception{
		
		return new ResponseEntity<>(commentService.getAllComment(), HttpStatus.OK);
		
	}
	
}
