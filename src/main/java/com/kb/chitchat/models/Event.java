package com.kb.chitchat.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

// Domain Model
@Entity // Related to databases
@Table(name="events") // TODO: change the table name
public class Event {
	// domain models allow us to create table
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long id;
	
    @NotNull
    @Size(min = 3, max = 200, message="name needs to be more than 3 characters")
    private String name;
    
    
    @NotNull
    @Future
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date eventDate;
    
    @NotNull
    @Size(min = 3, max = 200, message="location needs to be more than 3 characters")
    private String location;
    
    
    // This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    
	// before it created
    @PrePersist 
    protected void onCreate(){
        this.createdAt = new Date();
    }
    // before it updates, null at first
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
    
    // TODO: Add relationship
    // TODO: getter for setter for relationships too.
    // many to one relationship
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    
    // you could use thingID, but it will convert to thing_id in MySQL databases
    // TODO: Add relationship - many to many
    // TODO: getter for setter for relationships too.
    // many to one relationship

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name="attendees",
    		joinColumns = @JoinColumn(name = "event_id"),
    		inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> attendingUsers;
    
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name="comments",
    		joinColumns = @JoinColumn(name = "event_id"),
    		inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> CommentingUsers;
    
  
    

	// empty public constructor
    public Event() {}

    // TODO: overloaded constructor
	public Event(String name, Date evenDate, String location) {
		this.name = name;
		this.eventDate = evenDate;
		this.location = location;
	}
	
    // TODO add getter and setters for member variable
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
    public List<User> getAttendingUsers() {
		return attendingUsers;
	}
	public void setAttendingUsers(List<User> attendingUsers) {
		this.attendingUsers = attendingUsers;
	}
	public List<User> getCommentingUsers() {
		return CommentingUsers;
	}
	public void setCommentingUsers(List<User> commentingUsers) {
		CommentingUsers = commentingUsers;
	}

}
