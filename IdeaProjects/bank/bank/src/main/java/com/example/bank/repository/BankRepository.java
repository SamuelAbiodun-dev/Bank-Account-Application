package com.example.bank.repository;

import com.example.bank.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BankRepository extends MongoRepository<User, String> {

}
