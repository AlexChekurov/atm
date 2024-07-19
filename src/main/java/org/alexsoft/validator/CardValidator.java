package org.alexsoft.validator;

import java.util.regex.*;

public class CardValidator {
    private static final String CARD_PATTERN = "\\d{4}-\\d{4}-\\d{4}-\\d{4}";

    public static boolean isValidCardNumber(String cardNumber) {
        Pattern pattern = Pattern.compile(CARD_PATTERN);
        Matcher matcher = pattern.matcher(cardNumber);
        return matcher.matches();
    }

    public static boolean isValidPin(String pin) {
        return pin.length() == 4 && pin.chars().allMatch(Character::isDigit);
    }
}

