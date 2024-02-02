package com.example.kiranabackend.repository;

import com.example.kiranabackend.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByDateBetween(Date fromDate, Date toDate);
}
