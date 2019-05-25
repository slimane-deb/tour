package com.tordot.tour.repository;

import com.tordot.tour.entity.security.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends MongoRepository<User, String> {
	User findByUsername(@Param("username") String name);
	
	User findByEmail(@Param("email") String email);
}
