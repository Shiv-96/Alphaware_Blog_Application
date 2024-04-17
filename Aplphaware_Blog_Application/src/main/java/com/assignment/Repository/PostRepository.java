package com.assignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.Entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
