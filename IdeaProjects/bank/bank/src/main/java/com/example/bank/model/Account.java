package com.example.bank.model;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class Account {
    BigDecimal balance;
    String accountNumber;
    String pin;
}
