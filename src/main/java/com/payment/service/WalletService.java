package com.payment.service;

import com.payment.model.Wallet;
import com.payment.model.WalletTransaction;

import java.math.BigDecimal;


public interface WalletService {

    void sendAmount(String fromAccountNo, String toAccountNo, BigDecimal amount);
    void receiveAmount(String toAccountNo, BigDecimal amount);
    Wallet getWalletDetails(String accountNumber);
}
