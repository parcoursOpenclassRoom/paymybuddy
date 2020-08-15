package com.maymybuddy.paymybuddy.integrations;

import com.maymybuddy.paymybuddy.Entity.User;
import com.maymybuddy.paymybuddy.Manager.user.UserManagerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(locations="classpath:application.test.properties")
public class UserManagerITTest {
    @Autowired
    UserManagerImpl userManager;

    @Mock
    SecurityContext securityContext;
    @Mock
    Authentication authentication;
    String email = "aristide.laurent@gmail.com";

    @BeforeEach
    public void setupTest(){
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(email);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void findByEmailTest(){
        User user = userManager.findByEmail(email);
        assertNotNull(user);
    }

    @Test
    public void findByIdTest(){
        int userId = 1;
        User user = userManager.findById(userId);
        assertNotNull(user);
    }

    @Test
    public void findUserByConnectionFromUserSessionTest(){
        List<User> users = userManager.findUserByConnectionFromUserSession();
        assertEquals(4, users.size());
    }

    @Test
    public void findUserSessionTest(){
        User user = userManager.findUserSession();
        assertNotNull(user);
    }
}
