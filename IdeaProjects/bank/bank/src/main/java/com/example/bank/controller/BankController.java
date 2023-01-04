package com.example.bank.controller;

import com.example.bank.dtos.AccountRequest;
import com.example.bank.service.BankService;
import com.example.bank.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class BankController {
    @Autowired
    BankService bankService;
    @PostMapping("/create")
    public String createUser(@RequestBody AccountRequest accountRequest){
        bankService.createUser(accountRequest);
        return "Successful";
    }
    @GetMapping("/all")
    public List<User> viewAllUsers(){
       return  bankService.getAllUser();
    }
    @DeleteMapping("{deleteUserId}")
    public String deleteUser(@PathVariable ("deleteUserId") String id){
        bankService.deleteUserById(id);
        return "Deleted";
    }
    @GetMapping("{userId}")
    public Optional<User> getUser(@PathVariable("userId") String id){
        return bankService.getUserById(id);
    }
    @PostMapping("/deposit/{userId}/{depositAmount}")
    public String makeDeposit(@PathVariable("userId") String id, @PathVariable("depositAmount") double depositAmount){
        bankService.deposit(id, depositAmount);
        return "Successful";
    }
    @PostMapping("/withdraw/{userId}/{withdrawAmount}")
    public String withdrawAmount(@PathVariable("userId") String id, @PathVariable("withdrawAmount") double withdrawAmount){
        bankService.withdraw(id, withdrawAmount);
        return "Successful";
    }
    @PostMapping("/transfer/{receiverId}/{senderId}/{transferAmount}")
    public String transferAmount(@PathVariable("receiverId") String receiverId, @PathVariable("senderId") String senderId, @PathVariable("transferAmount")
                                 double transferAmount){
        bankService.transfer(senderId, receiverId, transferAmount);
        return "Successful";
    }
}
