package com.payment.repository;

import com.payment.model.Wallet;
import com.payment.model.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {

    List<WalletTransaction> findByFromWalletOrToWallet(Wallet fromWallet, Wallet toWallet);
}
