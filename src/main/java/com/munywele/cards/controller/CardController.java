package com.munywele.cards.controller;

import com.munywele.cards.dto.CardResponse;
import com.munywele.cards.dto.CardUpdateRequest;
import com.munywele.cards.dto.NewCardRequest;
import com.munywele.cards.enums.EnumCardStatus;
import com.munywele.cards.enums.EnumJwtClaims;
import com.munywele.cards.service.CardService;
import com.munywele.cards.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequestMapping("api/v1/cards")
@RestController
@EnableMethodSecurity
public class CardController {
    private final CardService cardService;
    private final JwtUtils jwtUtils;


    public CardController(CardService cardService, JwtUtils jwtUtils) {
        this.cardService = cardService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public ResponseEntity<CardResponse> singleCard(@PathVariable Long id, HttpServletRequest request) throws Exception {
        Long userId = jwtUtils.getAuthenticatedUserId(request);
        CardResponse cardResponses = cardService.getSingleCard(id, userId);
        return ResponseEntity.ok(cardResponses);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public ResponseEntity<CardResponse> updateCard(@PathVariable Long id, @Valid @RequestBody CardUpdateRequest cardUpdateRequest, HttpServletRequest request) throws Exception {
        Long userid = jwtUtils.getAuthenticatedUserId(request);
        CardResponse cardResponses = cardService.updateCard(id, cardUpdateRequest, userid);
        return ResponseEntity.ok(cardResponses);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public ResponseEntity<Page<CardResponse>> listCards(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "50") int pageSize, @RequestParam(required = false) String cardName, @RequestParam(required = false) String cardColor, @RequestParam(required = false) EnumCardStatus cardStatus, @RequestParam(required = false) String createdAt, @RequestParam(defaultValue = "cardName") String sortField, @RequestParam(defaultValue = "asc") String sortOrder, HttpServletRequest request) {

        //check user role
        String jwtToken = jwtUtils.parseJwtFromHeader(request);
        Long userId = jwtUtils.getAuthenticatedUserId(request);
        String role = jwtUtils.getClaim(jwtToken, EnumJwtClaims.ROLE).asString();

        LocalDateTime startDate = jwtUtils.convertToLocalDateViaInstant(createdAt);


        Page<CardResponse> cardResponses = cardService.listAllCards(userId, role, pageNumber, pageSize,
                cardName, cardColor, cardStatus,
                startDate, sortField, sortOrder);

        return ResponseEntity.ok(cardResponses);
    }

    @PostMapping
    public ResponseEntity<CardResponse> addNewCard(@Valid @RequestBody NewCardRequest newCardRequest, HttpServletRequest request) {
        Long userId = jwtUtils.getAuthenticatedUserId(request);
        CardResponse cardResponses = cardService.addCard(newCardRequest, userId);
        return ResponseEntity.ok(cardResponses);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    public ResponseEntity<Object> updateCard(@PathVariable Long id, HttpServletRequest request) throws Exception {
        Long userId = jwtUtils.getAuthenticatedUserId(request);
        cardService.deleteCard(id, userId);
        return ResponseEntity.noContent().build();
    }

}
