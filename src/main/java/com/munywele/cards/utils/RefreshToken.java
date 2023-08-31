package com.munywele.cards.utils;

import java.time.LocalDateTime;

public class RefreshToken {
    private final String username;
    private final String token;
    private final LocalDateTime expiryDate;

    public RefreshToken(String username, String token, LocalDateTime expiryDate) {
        this.username = username;
        this.token = token;
        this.expiryDate = expiryDate;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }
}
