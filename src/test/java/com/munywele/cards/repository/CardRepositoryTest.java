package com.munywele.cards.repository;

import com.munywele.cards.dto.CardResponse;
import com.munywele.cards.dto.CardUpdateRequest;
import com.munywele.cards.dto.NewCardRequest;
import com.munywele.cards.enums.EnumCardStatus;
import com.munywele.cards.model.CardEntity;
import com.munywele.cards.service.CardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("junit")
//@Rollback(false)
@ExtendWith(MockitoExtension.class)
class CardRepositoryTest {

    @Mock
    private CardRepository itemRepository;

    @InjectMocks
    private CardService itemService;

    @Test
    void testInsertItem() {
        NewCardRequest updatedItem = new NewCardRequest();
        updatedItem.setCardName("Test");

        // Perform the update
        CardResponse result = itemService.addCard(updatedItem, 1L);

        // Verify that the repository methods were called
        verify(itemRepository, times(1)).save(any(CardEntity.class));

        // Assert the result
        assertNotNull(result);
        assertEquals(updatedItem.getCardName(), result.getCardName());
        assertEquals(updatedItem.getCardName(), result.getCardName());
    }

    void testUpdateItem() throws Exception {
        // Create a sample item
        CardEntity card = new CardEntity();
        card.setId(1L);


        // Mock the behavior of the repository
        when(itemRepository.findById(card.getId())).thenReturn(Optional.of(card));
        when(itemRepository.save(any(CardEntity.class))).thenAnswer(invocation -> invocation.getArgument(0)); // Return the saved item

        // Updated item information
        CardUpdateRequest updatedItem = new CardUpdateRequest();
        updatedItem.setCardName("Test");
        updatedItem.setCardStatus(EnumCardStatus.TODO);

        // Perform the update
        CardResponse result = itemService.updateCard(1L, updatedItem, 3L);

        // Verify that the repository methods were called
        verify(itemRepository, times(1)).findById(card.getId());
        verify(itemRepository, times(1)).save(any(CardEntity.class));

        // Assert the result
        assertNotNull(result);
        assertEquals(updatedItem.getCardName(), result.getCardName());
        assertEquals(updatedItem.getCardStatus(), result.getCardStatus());
    }

}
