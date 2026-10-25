package com.capitec.atm;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AtmController {

    public AtmController() {
        acc.add(new Account("ACC-1001", "Andile Masilela", 52300));
        acc.add(new Account("ACC-1002", "Siyabonga Mashimane", 1800));
        acc.add(new Account("ACC-1003", "Lionel Ngoma", 12500));
        acc.add(new Account("ACC-1004", "Nhlakanipho Mpisane", 950));
        acc.add(new Account("ACC-1005", "Siphosethu Skhosane", 52300));
        acc.add(new Account("ACC-1006", "Mageba Mnguni", 3400));
        acc.add(new Account("ACC-1007", "Kgopotso Mokwena", 1500));
        acc.add(new Account("ACC-1008", "Viwe Mabhongo", 89000));
        acc.add(new Account("ACC-1009", "Siyabonga Magudulela", 450));
    }

    ArrayList<Account> acc = new ArrayList<>();

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
        return acc;
    }

    @GetMapping("/accounts/{accountNumber}")
    public Account getSingleAccount(@PathVariable String accountNumber){
        for(Account account : acc){
            if(account.getAccountNumber().equals(accountNumber)){
                return account;
            }
        }
        return null;
    }

    @GetMapping("/accounts/{accountNumber}/{ownerName}")
    public Account getAccount(@PathVariable String accountNumber, @PathVariable String ownerName){
        for(Account account : acc){
            if(account.getAccountNumber().equals(accountNumber) && account.getOwnerName().equals(ownerName)){
                return account;
            }
        }
        return null;
    }

    // Challenge 1: The @PathVariable Balance Checker
    @GetMapping("/accounts/{accNum}/balance")
    public String getAccountBalance(@PathVariable String accNum){
        for(Account currentAccount : acc){
            if (currentAccount != null && currentAccount.getAccountNumber().equals(accNum)){
                // "The balance for Siyabonga Mashimane is R1800.0"
                return "The balance for " + currentAccount.getOwnerName() + " is R" + currentAccount.getBalance();
            }
        }
        return "Account not found!";
    }

    // Challenge 2: The @RequestParam NSFAS Cap Filter
    @GetMapping("/accounts/nsfas")
    public ArrayList<Account> getNsfasAccounts(@RequestParam double maxBalance){
        ArrayList<Account> nsfasAccounts = new ArrayList<>();
        for (Account currentAccount: acc){
            if (currentAccount != null && currentAccount.getBalance() <= maxBalance){
                nsfasAccounts.add(currentAccount);
            }
        }
        return nsfasAccounts;
    }

    @GetMapping("/accounts/range")
    public ArrayList<Account> getRangedAccounts(@RequestParam(defaultValue = "1000") double minBalance, @RequestParam(defaultValue = "2000") double maxBalance){
        ArrayList<Account> accountsInRange = new ArrayList<>();

        for (Account currentAccount : acc){
            if (currentAccount != null && currentAccount.getBalance() >= minBalance && currentAccount.getBalance() <= maxBalance){
                accountsInRange.add(currentAccount);
            }
        }
        return accountsInRange;
    }

    // Challenge 3: Searching by Owner Name (@RequestParam)
    @GetMapping("/accounts/owner")
    public ArrayList<Account> getAccountByUserName(@RequestParam String name){
        ArrayList<Account> ownerAccounts = new ArrayList<>();
        for (Account currentAccount : acc){
            if (currentAccount.getOwnerName().toLowerCase().contains(name.toLowerCase())){
                ownerAccounts.add(currentAccount);
            }
        }
        return ownerAccounts;
    }

    @PostMapping("/accounts")
    public String createAccount(@RequestBody Account newAccount){
        acc.add(newAccount);
        return "Account successfully created!";
    }
}
