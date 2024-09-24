package utils;


import java.util.regex.Pattern;

public class InputValidator {

    public static boolean validatePhoneNumber(String telephone) {
        String regex = "^0\\d{9}$";
        return Pattern.matches(regex, telephone);
    }

    public static boolean validateRemise(float remise) {
        return remise >= 0 && remise <= 100;
    }

    public static boolean validateDuree(int duree) {
        return duree > 0;
    }

    public static boolean validateNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean validateStatus(String status) {
        return status.equalsIgnoreCase("accepter") || status.equalsIgnoreCase("refuser");
    }




}

