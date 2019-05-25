package com.tordot.tour.entity.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document
@Data
@NoArgsConstructor
public class Role {
	
	@Id
	private String id;
	private String name;
	private Set<UserRole> userRoles = new HashSet<>();

	
	
}
