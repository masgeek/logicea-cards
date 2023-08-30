package com.munywele.cards.controller;

import com.munywele.cards.dto.NewCardRequest;
import com.munywele.cards.dto.CardResponse;
import com.munywele.cards.service.CardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/cards")
@RestController
@EnableMethodSecurity
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<CardResponse>> listCards(Pageable pageable) {
        Page<CardResponse> cardResponses = cardService.listAllCards(pageable);
        return ResponseEntity.ok(cardResponses);
    }

    @PostMapping
    public ResponseEntity<CardResponse> addNewCard(@Valid @RequestBody NewCardRequest newCardRequest, HttpServletRequest request) {
        CardResponse cardResponses = cardService.addCard(newCardRequest,request);
        return ResponseEntity.ok(cardResponses);
    }
}
