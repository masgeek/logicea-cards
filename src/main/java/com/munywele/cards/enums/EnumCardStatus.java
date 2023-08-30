package com.munywele.cards.enums;

public enum EnumCardStatus {

    TODO("To Do"), IN_PROGRESS("In Progress"), DONE("Done");

    private final String displayText;

    EnumCardStatus(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
