package com.stackgroup.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackgroup.service.document.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, Long> {

}
