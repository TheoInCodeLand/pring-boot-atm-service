package com.capitec.atm.service;

import com.capitec.atm.model.Account;
import com.capitec.atm.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.util.*;

@Service
public class AtmService {

//    Constructor Dependency Injection
    private final AccountRepository accountRepository;
    private ArrayList<Account> accounts = new ArrayList<>();

    public AtmService(AccountRepository accountRepository) {
//      Constructor Dependency Injection
        this.accountRepository = accountRepository;

        if (accountRepository.count()==0){
            accountRepository.save(new Account("ACC-1001", "Andile Masilela", 52300));
            accountRepository.save(new Account("ACC-1002", "Siyabonga Mashimane", 1800));
            accountRepository.save(new Account("ACC-1003", "Lionel Ngoma", 12500));
            accountRepository.save(new Account("ACC-1004", "Nhlakanipho Mpisane", 950));
            accountRepository.save(new Account("ACC-1005", "Siphosethu Skhosane", 52300));
            accountRepository.save(new Account("ACC-1006", "Mageba Mnguni", 3400));
            accountRepository.save(new Account("ACC-1007", "Kgopotso Mokwena", 1500));
            accountRepository.save(new Account("ACC-1008", "Viwe Mabhongo", 89000));
            accountRepository.save(new Account("ACC-1009", "Siyabonga Magudulela", 450));
        }
    }

    // Business logic method 1: Get all accounts
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    // Business logic method 2: Find a single accoun
    public Optional<Account> findAccountByNumber(String accountNumber){
        return accountRepository.findByAccountNumberIgnoreCase(accountNumber);
    }

    // Business logic method 3: Deposit money into an account
    public String depositMoney(String accNum, double amount){
        Optional<Account> currentAccount = findAccountByNumber(accNum);
        if (currentAccount != null){
            Account account = currentAccount.get();
            account.deposit(amount);
            accountRepository.save(account);    // Saves the new balance & transaction to Neon!
            return "Deposit successful! New balance: R" + account.getBalance();
        }

        return "Account " + accNum + " not found!";
    }

    // Business logic method 4: Withdraw money from an account
    public String withdrawMoney(String accNum, double amount){

        Optional<Account> optionalAccount = findAccountByNumber(accNum);
        if (optionalAccount.isPresent()){
            Account currentAccount = optionalAccount.get();
            currentAccount.withdraw(amount);
            accountRepository.save(currentAccount);
            return "Withdrawal successful! New balance: R" + currentAccount.getBalance();
        }

        return "Account " + accNum + " not found!";
    }

    // Business logic method 5: Delete account
    public String deleteAccount(String accNum) {
        Optional<Account> optionalAccount = accountRepository.findByAccountNumberIgnoreCase(accNum);

        if (optionalAccount.isEmpty()) {
            return "Account " + accNum + " not found!";
        }

        accountRepository.delete(optionalAccount.get());
        return "Account " + accNum + " successfully deleted!";
    }

    //
    public List getNsfasAccounts(double maxBalance){

        return accountRepository.findByBalanceLessThanEqualOrderByBalanceDesc(maxBalance);
    }

    public List getRangedAccounts(double minBalance, double maxBalance){

        return accountRepository.findByBalanceBetweenOrderByBalanceDesc(minBalance,maxBalance);
    }

    public List<Account> getAccountByUserName(String name){

        return accountRepository.findByOwnerNameContainingIgnoreCaseOrderByOwnerNameAsc(name);
    }

    public Account createAccount(Account newAccount){
        accountRepository.save(newAccount);
        return newAccount;
    }

    public String updateAccount(String accNum, Account updatedAccount){

//        for(Account currentAccount : accounts){
//            if(currentAccount.getAccountNumber().equalsIgnoreCase(accNum)){
//                currentAccount.setOwnerName(updatedAccount.getOwnerName());
//                currentAccount.setBalance(updatedAccount.getBalance());
//
//                return "Account " + currentAccount.getAccountNumber() + " Successfully updated.";
//            }
//        }

        if (accountRepository.findByAccountNumberIgnoreCase(accNum).get() != null){
            accountRepository.findByAccountNumberIgnoreCase(accNum).get().setOwnerName(updatedAccount.getOwnerName());
            accountRepository.findByAccountNumberIgnoreCase(accNum).get().setBalance(updatedAccount.getBalance());

            accountRepository.save(accountRepository.findByAccountNumberIgnoreCase(accNum).get());

            return "Accoun Successfully updated." + "new name set to: " + accountRepository.findByAccountNumberIgnoreCase(accNum).get().getOwnerName() + " and new balance: " + accountRepository.findByAccountNumberIgnoreCase(accNum).get().getBalance();
        }

        return "Account not found!";
    }

    public List<String> getAccountStatement(String accNum) {
        if (accountRepository.findByAccountNumberIgnoreCase(accNum).isPresent()){
            return accountRepository.findByAccountNumberIgnoreCase(accNum).get().getTransactions();
        } else {
            return null;
        }
    }
}
