package com.payment.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wallet")
@Data
@NoArgsConstructor
public class Wallet extends Utils{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private BigDecimal openingBal;
    private BigDecimal closingBal;
    private Boolean active;
    @OneToMany(mappedBy = "fromWallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalletTransaction> outgoingTransaction = new ArrayList<>();
    @OneToMany(mappedBy = "toWallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalletTransaction> incomingTransaction = new ArrayList<>();


    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", openingBal=" + openingBal +
                ", closingBal=" + closingBal +
                ", active=" + active +
                ", outgoingTransaction=" + outgoingTransaction +
                ", incomingTransaction=" + incomingTransaction +
                '}';
    }
}
