package com.capitec.atm.repository;
import com.capitec.atm.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{
    // The two generics tell Spring:  public interface AccountRepository extends JpaRepository<Account, String>
    //Account: which entity (table) this repository manages
    //String: the type of that entity’s @Id
    List<Account> findByBalanceLessThanEqualOrderByBalanceDesc(double maxBalance);
    List<Account> findByBalanceBetweenOrderByBalanceDesc(double minBalance, double maxBalance);
    Optional<Account> findByAccountNumberIgnoreCase(String accountNumber);
    List<Account> findByBalanceGreaterThanOrderByBalanceDesc(double min);   // ... ORDER BY balance DESC
    List<Account> findTop3ByOrderByBalanceDesc();                           // 3 richest, 0 parameters
    List<Account> findByOwnerNameContainingIgnoreCaseOrderByOwnerNameAsc(String name);
}
