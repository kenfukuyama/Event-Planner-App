package com.kb.chitchat.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kb.chitchat.models.Comment;
import com.kb.chitchat.models.Event;
import com.kb.chitchat.models.User;
import com.kb.chitchat.services.CommentService;
import com.kb.chitchat.services.EventService;
import com.kb.chitchat.services.UserService;


@Controller
public class EventController {
	@Autowired 
	private EventService eventService;
	
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	
	   
    // ! SHOW  =================================================================
	@GetMapping("/home")
    public String home(HttpSession session, Model model, @ModelAttribute Event event) {
    	Long userId = (Long)session.getAttribute("userId");
    	
    	// put user back if not logged in
    	if (userId == null) {
    		return "redirect:/";
    	} 
    	
    	User user = userService.findUser(userId);
    	model.addAttribute("user", user);
    	
    	 // change here
    	List<Event> events = eventService.allEvents();
    	model.addAttribute("events", events);

    	
    	return "dashboard.jsp";
    }
	
    // ! show 1 event page
    @GetMapping("/events/{id}/show")
    public String showEvent(@PathVariable("id") Long id, Model model, HttpSession session,
    		@ModelAttribute Comment comment) {
    	model.addAttribute("event", eventService.findEvent(id));
    	model.addAttribute("userId", (Long)session.getAttribute("userId"));
    	model.addAttribute("comments", commentService.AllCommentsByEvent(id));
    	
    	// errors
    	
    	return "showEvent.jsp";
    	
    }
   
	
    
	 // ! Create  =================================================================
    // ! new Event
    @GetMapping("/events/add")
    public String addEvent(@ModelAttribute("event") Event event, Model model,
    		HttpSession session) {
    	model.addAttribute("userId", (Long)session.getAttribute("userId"));
    	
    	
        return "newEvent.jsp";
    }
    
    // ! POST new events
    @PostMapping("/events/add")
    public String createEvent(@Valid @ModelAttribute("event") Event event,
            BindingResult result, Model model) {
    	
    	
//    	eventService.checkDate(event, result);
    	
        if (result.hasErrors()) {
        	List<Event> events = eventService.allEvents();
        	model.addAttribute("events", events);
            return "dashboard.jsp";
        }
       
        eventService.saveEvent(event);
        return "redirect:/home";
        
        
    }
    
    
    // ! POST new comments
    @PostMapping("/comments/add")
    public String createComment(@Valid @ModelAttribute("comment") Comment comment, BindingResult result) {
    	
    	if (result.hasErrors()) {
    		return "redirect:/events/" + comment.getEvent().getId() + "/show"; 
    	}
    	
    	commentService.saveComment(comment);
        return "redirect:/events/" + comment.getEvent().getId() + "/show"; 
    }

    // ! Add connection
    @PostMapping("/events/addConnect")
    public String addConnect(@RequestParam("userId") Long userId,
    						 @RequestParam("eventId") Long eventId) {
    	eventService.addConnect(userId, eventId);
    	return "redirect:/home";
    }
    
    
    
    // ! Edit =================================================================
    // ! Edit event page
    @GetMapping("/events/{id}/edit")
    public String editEvent(@PathVariable("id") Long id, Model model, HttpSession session) {
    	// validation
    	Long userId = (Long) session.getAttribute("userId");   
    	Long eventUserId = eventService.findEvent(id).getUser().getId();
    	
    	if (!userId.equals(eventUserId)) {
    		return "redirect:/home";
    	}
    	
    	
    	model.addAttribute("event", eventService.findEvent(id));
    	return "editEvent.jsp";

    }
    
    
    // Edit put request
    @RequestMapping(value="/events/{id}", method=RequestMethod.PUT)
    public String updateEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, @PathVariable("id") Long id) {
        if (result.hasErrors()) {
            return  "editEvent.jsp";
        } else {
            eventService.saveEvent(event);
            return "redirect:/home";
        }
        
        
        
    }
    
    
    // Delete Route ============================================================
    // basic delete
    @RequestMapping(value="/events/{id}", method=RequestMethod.DELETE)
    public String deleteEvent(@PathVariable("id") Long id) {
    	eventService.deleteEvent(eventService.findEvent(id));
    	return "redirect:/home";
    }
    

    // remove connection
    @PostMapping("/events/removeConnect")
    public String removeConnect(@RequestParam("userId") Long userId, @RequestParam("eventId") Long eventId) {
    	eventService.removeConnect(userId, eventId);
    	return "redirect:/home";
    }

    
    


}
