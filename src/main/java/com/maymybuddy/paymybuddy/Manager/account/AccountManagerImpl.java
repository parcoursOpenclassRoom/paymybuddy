package com.maymybuddy.paymybuddy.Manager.account;

import com.maymybuddy.paymybuddy.Entity.Account;
import com.maymybuddy.paymybuddy.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountManagerImpl implements AccountManager {
    @Autowired
    AccountRepository accountRepository;
    @Override
    public Account findDefaultAccount(int userId) {
        return accountRepository.findDefaultAccount(userId);
    }

    @Override
    public int save(Account account) {
        return accountRepository.save(account);
    }
}
