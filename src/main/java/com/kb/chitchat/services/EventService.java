package com.kb.chitchat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kb.chitchat.models.Event;
import com.kb.chitchat.models.User;
import com.kb.chitchat.repositories.EventRepo;

@Service
public class EventService {
	@Autowired 
	private EventRepo eventRepo;

	// for many to many
	@Autowired
	private UserService userService;
	
		
	// read all
	public List<Event> allEvents(){ 
		return eventRepo.findAll();
	}
	
	// read one
//	public List<Event> allEventsOfEvent(Event event) {
//		return eventRepo.findByEvents(event);
//	}
//	
//	public List<Event> allEventsNotofEvent(Event event) {
//		return eventRepo.findByEventsNotContains(event);
//	}
	
	
	// Create and Update
	public Event saveEvent(Event event) {
		return eventRepo.save(event);
	}
	
	// delete
	public void deleteEvent(Event event) {
		eventRepo.delete(event);
	}
	
	// read one
	public Event findEvent(Long id) {
		Optional<Event> optionalEvent = eventRepo.findById(id);
		
		if (optionalEvent.isPresent()) { return optionalEvent.get(); } 
		else { return null;}
	}
	
	
	
	// TODO: many to many relationship service
	public Event addConnect(Long userId, Long eventId) {
		// retrieve an instance of a event using another method in the service.
	    Event thisEvent = findEvent(eventId);
	    User thisUser = userService.findUser(userId);
	    // add the user to this event's list of users
	    thisEvent.getAttendingUsers().add(thisUser);
	    
	    // Save thisEvent, since you made changes to its user list.
	    return eventRepo.save(thisEvent);
		
	}
	
	
	public Event removeConnect(Long userId, Long eventId) {
		// retrieve an instance of a event using another method in the service.
	    Event thisEvent = findEvent(eventId);
	    User thisUser = userService.findUser(userId);
	    // add the user to this event's list of users
	    thisEvent.getAttendingUsers().remove(thisUser);
	    
	    // Save thisEvent, since you made changes to its user list.
	    // TODO: don't forget this!! 
	    return eventRepo.save(thisEvent);
		
	}
	// ! TODO addition validation
//	public void checkDate(Event event, BindingResult result) {
//		System.out.println(event.getEventDate());
//		System.out.println(new Date());
//		
//		if (event.getEventDate().after(new Date())) {
//			System.out.println("it is future");
//		}
//		else {
//			System.out.println("not futre");
//		}
//	}
//	
	
}
