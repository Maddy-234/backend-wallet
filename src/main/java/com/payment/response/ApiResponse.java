package com.payment.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
public class ApiResponse {

    private String message;
    private HashMap<String, Object> objResponse = new HashMap<>();
    private Boolean status;
}
