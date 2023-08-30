package com.munywele.cards.service;

import com.munywele.cards.dto.NewCardRequest;
import com.munywele.cards.dto.CardResponse;
import com.munywele.cards.dto.UserResponse;
import com.munywele.cards.enums.EnumCardStatus;
import com.munywele.cards.enums.EnumJwtClaims;
import com.munywele.cards.enums.EnumUserRole;
import com.munywele.cards.model.CardEntity;
import com.munywele.cards.model.UserEntity;
import com.munywele.cards.repository.CardRepository;
import com.munywele.cards.repository.UserRepository;
import com.munywele.cards.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

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

        CardEntity card = cardRepo.findById(cardId).orElseThrow(() -> new Exception("Card not dound"));
        return modelMapper.map(card, CardResponse.class);
    }

    public Page<CardResponse> listAllCards(Pageable pageable, HttpServletRequest request) {
        Page<CardEntity> cardEntities = null;
        //check user role
        String jwtToken = jwtUtils.parseJwtFromHeader(request);
        Long userid = getAuthenticatedUserId(request);
        String role = jwtUtils.getClaim(jwtToken, EnumJwtClaims.ROLE).asString();

        if (Objects.equals(role, EnumUserRole.MEMBER.getRoleName())) {
            //filter out cards using the user id
            cardEntities = cardRepo.findAllByUserId(userid, pageable);
        } else {
            cardEntities = cardRepo.findAll(pageable);
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


    private CardResponse convertToCardResponse(CardEntity cardEntity) {
        return modelMapper.map(cardEntity, CardResponse.class);
    }

    private Long getAuthenticatedUserId(HttpServletRequest request) {
        String jwtToken = jwtUtils.parseJwtFromHeader(request);
        return jwtUtils.getClaim(jwtToken, EnumJwtClaims.USER_ID).asLong();
    }
}
