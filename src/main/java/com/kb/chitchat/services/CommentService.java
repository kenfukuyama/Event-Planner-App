package com.kb.chitchat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kb.chitchat.models.Comment;
import com.kb.chitchat.repositories.CommentRepo;

@Service
public class CommentService {
	@Autowired 
	private CommentRepo commentRepo;

	// for many to many
//	@Autowired
//	private UserService userService;
	
		
	// read all
	public List<Comment> allComments(){ 
		return commentRepo.findAll();
	}
	
	// read one
//	public List<Comment> allCommentsOfComment(Comment comment) {
//		return commentRepo.findByComments(comment);
//	}
//	
//	public List<Comment> allCommentsNotofComment(Comment comment) {
//		return commentRepo.findByCommentsNotContains(comment);
//	}
	
	
	// Create and Update
	public Comment saveComment(Comment comment) {
		return commentRepo.save(comment);
	}
	
	// delete
	public void deleteComment(Comment comment) {
		commentRepo.delete(comment);
	}
	
	// read one
	public Comment findComment(Long id) {
		Optional<Comment> optionalComment = commentRepo.findById(id);
		
		if (optionalComment.isPresent()) { return optionalComment.get(); } 
		else { return null;}
	}
	
	
	public List<Comment> AllCommentsByEvent(Long eventId) {
		return commentRepo.findByEventId(eventId);
	}
	
	
	
	
	
	
	
	
	
	
	// TODO: many to many relationship service
//	public Comment addConnect(Long userId, Long commentId) {
//		// retrieve an instance of a comment using another method in the service.
//	    Comment thisComment = findComment(commentId);
//	    User thisUser = userService.findUser(userId);
//	    // add the user to this comment's list of users
//	    thisComment.getAttendingUsers().add(thisUser);
//	    
//	    // Save thisComment, since you made changes to its user list.
//	    return commentRepo.save(thisComment);
//		
//	}
//	
	
//	public Comment removeConnect(Long userId, Long commentId) {
//		// retrieve an instance of a comment using another method in the service.
//	    Comment thisComment = findComment(commentId);
//	    User thisUser = userService.findUser(userId);
//	    // add the user to this comment's list of users
//	    thisComment.getAttendingUsers().remove(thisUser);
//	    
//	    // Save thisComment, since you made changes to its user list.
//	    // TODO: don't forget this!! 
//	    return commentRepo.save(thisComment);
//		
//	}
	
	
	// ! TODO addition validation
//	public void checkDate(Comment comment, BindingResult result) {
//		System.out.println(comment.getCommentDate());
//		System.out.println(new Date());
//		
//		if (comment.getCommentDate().after(new Date())) {
//			System.out.println("it is future");
//		}
//		else {
//			System.out.println("not futre");
//		}
//	}
//	
	
}
