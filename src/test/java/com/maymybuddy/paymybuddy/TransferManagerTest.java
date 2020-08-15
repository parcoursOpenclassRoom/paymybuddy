package com.maymybuddy.paymybuddy;

import com.maymybuddy.paymybuddy.Entity.Account;
import com.maymybuddy.paymybuddy.Entity.Transaction;
import com.maymybuddy.paymybuddy.Entity.Transfer;
import com.maymybuddy.paymybuddy.Entity.User;
import com.maymybuddy.paymybuddy.Manager.account.AccountManager;
import com.maymybuddy.paymybuddy.Manager.transfer.TransferManagerImpl;
import com.maymybuddy.paymybuddy.Manager.user.UserManager;
import com.maymybuddy.paymybuddy.Repository.TransactionRepository;
import com.maymybuddy.paymybuddy.Repository.TransferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransferManagerTest {
    @Mock
    AccountManager accountManager = mock(AccountManager.class);

    @Mock
    UserManager userManager;
    @Mock
    TransactionRepository transactionRepository;
    @Mock
    TransferRepository transferRepository;

    @InjectMocks
    private final TransferManagerImpl transferManager = new TransferManagerImpl();

    User userRecevied = new User();
    Account recevied = new Account();
    Account sender = new Account();

    @BeforeEach
    public void setupTest(){
        // account recevied
        userRecevied.setId(1);
        recevied.setBalance(0);
        recevied.setDefaultAccount(true);

        // account sender and user
        User userSender = new User();
        userSender.setId(1);
        sender.setBalance(200);
        sender.setDefaultAccount(true);

        when(userManager.findUserSession()).thenReturn(userSender);
        when(accountManager.findDefaultAccount(userSender.getId())).thenReturn(sender);
        when(accountManager.findDefaultAccount(userRecevied.getId())).thenReturn(recevied);

        when(accountManager.save(any(Account.class))).thenReturn(1);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(1);
    }

    @Test
    public void saveWithSuccessTest(){
        when(transferRepository.save(any(Transfer.class))).thenReturn(1);
        // new transfer
        Transfer transfer = new Transfer();
        transfer.setReceiver(recevied);
        transfer.setAmount(100);
        transfer.setDescription("send monney test");
        transfer.setSender(sender);
        transfer.setUser(userRecevied.getId());
        boolean result = transferManager.save(transfer);
        assertTrue(result);
    }

    @Test
    public void saveWithErrorTest(){
        when(transferRepository.save(any(Transfer.class))).thenReturn(0);
        // new transfer
        Transfer transfer = new Transfer();
        transfer.setReceiver(recevied);
        transfer.setAmount(100);
        transfer.setDescription("send monney test");
        transfer.setSender(sender);
        transfer.setUser(userRecevied.getId());
        boolean result = transferManager.save(transfer);
        assertFalse(result);
    }
}
