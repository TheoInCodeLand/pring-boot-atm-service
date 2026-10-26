package com.capitec.atm.service;

import com.capitec.atm.model.Account;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AtmService {

    private ArrayList<Account> accounts = new ArrayList<>();

    public AtmService() {
        accounts.add(new Account("ACC-1001", "Andile Masilela", 52300));
        accounts.add(new Account("ACC-1002", "Siyabonga Mashimane", 1800));
        accounts.add(new Account("ACC-1003", "Lionel Ngoma", 12500));
        accounts.add(new Account("ACC-1004", "Nhlakanipho Mpisane", 950));
        accounts.add(new Account("ACC-1005", "Siphosethu Skhosane", 52300));
        accounts.add(new Account("ACC-1006", "Mageba Mnguni", 3400));
        accounts.add(new Account("ACC-1007", "Kgopotso Mokwena", 1500));
        accounts.add(new Account("ACC-1008", "Viwe Mabhongo", 89000));
        accounts.add(new Account("ACC-1009", "Siyabonga Magudulela", 450));
    }

    // Business logic method 1: Get all accounts
    public ArrayList<Account> getAllAccounts(){
        return accounts;
    }

    // Business logic method 2: Find a single accoun
    public Account findAccountByNumber(String accountNumber){
        for(Account account : accounts){
            if(account.getAccountNumber().equalsIgnoreCase(accountNumber)){
                return account;
            }
        }
        return null;
    }

    // Business logic method 3: Deposit money into an account
    public String depositMoney(String accNum, double amount){
        for (Account currentAccount : accounts){
            if(currentAccount.getAccountNumber().equalsIgnoreCase(accNum)){
                currentAccount.deposit(amount);
                return "Deposit successful! New balance: R" + currentAccount.getBalance();
            }
        }

        return "Account " + accNum + " not found!";
    }

    // Business logic method 4: Withdraw money from an account
    public String withdrawMoney(String accNum, double amount){
        for(Account currentAccount : accounts){
            if(currentAccount.getAccountNumber().equalsIgnoreCase(accNum)){
                boolean withdrawn = currentAccount.withdraw(amount);
                if(withdrawn){
                    return "Withdrawal successful! New balance: R" + currentAccount.getBalance();
                } else {
                    return "Invalid amount! Current balance: R" + currentAccount.getBalance();
                }
            }
        }

        return "Account " + accNum + " not found!";
    }

    // Business logic method 5: Delete account
    public String deleteAccount(String accNum){
        boolean accountDeleted = accounts.removeIf((Account currentAccount) -> currentAccount.getAccountNumber().equalsIgnoreCase(accNum));
        if (accountDeleted){
            return "Account " + accNum + " successfully deleted.";
        } else {
            return "Account not found!";
        }
    }

    //
    public ArrayList<Account> getNsfasAccounts(double maxBalance){
        ArrayList<Account> nsfasStudents = new ArrayList<>();
        for(Account currentAcccount : accounts){
            if(currentAcccount.getBalance() <= maxBalance){
                nsfasStudents.add(currentAcccount);
            }
        }

        return nsfasStudents;
    }

    public ArrayList<Account> getRangedAccounts(double minBalance, double maxBalance){
        ArrayList<Account> accountsInRange = new ArrayList<>();
        for(Account currentAcccount : accounts){
            if(currentAcccount.getBalance() >= minBalance && currentAcccount.getBalance() <= maxBalance){
                accountsInRange.add(currentAcccount);
            }
        }

        return accountsInRange;
    }

    public ArrayList<Account> getAccountByUserName(String name){
        ArrayList<Account> filteredAccount = new ArrayList<>();
        for(Account currentAccount : accounts){
            if(currentAccount.getOwnerName().toLowerCase().contains(name.toLowerCase())){
                filteredAccount.add(currentAccount);
            }
        }

        return filteredAccount;
    }

    public Account createAccount(Account newAccount){
        accounts.add(newAccount);
        return newAccount;
    }

    public String updateAccount(String accNum, Account updatedAccount){
        for(Account currentAccount : accounts){
            if(currentAccount.getAccountNumber().equalsIgnoreCase(accNum)){
                currentAccount.setOwnerName(updatedAccount.getOwnerName());
                currentAccount.setBalance(updatedAccount.getBalance());

                return "Account " + currentAccount.getAccountNumber() + " Successfully updated.";
            }
        }

        return "Account not found!";
    }

    public ArrayList<String> getAccountStatement(String accNum) {
        for (Account currentAccount : accounts) {
            if (currentAccount.getAccountNumber().equalsIgnoreCase(accNum)) {
                return currentAccount.getTransactions();
            }
        }
        return null;
    }
}
