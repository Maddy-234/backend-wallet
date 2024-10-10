package com.payment.service.impl;

import com.payment.model.Wallet;
import com.payment.model.WalletTransaction;
import com.payment.repository.WalletRepository;
import com.payment.repository.WalletTransactionRepository;
import com.payment.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletTransactionRepository walletTransactionRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, WalletTransactionRepository walletTransactionRepository) {
        this.walletRepository = walletRepository;
        this.walletTransactionRepository = walletTransactionRepository;
    }

    @Transactional
    @Override
    public void sendAmount(String fromAccountNo, String toAccountNo, BigDecimal amount) {
        Wallet fromWallet = walletRepository.findByAccountNumber(fromAccountNo)
                .orElseThrow(() -> new RuntimeException("Sender Wallet not found"));

        Wallet toWallet = walletRepository.findByAccountNumber(toAccountNo)
                .orElseThrow(() -> new RuntimeException("Receiver Wallet not found"));

        //Update Balance
        if (fromWallet.getClosingBal().compareTo(amount) < 0){
            throw new RuntimeException("Insufficient balance");
        }

        fromWallet.setClosingBal(fromWallet.getClosingBal().subtract(amount));
        toWallet.setClosingBal(toWallet.getClosingBal().add(amount));

        //Save Transaction
        WalletTransaction transaction = new WalletTransaction();
        transaction.setFromWallet(fromWallet);
        transaction.setToWallet(toWallet);
        transaction.setTransactionAmount(amount);
        transaction.setDateTime(LocalDateTime.now());
        transaction.setTransactionType("SEND");
        transaction.setStatus("Success");
        transaction.setTransactionId(generateTransactionId());

        walletTransactionRepository.save(transaction);
        walletRepository.save(fromWallet);
        walletRepository.save(toWallet);
    }

    @Override
    public void receiveAmount(String toAccountNo, BigDecimal amount) {
        Wallet toWallet = walletRepository.findByAccountNumber(toAccountNo)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        //Update Balance
        toWallet.setClosingBal(toWallet.getClosingBal().add(amount));

        //Save Transaction
        WalletTransaction transaction = new WalletTransaction();
        transaction.setToWallet(toWallet);
        transaction.setTransactionAmount(amount);
        transaction.setDateTime(LocalDateTime.now());
        transaction.setTransactionType("RECEIVE");
        transaction.setStatus("Success");
        transaction.setTransactionId(generateTransactionId());

        walletTransactionRepository.save(transaction);
        walletRepository.save(toWallet);
    }

    @Override
    public Wallet getWalletDetails(String accountNumber) {
        return walletRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new RuntimeException("Wallet not exist"));
    }

    private String generateTransactionId() {
        return "TXN-" + System.currentTimeMillis();
    }
}
