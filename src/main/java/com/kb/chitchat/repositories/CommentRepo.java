package com.kb.chitchat.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kb.chitchat.models.Comment;
import com.kb.chitchat.models.Event;


@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {
	List<Comment> findAll();
	
	
	// all comments for specific events
	List<Comment> findByEventId(Long eventId);
	
}