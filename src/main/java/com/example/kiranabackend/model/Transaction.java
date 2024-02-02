package com.example.kiranabackend.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;
    private BigDecimal amount;
    private Date date;
    private String category;
    private String payee;
    private String currency = "INR"; 
}
