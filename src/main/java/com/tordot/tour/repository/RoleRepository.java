package com.tordot.tour.repository;

import com.tordot.tour.entity.security.Role;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RoleRepository extends MongoRepository<Role, String>{

}
