package com.tordot.tour.repository;

import com.tordot.tour.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "message", path = "message")
public interface MessageRepository extends MongoRepository<Message, String> {

}
