package com.telexiom.ngschulung.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.telexiom.ngschulung.repositories.UserRepository;

@Configuration
@EnableMongoRepositories(basePackageClasses = {UserRepository.class})
public class MongoConfig {
}