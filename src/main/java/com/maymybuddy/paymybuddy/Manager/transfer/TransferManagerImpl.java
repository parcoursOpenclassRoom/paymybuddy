package com.maymybuddy.paymybuddy.Manager.transfer;

import com.maymybuddy.paymybuddy.Entity.Account;
import com.maymybuddy.paymybuddy.Entity.Transaction;
import com.maymybuddy.paymybuddy.Entity.Transfer;
import com.maymybuddy.paymybuddy.Entity.User;
import com.maymybuddy.paymybuddy.Manager.account.AccountManager;
import com.maymybuddy.paymybuddy.Manager.consts.TransactionConst;
import com.maymybuddy.paymybuddy.Manager.user.UserManager;
import com.maymybuddy.paymybuddy.Repository.TransactionRepository;
import com.maymybuddy.paymybuddy.Repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransferManagerImpl implements TransferManager {
    @Autowired
    AccountManager accountManager;
    @Autowired
    UserManager userManager;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransferRepository transferRepository;

    @Override
    @Transactional
    public boolean save(Transfer transfer) {

        User user = userManager.findUserSession();
        String descriptionSender = "Transfert de fond ( " + transfer.getDescription()+" )";
        String descriptionReceiver = "Réception de fond ( " + transfer.getDescription()+" )";

        //retrieve transfer information and save
        Account receiver = accountManager.findDefaultAccount(transfer.getUser());
        Account sender = accountManager.findDefaultAccount(user.getId());
        transfer.setStatus(true);
        transfer.setReceiver(receiver);
        transfer.setSender(sender);
        int trans = transferRepository.save(transfer);

        // modify account balances and save
        sender.setBalance(sender.getBalance() - transfer.getAmount());
        receiver.setBalance(receiver.getBalance() + transfer.getAmount());
        accountManager.save(receiver);
        int accountSave = accountManager.save(sender);

        // create a history for the transaction (sender)
        Transaction transSender = new Transaction();
        transSender.setAmount(transfer.getAmount());
        transSender.setType(TransactionConst.DEBIT);
        transSender.setDescription(descriptionSender);
        transSender.setAccount(sender);
        int transacSender = transactionRepository.save(transSender);

        // create a history for the transaction (receiver)
        Transaction transReceiver = new Transaction();
        transReceiver.setAmount(transfer.getAmount());
        transReceiver.setType(TransactionConst.CREDIT);
        transReceiver.setDescription(descriptionReceiver);
        transReceiver.setAccount(receiver);
        int transacReceiver = transactionRepository.save(transReceiver);
        return trans == 1 && accountSave == 1 && transacSender == 1 && transacReceiver == 1 ? true : false;
    }

    @Override
    public List<Transfer> findBySender(String userEmail) {
        return transferRepository.findByUserSender(userEmail);
    }

    @Override
    public List<Transfer> findBySenderFromUserSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return findBySender(authentication.getName());
    }
}
