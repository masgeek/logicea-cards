package com.munywele.cards.service;

import com.munywele.cards.dto.CardUpdateRequest;
import com.munywele.cards.dto.NewCardRequest;
import com.munywele.cards.dto.CardResponse;
import com.munywele.cards.enums.EnumCardStatus;
import com.munywele.cards.enums.EnumJwtClaims;
import com.munywele.cards.enums.EnumUserRole;
import com.munywele.cards.model.CardEntity;
import com.munywele.cards.repository.CardRepository;
import com.munywele.cards.repository.UserRepository;
import com.munywele.cards.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CardService {

    private final CardRepository cardRepo;
    private final UserRepository userRepo;
    private final JwtUtils jwtUtils;

    ModelMapper modelMapper = new ModelMapper();

    public CardService(CardRepository cardRepo, UserRepository userRepo, JwtUtils jwtUtils) {
        this.cardRepo = cardRepo;
        this.userRepo = userRepo;
        this.jwtUtils = jwtUtils;
    }

    public CardResponse getSingleCard(Long cardId, HttpServletRequest request) throws Exception {
        Long userid = getAuthenticatedUserId(request);
        CardEntity card = cardRepo.findByIdAndUserId(cardId, userid).orElseThrow(() -> new Exception("Card not found"));
        return modelMapper.map(card, CardResponse.class);
    }

    public CardResponse updateCard(Long cardId, CardUpdateRequest cardUpdateRequest, HttpServletRequest request) throws Exception {
        Long userid = getAuthenticatedUserId(request);
        CardEntity card = cardRepo.findByIdAndUserId(cardId, userid).orElseThrow(() -> new Exception("Card not found"));
        modelMapper.map(cardUpdateRequest, card);
        CardEntity saved = cardRepo.save(card);
        return modelMapper.map(saved, CardResponse.class);
    }

    public Page<CardResponse> listAllCards(int pageNumber, int pageSize, String cardName, String cardColor, EnumCardStatus cardStatus, String createdAt, String sortField, String sortOrder, HttpServletRequest request) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortField);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<CardEntity> cardEntities;
        //check user role
        String jwtToken = jwtUtils.parseJwtFromHeader(request);
        Long userId = getAuthenticatedUserId(request);
        String role = jwtUtils.getClaim(jwtToken, EnumJwtClaims.ROLE).asString();

        LocalDateTime startDate = jwtUtils.convertToLocalDateViaInstant(createdAt);

        if (Objects.equals(role, EnumUserRole.ADMIN.getRoleName())) {
            cardEntities = cardRepo.searchCards(cardName, cardColor, cardStatus, startDate, pageable);
        } else {
            cardEntities = cardRepo.searchCards(userId, cardName, cardColor, cardStatus, startDate, pageable);
        }
        return cardEntities.map(this::convertToCardResponse);
    }

    public CardResponse addCard(NewCardRequest newCardRequest, HttpServletRequest request) {
        Long userId = getAuthenticatedUserId(request);

        CardEntity cardEntity = modelMapper.map(newCardRequest, CardEntity.class);

        cardEntity.setUserId(userId);

        cardEntity.setCardStatus(EnumCardStatus.TODO);

        CardEntity savedCard = cardRepo.save(cardEntity);

        return modelMapper.map(savedCard, CardResponse.class);
    }

    public void deleteCard(Long cardId, HttpServletRequest request) throws Exception {
        Long userid = getAuthenticatedUserId(request);
        CardEntity card = cardRepo.findByIdAndUserId(cardId, userid).orElseThrow(() -> new Exception("Card not found"));
        cardRepo.delete(card);
    }


    private CardResponse convertToCardResponse(CardEntity cardEntity) {
        return modelMapper.map(cardEntity, CardResponse.class);
    }

    private Long getAuthenticatedUserId(HttpServletRequest request) {
        String jwtToken = jwtUtils.parseJwtFromHeader(request);
        return jwtUtils.getClaim(jwtToken, EnumJwtClaims.USER_ID).asLong();
    }

}
