package com.kb.chitchat.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.kb.chitchat.models.LoginUser;
import com.kb.chitchat.models.User;
import com.kb.chitchat.repositories.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo userRepo;
    
    // TO-DO: Write register and login methods!
    public User register(User newUser, BindingResult result) {
    	
        // TO-DO: Additional validations!
    	
    	// TO-DO - Reject values or
    	// register if no errors:
        // Reject if email is taken (present in database)
    	// Return null if result has errors
    	Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
    	if (potentialUser.isPresent()) {
    		result.rejectValue("email", "Exists", "Email is taken");
//    		return null;
    	}
    	
        // Reject if password doesn't match confirmation
    	if(!newUser.getPassword().equals(newUser.getConfirm())) {
    	    result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
//    	    return null;
    	}
    	
    	// you need this to catch any existing errors for creating models
    	
        if(result.hasErrors()) {
        	return null;
        }
    
        // Hash and set password, save user to database
    	String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	newUser.setPassword(hashed);

    	
//        return null;
    	System.out.println("new user created ---------" + newUser);
    	return userRepo.save(newUser);
    	
    }
    public User login(LoginUser newLoginObject, BindingResult result) {
        // TO-DO: Additional validations!
    	
    	// Find user in the DB by email
        // Reject if NOT present
    	// Return null if result has errors
    	Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
    	if (!potentialUser.isPresent()) {
    		result.rejectValue("email", "exists", "User does not exsit");
    		return null;
    	} else {
    		// the user is present, noiw we will check the passoword
    		
            // Reject if BCrypt password match fails3
    		User user = potentialUser.get(); 
    		if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
        	    result.rejectValue("password", "Matches", "Wrong Password!");
        	    return null;
        	}
    		System.out.println("successfully logged in:" + user);
    		return user;
    		
    	}
    
        // Otherwise, return the user object
    	
//        return null;
    }
    
    public User findUser(Long id) {
    	// Optional User that may or may not conatin User
        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }
    
}