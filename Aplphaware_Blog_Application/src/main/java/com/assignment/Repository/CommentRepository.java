package com.assignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.Entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	
	
}
