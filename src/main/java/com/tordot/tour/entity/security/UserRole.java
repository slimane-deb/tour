package com.tordot.tour.entity.security;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserRole {
	
	@Id
	private String Id;
	
	@DBRef
	private User user;
	
	@DBRef
	private Role role;
	
	public UserRole(User user, Role role) {
		this.user = user;
		this.role = role;
	}
	
	public UserRole() {}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
