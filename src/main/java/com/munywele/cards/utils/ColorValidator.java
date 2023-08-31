package com.munywele.cards.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The ColorValidator class provides a method to validate whether a given string represents a valid
 * hexadecimal color code.
 */
public class ColorValidator {

    private ColorValidator() {
    }

    /**
     * The function checks if a given string is a valid hexadecimal color code.
     * 
     * @param hexColor The hexColor parameter is a string representing a hexadecimal color code.
     * @return The method is returning a boolean value. It returns true if the hexColor parameter is a
     * valid hex color code (either 6 or 8 characters long, starting with a '#' symbol), and false
     * otherwise.
     */
    public static boolean isValidHexColor(String hexColor) {
        if (hexColor == null || hexColor.isEmpty()) {
            return false;
        }
        // Regular expression for validating hex color codes with a maximum length of 8
        String regex = "^#([0-9a-fA-F]{6}|[0-9a-fA-F]{8})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(hexColor);
        return matcher.matches();
    }
}
