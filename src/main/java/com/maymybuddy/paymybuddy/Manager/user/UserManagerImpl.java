package com.maymybuddy.paymybuddy.Manager.user;

import com.maymybuddy.paymybuddy.Entity.User;
import com.maymybuddy.paymybuddy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserManagerImpl implements UserManager {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        return userRepository.findByEmail(user);
    }
}
