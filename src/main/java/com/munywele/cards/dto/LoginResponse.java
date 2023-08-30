package com.munywele.cards.dto;

import java.time.LocalDateTime;

public record LoginResponse(String bearerToken, LocalDateTime expiryDate) {
}
