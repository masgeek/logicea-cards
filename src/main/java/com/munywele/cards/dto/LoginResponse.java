package com.munywele.cards.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record LoginResponse(@JsonProperty("bearerToken") String bearerToken) {
}
