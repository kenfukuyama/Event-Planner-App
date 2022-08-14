package com.kb.chitchat.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kb.chitchat.models.Event;


@Repository
public interface EventRepo extends CrudRepository<Event, Long> {
	List<Event> findAll();
	
}