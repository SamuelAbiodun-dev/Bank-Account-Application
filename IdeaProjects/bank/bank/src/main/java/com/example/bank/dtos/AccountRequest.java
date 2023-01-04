package com.example.bank.dtos;

import com.example.bank.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class AccountRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String accountNumber;
    private double balance;

}
