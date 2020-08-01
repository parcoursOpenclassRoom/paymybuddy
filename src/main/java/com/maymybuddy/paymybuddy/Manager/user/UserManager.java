package com.maymybuddy.paymybuddy.Manager.user;

import com.maymybuddy.paymybuddy.Entity.User;

public interface UserManager {
    User findByEmail(String email);
}
