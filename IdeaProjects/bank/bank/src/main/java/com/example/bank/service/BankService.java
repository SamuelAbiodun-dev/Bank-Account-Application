package com.example.bank.service;

import com.example.bank.dtos.AccountRequest;
import com.example.bank.repository.BankRepository;
import com.example.bank.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BankService {
    BankRepository bankRepository;

    public void createUser(AccountRequest accountRequest) {
        User user = new User();
//        Account account = new Account();

        user.setFirstName(accountRequest.getFirstName());
        user.setLastName(accountRequest.getLastName());
        user.setPhoneNumber(accountRequest.getPhoneNumber());
        user.setBalance(accountRequest.getBalance());
        user.setAccountNumber(accountRequest.getAccountNumber());

//        account.setBalance(accountRequest.getAccount().getBalance());
//        account.setAccountNumber(accountRequest.getAccount().getAccountNumber());
        bankRepository.save(user);
    }

    public List<User> getAllUser() {
        return bankRepository.findAll();
    }

    public void deleteUserById(String id) {
        bankRepository.deleteById(id);
    }

    public Optional<User> getUserById(String id) {
        return bankRepository.findById(id);
    }

    public void deposit(String id, double depositAmount) {
        Optional<User> user = bankRepository.findById(id);
        boolean exist = user.isPresent();
        if (!exist) {
            throw new IllegalArgumentException("User with" + id + "does not exist");
        }
        double newBalance = user.get().getBalance() + depositAmount;
        user.get().setBalance(newBalance);
        bankRepository.save(user.get());
    }


    public void withdraw(String id, double withdrawAmount) {
        Optional<User> sender = bankRepository.findById(id);
        boolean exist = sender.isPresent();
        if (!exist) {
            throw new IllegalArgumentException("User with" + id + "does not exist");
        }
        if (sender.get().getBalance() < withdrawAmount) {
            throw new IllegalArgumentException("Insufficient fund");
        } else {
            double newBalance = sender.get().getBalance() - withdrawAmount;
            sender.get().setBalance(newBalance);
            bankRepository.save(sender.get());
        }
    }

    public void transfer(String senderId, String receiverId, double transferAmount) {
        if(senderId.equals(receiverId)){
            throw new IllegalArgumentException("Sender's account is same with the receiver's id");
        }
        withdraw(senderId, transferAmount);
        deposit(receiverId, transferAmount);
        }
    }

