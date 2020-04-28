package br.com.alura.edigi.validators;

public class ValidationUtils {

    public static boolean isNull(String value){
        if(value.isBlank()) return true;

        return false;
    }

    public static boolean isValidEmail(String email){
        if(email.matches("^([\\w-]\\.?)+@([\\w-]+\\.)+([A-Za-z]{2,4})+$")) return true;

        return false;
    }
}