package com.maymybuddy.paymybuddy.Manager.transfer;

import com.maymybuddy.paymybuddy.Entity.Transfer;

import java.util.List;

public interface TransferManager {
    boolean save(Transfer transfer);
    List<Transfer> findBySender(String userEmail);
    List<Transfer> findBySenderFromUserSession();
}
