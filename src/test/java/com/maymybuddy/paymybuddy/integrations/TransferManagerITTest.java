package com.maymybuddy.paymybuddy.integrations;

import com.maymybuddy.paymybuddy.Entity.Transfer;
import com.maymybuddy.paymybuddy.Manager.transfer.TransferManager;
import com.maymybuddy.paymybuddy.Repository.TransferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(locations="classpath:application.test.properties")
public class TransferManagerITTest {
    @Autowired
    TransferManager transferManager;

    @Mock
    TransferRepository transferRepository;

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
        assertTrue(transfers.size() > 0 );
    }

    @Test
    public void saveWithSuccessTest(){
        Transfer transfer = new Transfer();
        transfer.setAmount(100);
        transfer.setUser(13);
        transfer.setDescription("Restaurant Pay");
        assertTrue(transferManager.save(transfer));
    }

    @Test
    public void saveWithErrorForUserInavlidTest(){
        Transfer transfer = new Transfer();
        transfer.setAmount(100);
        transfer.setUser(50);
        transfer.setDescription("Restaurant Pay");
        assertThrows(EmptyResultDataAccessException.class, () -> transferManager.save(transfer));
    }

    @Test
    public void saveWithErrorForTest(){
        when(transferRepository.save(any(Transfer.class))).thenReturn(0);
        Transfer transfer = new Transfer();
        transfer.setAmount(100);
        transfer.setUser(13);
        transfer.setDescription("Restaurant Pay");
        assertTrue(transferManager.save(transfer));
    }
}
