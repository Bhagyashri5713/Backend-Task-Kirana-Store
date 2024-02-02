package com.example.kiranabackend.service;

import com.example.kiranabackend.model.Transaction;
import com.example.kiranabackend.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Date;
import java.util.*;
import java.util.stream.*;

@Service

public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(String id) {
        return transactionRepository.findById(id);
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    } 

    public List<Transaction> getFilteredTransactionsByDate(Date fromDate, Date toDate) {
        if (fromDate != null && toDate != null) {
            return transactionRepository.findByDateBetween(fromDate, toDate);
        } else {
            return transactionRepository.findAll();
        }
    }
    public List<Transaction> getGroupedTransactionsByDate(Date fromDate, Date toDate) {
        List<Transaction> transactions = getFilteredTransactionsByDate(fromDate, toDate);

        // Group transactions by date
        Map<Date, List<Transaction>> groupedByDate = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getDate));

        // Optionally sort the result by date
        List<Map.Entry<Date, List<Transaction>>> sortedGrouped = new ArrayList<>(groupedByDate.entrySet());
        sortedGrouped.sort(Comparator.comparing(Map.Entry::getKey));

        // Flatten the grouped transactions
        List<Transaction> groupedTransactions = sortedGrouped.stream()
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toList());

        return groupedTransactions;
    }
}
