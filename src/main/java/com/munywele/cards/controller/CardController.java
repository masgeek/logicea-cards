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

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public ResponseEntity<CardResponse> singleCard(@PathVariable Long id, HttpServletRequest request) {
        CardResponse cardResponses = cardService.getSingleCard(id, request);
        return ResponseEntity.ok(cardResponses);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public ResponseEntity<Page<CardResponse>> listCards(Pageable pageable, HttpServletRequest request) {
        Page<CardResponse> cardResponses = cardService.listAllCards(pageable, request);
        return ResponseEntity.ok(cardResponses);
    }

    @PostMapping
    public ResponseEntity<CardResponse> addNewCard(@Valid @RequestBody NewCardRequest newCardRequest, HttpServletRequest request) {
        CardResponse cardResponses = cardService.addCard(newCardRequest, request);
        return ResponseEntity.ok(cardResponses);
    }
}
