package com.payment.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String transactionId;
    @Column(unique = true)
    private String orderId;
    private LocalDateTime dateTime;
    private String transactionType;
    private String status;
    private BigDecimal transactionAmount;
    @ManyToOne
    @JoinColumn(name = "from_wallet_id")
    private Wallet fromWallet;
    @ManyToOne
    @JoinColumn(name = "to_wallet_id")
    private Wallet toWallet;
}
