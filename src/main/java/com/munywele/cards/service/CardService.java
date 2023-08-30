package com.munywele.cards.service;

import com.munywele.cards.dto.NewCardRequest;
import com.munywele.cards.dto.CardResponse;
import com.munywele.cards.dto.UserResponse;
import com.munywele.cards.enums.EnumCardStatus;
import com.munywele.cards.enums.EnumJwtClaims;
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

    public Page<CardResponse> listAllCards(Pageable pageable) {

        Page<CardEntity> cardEntities = cardRepo.findAll(pageable);
        return cardEntities.map(this::convertToCardResponse);

    }

    public CardResponse addCard(NewCardRequest newCardRequest, HttpServletRequest request) {

        String jwtToken = jwtUtils.parseJwtFromHeader(request);
        Long userId = jwtUtils.getClaim(jwtToken, EnumJwtClaims.USER_ID).asLong();


        CardEntity cardEntity = modelMapper.map(newCardRequest, CardEntity.class);

        cardEntity.setUserId(userId);

        cardEntity.setCardStatus(EnumCardStatus.TODO);

        CardEntity savedCard = cardRepo.save(cardEntity);

        return modelMapper.map(savedCard, CardResponse.class);
    }


    private CardResponse convertToCardResponse(CardEntity cardEntity) {
        CardResponse response = modelMapper.map(cardEntity, CardResponse.class);


        Optional<UserEntity> userEntity = userRepo.findById(cardEntity.getUserId());
        if (userEntity.isPresent()) {
            response.setCardOwner(modelMapper.map(userEntity, UserResponse.class));
        }

        return response;
    }

}
