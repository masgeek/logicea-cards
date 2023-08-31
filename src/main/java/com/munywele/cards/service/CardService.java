package com.munywele.cards.service;

import com.munywele.cards.dto.CardResponse;
import com.munywele.cards.dto.CardUpdateRequest;
import com.munywele.cards.dto.NewCardRequest;
import com.munywele.cards.enums.EnumCardStatus;
import com.munywele.cards.enums.EnumJwtClaims;
import com.munywele.cards.enums.EnumUserRole;
import com.munywele.cards.model.CardEntity;
import com.munywele.cards.repository.CardRepository;
import com.munywele.cards.repository.UserRepository;
import com.munywele.cards.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
/**
 * The CardService class is used to perform operations related to cards.
 */
public class CardService {

    private final CardRepository cardRepo;
    private final UserRepository userRepo;
    ModelMapper modelMapper = new ModelMapper();

    public CardService(CardRepository cardRepo, UserRepository userRepo) {
        this.cardRepo = cardRepo;
        this.userRepo = userRepo;
    }

    /**
     * The function retrieves a single card based on its ID and the user ID, and returns a mapped
     * response object.
     * 
     * @param cardId The cardId parameter is the unique identifier of the card that we want to
     * retrieve. It is of type Long.
     * @param userId The userId parameter is the unique identifier of the user who owns the card.
     * @return The method is returning a CardResponse object.
     */
    public CardResponse getSingleCard(Long cardId, Long userId) throws Exception {
        CardEntity card = cardRepo.findByIdAndUserId(cardId, userId).orElseThrow(() -> new Exception("Card not found"));
        return modelMapper.map(card, CardResponse.class);
    }

    /**
     * The function updates a card with the provided cardId and cardUpdateRequest for the specified
     * userId and returns the updated card as a CardResponse object.
     * 
     * @param cardId The ID of the card that needs to be updated.
     * @param cardUpdateRequest The `cardUpdateRequest` parameter is an object of type
     * `CardUpdateRequest` which contains the updated information for the card.
     * @param userId The userId parameter is the unique identifier of the user who owns the card.
     * @return The method is returning a CardResponse object.
     */
    public CardResponse updateCard(Long cardId, CardUpdateRequest cardUpdateRequest, Long userId) throws Exception {

        CardEntity card = cardRepo.findByIdAndUserId(cardId, userId).orElseThrow(() -> new Exception("Card not found"));
        modelMapper.map(cardUpdateRequest, card);
        CardEntity saved = cardRepo.save(card);
        return modelMapper.map(saved, CardResponse.class);
    }

    /**
     * The function `listAllCards` retrieves a paginated list of card entities based on various search
     * criteria and converts them into card response objects.
     * 
     * @param userId The ID of the user for whom the cards are being listed.
     * @param role The role parameter is a String that represents the role of the user. It is used to
     * determine whether the user is an admin or not.
     * @param pageNumber The page number of the results you want to retrieve. It starts from 0, so the
     * first page is 0, the second page is 1, and so on.
     * @param pageSize The number of cards to be displayed per page.
     * @param cardName The name of the card to search for.
     * @param cardColor The parameter "cardColor" is a string that represents the color of the card. It
     * is used as a filter to search for cards with a specific color.
     * @param cardStatus The cardStatus parameter is an enumeration type (EnumCardStatus) that
     * represents the status of a card. It can have values such as "ACTIVE", "INACTIVE", "PENDING",
     * etc.
     * @param startDate The start date is the date from which the search for cards should begin. It is
     * used as a filter criteria to retrieve cards created after this date.
     * @param sortField The field by which the cards should be sorted.
     * @param sortOrder The sortOrder parameter is a string that specifies the order in which the
     * results should be sorted. It can have two possible values: "ASC" for ascending order and "DESC"
     * for descending order.
     * @return The method is returning a Page object containing CardResponse objects.
     */
    public Page<CardResponse> listAllCards(
            Long userId, String role, int pageNumber, int pageSize,
            String cardName, String cardColor, EnumCardStatus cardStatus,
            LocalDateTime startDate, String sortField, String sortOrder) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortField);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<CardEntity> cardEntities;

        if (Objects.equals(role, EnumUserRole.ADMIN.getRoleName())) {
            cardEntities = cardRepo.searchCards(cardName, cardColor, cardStatus, startDate, pageable);
        } else {
            cardEntities = cardRepo.searchCards(userId, cardName, cardColor, cardStatus, startDate, pageable);
        }
        return cardEntities.map(this::convertToCardResponse);
    }

    /**
     * The function takes a new card request and a user ID, creates a new card entity, sets the user ID
     * and card status, saves the card entity to the database, and returns the saved card as a
     * response.
     * 
     * @param newCardRequest The new card request object that contains the details of the card to be
     * added.
     * @param userId The ID of the user for whom the card is being added.
     * @return The method is returning a CardResponse object.
     */
    public CardResponse addCard(NewCardRequest newCardRequest, Long userId) {

        CardEntity cardEntity = modelMapper.map(newCardRequest, CardEntity.class);

        cardEntity.setUserId(userId);

        cardEntity.setCardStatus(EnumCardStatus.TODO);

        CardEntity savedCard = cardRepo.save(cardEntity);

        return modelMapper.map(savedCard, CardResponse.class);
    }

    /**
     * The function deletes a card with a specific cardId and userId from the card repository.
     * 
     * @param cardId The ID of the card that needs to be deleted.
     * @param userId The userId parameter is the unique identifier of the user who owns the card.
     */
    public void deleteCard(Long cardId, Long userId) throws Exception {
        CardEntity card = cardRepo.findByIdAndUserId(cardId, userId).orElseThrow(() -> new Exception("Card not found"));
        cardRepo.delete(card);
    }


    /**
     * The function converts a CardEntity object to a CardResponse object using a model mapper.
     * 
     * @param cardEntity The `cardEntity` parameter is an instance of the `CardEntity` class.
     * @return The method is returning a CardResponse object.
     */
    private CardResponse convertToCardResponse(CardEntity cardEntity) {
        return modelMapper.map(cardEntity, CardResponse.class);
    }

}
