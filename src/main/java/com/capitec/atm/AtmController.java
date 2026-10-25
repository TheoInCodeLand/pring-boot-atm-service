package com.capitec.atm;

import org.springframework.web.bind.annotation.*;
import java.util.*;

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
    public Account getSingleAccount(@PathVariable String accountNumber){
        return atmService.findAccountByNumber(accountNumber);
    }

    // Challenge 2: The @RequestParam NSFAS Cap Filter
    @GetMapping("/accounts/nsfas")
    public ArrayList<Account> getNsfasAccounts(@RequestParam double maxBalance){
        return atmService.getNsfasAccounts(maxBalance);
    }

    @GetMapping("/accounts/range")
    public ArrayList<Account> getRangedAccounts(@RequestParam(defaultValue = "1000") double minBalance, @RequestParam(defaultValue = "2000") double maxBalance){
        return atmService.getRangedAccounts(minBalance, maxBalance);
    }

    // Challenge 3: Searching by Owner Name (@RequestParam)
    @GetMapping("/accounts/owner")
    public ArrayList<Account> getAccountByUserName(@RequestParam String name){
        return atmService.getAccountByUserName(name);
    }

    // 1. Create a brand-new Account using @RequestBody
    @PostMapping("/accounts")
    public Account createAccount(@RequestBody Account newAccount){
        return  atmService.createAccount(newAccount);
    }

    // 2. Deposit money into a specific account
    @PostMapping("/accounts/{accNum}/deposit")
    public String depositMoney(@PathVariable String accNum, @RequestParam double amount){
        return atmService.depositMoney(accNum, amount);
    }

    // 3. Withdraw money from a specific account
    @PostMapping("/accounts/{accNum}/withdraw")
    public String withdrawMoney(@PathVariable String accNum, @RequestParam double amount){
        return atmService.withdrawMoney(accNum, amount);
    }

    // @PutMapping (Updating Account Details)
    @PutMapping("/accounts/{accNum}")
    public String updateAccount(@PathVariable String accNum, @RequestBody Account updatedAccount){
        return atmService.updateAccount(accNum, updatedAccount);
    }

    //@DeleteMapping (Closing / Delete an Account)
    @DeleteMapping("/accounts/{accNum}")
    public String deleteAccount(@PathVariable String accNum){
        return atmService.deleteAccount(accNum);
    }

    @GetMapping("/accounts/{accNum}/statement")
    public ArrayList<String> getAccountStatement(@PathVariable String accNum){
        return atmService.getAccountStatement(accNum);
    }
}
