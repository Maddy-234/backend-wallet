package com.payment.controller;

import com.payment.model.User;
import com.payment.model.Wallet;
import com.payment.response.ApiResponse;
import com.payment.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {


    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendAmount(
            @RequestParam("fromAccount") String fromAccount,
            @RequestParam("toAccount") String toAccount,
            @RequestParam("amount") BigDecimal amount) {
        walletService.sendAmount(fromAccount, toAccount, amount);
        return ResponseEntity.ok("Amount sent successfully");
    }

    @PostMapping("/receive")
    public ResponseEntity<String> receiveAmount(
            @RequestParam("toAccount") String toAccount,
            @RequestParam("amount") BigDecimal amount) {
        walletService.receiveAmount(toAccount, amount);
        return ResponseEntity.ok("Amount received successfully");
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Wallet> getWalletDetails(@PathVariable String accountNumber) {
        Wallet wallet = walletService.getWalletDetails(accountNumber);
        return ResponseEntity.ok(wallet);
    }

//    @PostMapping
//    public ResponseEntity<ApiResponse> createDefaultConfig(){
//
//    };
}

