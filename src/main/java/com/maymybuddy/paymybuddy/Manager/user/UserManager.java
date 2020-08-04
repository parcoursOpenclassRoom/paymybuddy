package com.maymybuddy.paymybuddy.Manager.user;

import com.maymybuddy.paymybuddy.Entity.User;

import java.util.List;

public interface UserManager {
    User findByEmail(String email);
    User findById(int id);
    List<User> findUserByConnectionFromUserSession();
    User findUserSession();
}
