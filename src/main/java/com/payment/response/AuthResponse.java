package com.payment.response;

import java.util.HashMap;


public class AuthResponse {

    private String message;
    private HashMap<String, Object> objResponse = new HashMap<>();
    private Boolean status;
    private String token;

    public AuthResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<String, Object> getObjResponse() {
        return objResponse;
    }

    public void setObjResponse(HashMap<String, Object> objResponse) {
        this.objResponse = objResponse;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "message='" + message + '\'' +
                ", objResponse=" + objResponse +
                ", status=" + status +
                ", token='" + token + '\'' +
                '}';
    }
}


