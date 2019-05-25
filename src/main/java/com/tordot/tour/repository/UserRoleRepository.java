package com.tordot.tour.repository;

import com.tordot.tour.entity.security.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRoleRepository extends MongoRepository<UserRole, String> {

}
