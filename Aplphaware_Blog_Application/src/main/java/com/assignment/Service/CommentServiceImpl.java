package com.assignment.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.assignment.Entity.Comment;
import com.assignment.Entity.Post;
import com.assignment.Entity.User;
import com.assignment.Entity.UserType;
import com.assignment.Repository.CommentRepository;
import com.assignment.Repository.PostRepository;
import com.assignment.Repository.UserRepository;

public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public Comment addComment(Comment comment, Integer postId) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Optional<User> user = userRepository.findByEmail(auth.getName());
		
		if(user.isEmpty()) {
			throw new Exception("Not authorized");
		}
		else {
			Optional<Post> opt = postRepository.findById(postId);
			if(opt.isEmpty()) {
				throw new Exception("Post does not Exists");
			}
			else {
				Post existingPost = opt.get();
				comment.setCreatedAt(LocalDateTime.now());
				comment.setPosts(existingPost);
				comment.setUsers(user.get());
				
				return commentRepository.save(comment);
				
			}
		}
		
		
	}

	@Override
	public Comment deleteComment(Integer ComId) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByEmail(auth.getName()).get();
		
		if(user.getUserType().equals(UserType.ROLE_ADMIN)) {
			Comment existingComment = commentRepository.findById(ComId).get();
			commentRepository.delete(existingComment);
			return existingComment;
		}
		else {
			throw new Exception("Not authorized");
		}
		
	}

	@Override
	public List<Comment> getAllComment() throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByEmail(auth.getName()).get();
		
		List<Comment> comments = commentRepository.findAll();
		
		if(user.getUserType().equals(UserType.ROLE_ADMIN)) {
			return comments;
		}
		else {
			List<Comment> allcomment = new ArrayList<>();
			
			for(Comment a: comments) {
				if(a.getUsers().getEmail().equals(user.getEmail())) {
					allcomment.add(a);
				}
			}
			
			return allcomment;
			
		}
	}

}
