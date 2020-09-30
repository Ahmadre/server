package com.telexiom.ngschulung.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.telexiom.ngschulung.documents.User;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{ 'email' : ?0 }")
    User findByEmail(String email);
}
