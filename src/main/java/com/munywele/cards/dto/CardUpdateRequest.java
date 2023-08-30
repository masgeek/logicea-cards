package com.munywele.cards.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.munywele.cards.enums.EnumCardStatus;
import com.munywele.cards.validators.annotations.ValidHexColor;

public class CardUpdateRequest {
    @JsonProperty("name")
    private String cardName;
    @JsonProperty("color")
    @ValidHexColor(required = false)
    private String cardColor;

    @JsonProperty("description")
    private String cardDescription;

    @JsonProperty("status")
    private EnumCardStatus cardStatus;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardColor() {
        return cardColor;
    }

    public void setCardColor(String cardColor) {
        this.cardColor = cardColor;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public EnumCardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(EnumCardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }
}
