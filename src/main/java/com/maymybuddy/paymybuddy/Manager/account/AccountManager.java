package com.maymybuddy.paymybuddy.Manager.account;

import com.maymybuddy.paymybuddy.Entity.Account;

public interface AccountManager {
    Account findDefaultAccount(int userId);
    int save(Account account);
}
