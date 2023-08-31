package com.munywele.cards.controller;

import com.munywele.cards.dto.CardUpdateRequest;
import com.munywele.cards.dto.NewCardRequest;
import com.munywele.cards.dto.CardResponse;
import com.munywele.cards.enums.EnumCardStatus;
import com.munywele.cards.service.CardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
    public ResponseEntity<CardResponse> singleCard(@PathVariable Long id, HttpServletRequest request) throws Exception {
        CardResponse cardResponses = cardService.getSingleCard(id, request);
        return ResponseEntity.ok(cardResponses);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public ResponseEntity<CardResponse> updateCard(@PathVariable Long id, @Valid @RequestBody CardUpdateRequest cardUpdateRequest, HttpServletRequest request) throws Exception {
        CardResponse cardResponses = cardService.updateCard(id, cardUpdateRequest, request);
        return ResponseEntity.ok(cardResponses);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public ResponseEntity<Page<CardResponse>> listCards(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "50") int pageSize,
            @RequestParam(required = false) String cardName,
            @RequestParam(required = false) String cardColor,
            @RequestParam(required = false) EnumCardStatus cardStatus,
            @RequestParam(required = false) String createdAt,
            @RequestParam(defaultValue = "cardName") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder,
            HttpServletRequest request) {

        Page<CardResponse> cardResponses = cardService.listAllCards(
                pageNumber,
                pageSize,
                cardName,
                cardColor,
                cardStatus,
                createdAt,
                sortField,
                sortOrder,
                request);

        return ResponseEntity.ok(cardResponses);
    }

    @PostMapping
    public ResponseEntity<CardResponse> addNewCard(@Valid @RequestBody NewCardRequest newCardRequest, HttpServletRequest request) {
        CardResponse cardResponses = cardService.addCard(newCardRequest, request);
        return ResponseEntity.ok(cardResponses);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public ResponseEntity<Object> updateCard(@PathVariable Long id, HttpServletRequest request) throws Exception {
        cardService.deleteCard(id, request);
        return ResponseEntity.noContent().build();
    }

}
