package com.munywele.cards.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

/**
 * The MyDateUtils class provides methods to convert a date string or a Date object to a LocalDateTime
 * object.
 */
public class MyDateUtils {

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    public LocalDateTime convertToLocalDateViaInstant(String dateStringToConvert) {
        try {
            Date date = formatter.parse(dateStringToConvert);
            return convertToLocalDateViaInstant(date);
        } catch (Exception ex) {
            //handle format exception
        }
        return null;
    }

    public LocalDateTime convertToLocalDateViaInstant(Date dateToConvert) {
        if (dateToConvert != null) {
            return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
        return null;
    }

}
