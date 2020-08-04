package com.maymybuddy.paymybuddy.integrations;

import com.maymybuddy.paymybuddy.Entity.Transfer;
import com.maymybuddy.paymybuddy.Manager.transfer.TransferManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransferManagerIT {
    @Autowired
    TransferManager transferManager;

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
    public void findBySenderFromUserSessionTest(){
        List<Transfer> transfers = transferManager.findBySenderFromUserSession();
        assertNotNull(transfers.get(0));
    }

    @Test
    public void saveTest(){
        Transfer transfer = new Transfer();
        transfer.setAmount(100);
        transfer.setUser(13);
        transfer.setDescription("Restaurant Pay");
        assertTrue(transferManager.save(transfer));
    }
}
