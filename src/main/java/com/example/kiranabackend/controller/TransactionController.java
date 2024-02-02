package com.example.kiranabackend.controller;

import com.example.kiranabackend.model.Transaction;
import com.example.kiranabackend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transactions")

public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @GetMapping
    public List<Transaction> getAllTransactions(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate,
            @RequestParam(required = false, defaultValue = "false") boolean groupByDate) {

        if (groupByDate) {
            return transactionService.getGroupedTransactionsByDate(fromDate, toDate);
        } else {
            return transactionService.getFilteredTransactionsByDate(fromDate, toDate);
        }
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable String id) {
        return transactionService.getTransactionById(id)
                .orElse(null);
    }
}
