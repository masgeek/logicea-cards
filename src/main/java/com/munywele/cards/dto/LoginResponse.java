package com.munywele.cards.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponse(@JsonProperty("bearerToken") String bearerToken) {
}
