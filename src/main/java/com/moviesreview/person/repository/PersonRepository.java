package com.moviesreview.person.repository;

import com.moviesreview.person.model.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends MongoRepository<Person, ObjectId> {
    Optional<Person> findBySlug(String slug);
    boolean existsBySlug(String slug);
}
