package com.munywele.cards.utils;

;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorValidator {

    private ColorValidator() {
    }

    public static boolean isValidHexColor(String hexColor) {
        if (hexColor == null) {
            return false;
        }
        // Regular expression for validating hex color codes with a maximum length of 8
        String regex = "^#([0-9a-fA-F]{6}|[0-9a-fA-F]{8})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(hexColor);
        return matcher.matches();
    }
}
