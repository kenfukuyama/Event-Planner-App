package com.kb.chitchat.controllers;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kb.chitchat.models.LoginUser;
import com.kb.chitchat.models.User;
import com.kb.chitchat.services.UserService;

@Controller
public class HomeController {
    
    // Add once service is implemented:
     @Autowired
     private UserService userService;
    
     
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
    	Long userId = (Long)session.getAttribute("userId");
    	
    	// put user back if logged in
    	if (userId != null) {
    		return "redirect:/home";
    	} 
    
        // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    
    
    
    // REGISTER
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        
        System.out.println("attempting to post a new user: ----------------");
        
//        if(result.hasErrors()) {
//            // Be sure to send in the empty LoginUser 
//        	// this is the other model that is clicked
//            // re-rendering the page.
//            model.addAttribute("newLogin", new LoginUser());
//            return "index.jsp";
//        }
        
        // TO-DO Later -- call a register method in the service 
//         to do some extra validations and create a- new user!
//    	System.out.println("User Object: " + newUser);
    	User user = userService.register(newUser, result);
    	
    	
    
    	// the result has binded to the newUser, so you don't need
    	// to direclty castch the result
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser 
        	// this is the other model that is clicked
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
    	
        
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        if (user != null) {
        	session.setAttribute("userId", user.getId()); 
//        	System.out.println(session.getAttribute("userId"));
        } 
        else {
        	System.out.println("User is null");
        }
        
    
        return "redirect:/home";
    }
    
    // LOGIN
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
    	
    	System.out.println("attempting to login a new user: ----------------");
//    	System.out.println("LoginUser object: " + newLogin);
        
        // Add once service is implemented:
        // User user = userServ.login(newLogin, result);
    
//        if(result.hasErrors()) {
//            model.addAttribute("newUser", new User());
//            return "index.jsp";
//        }
    
    	System.out.println("loginuser Object: " + newLogin);
    	User user = userService.login(newLogin, result);
    	System.out.println("returned Logged in user is: " + user);
    	
    	// the result has binded to the newUser, so you don't need
    	// to trickled catch the result
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
    	
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        if (user != null) {
        	session.setAttribute("userId", user.getId()); 
//        	System.out.println(session.getAttribute("userId"));
        } 
        else {
        	System.out.println("User is null");
        }
        
    
        return "redirect:/home";
    }
    
 
    // LOGOUT
    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
//    	httpSession.removeAttribute("userId");
    	httpSession.invalidate();
    	return "redirect:/";
    }
    
    







}


