package com.capitec.atm.controller;

import com.capitec.atm.model.Account;
import com.capitec.atm.service.AtmService;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.http.*;

@RestController
public class AtmController {

    private final AtmService atmService;

    public AtmController(AtmService atmService){
        this.atmService = atmService;
    }

    @GetMapping("/")
    public String homeScreen(){
        return "Welcome to the java ATM capitec home screen";
    }

    @GetMapping("/status")
    public String checkStatus(){
        return "ATM Status: ONLINE and ready to dispense cash.";
    }

    @GetMapping("/accounts")
    public ArrayList<Account> getAllAccounts(){
        return atmService.getAllAccounts();
    }

    @GetMapping("/accounts/{accountNumber}")
    public ResponseEntity<Account> getSingleAccount(@PathVariable String accountNumber){
        Account foundAccount = atmService.findAccountByNumber(accountNumber);
        if(foundAccount != null){
            return ResponseEntity.status(HttpStatus.OK).body(foundAccount);         // Sends 200 OK + JSON Account
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(foundAccount);       // Sends 404 Not Found!
        }
    }

    @GetMapping("/accounts/nsfas")
    public ArrayList<Account> getNsfasAccounts(@RequestParam double maxBalance){
        return atmService.getNsfasAccounts(maxBalance);
    }

    @GetMapping("/accounts/range")
    public ArrayList<Account> getRangedAccounts(@RequestParam(defaultValue = "1000") double minBalance, @RequestParam(defaultValue = "2000") double maxBalance){
        return atmService.getRangedAccounts(minBalance, maxBalance);
    }

    @GetMapping("/accounts/owner")
    public ResponseEntity<ArrayList<Account>> getAccountByUserName(@RequestParam String name){
        ArrayList<Account> filteredAccount = atmService.getAccountByUserName(name);
        return ResponseEntity.status(HttpStatus.OK).body(filteredAccount);
    }

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account newAccount){
        Account created =  atmService.createAccount(newAccount);

        // 2. Stamp 201 CREATED on the box, put 'created' inside, and return it:
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/accounts/{accNum}/deposit")
    public ResponseEntity<String> depositMoney(@PathVariable String accNum, @RequestParam double amount){
        String resultMessage = atmService.depositMoney(accNum, amount);
        if(resultMessage.contains("not found!")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultMessage);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(resultMessage);
        }
    }

    @PostMapping("/accounts/{accNum}/withdraw")
    public ResponseEntity<String> withdrawMoney(@PathVariable String accNum, @RequestParam double amount){
        String resultMessage = atmService.withdrawMoney(accNum, amount);
        if(resultMessage.contains("not found")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultMessage);
        } else if (resultMessage.contains("Invalid amount")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMessage);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(resultMessage);
        }
    }

    @PutMapping("/accounts/{accNum}")
    public ResponseEntity<String> updateAccount(@PathVariable String accNum, @RequestBody Account updatedAccount){
        String resultMessage = atmService.updateAccount(accNum, updatedAccount);
        if (resultMessage.contains("not found")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultMessage);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(resultMessage);
        }
    }

    @DeleteMapping("/accounts/{accNum}")
    public ResponseEntity<String> deleteAccount(@PathVariable String accNum){
        String resultMessage = atmService.deleteAccount(accNum);
        if (resultMessage.toLowerCase().contains("not found")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultMessage);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(resultMessage);
        }
    }


    @GetMapping("/accounts/{accNum}/statement")
    public ResponseEntity<ArrayList<String>> getAccountStatement(@PathVariable String accNum){
        ArrayList<String> statementFound = atmService.getAccountStatement(accNum);
        if(statementFound != null){
            return ResponseEntity.status(HttpStatus.OK).body(statementFound);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(statementFound);
        }
    }
}
