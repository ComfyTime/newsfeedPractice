package org.example.newsfeedPractice.member.util;

public class PasswordValidatorUtil {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{10,}$";

    public static boolean isValid(String password) {
        if (password == null) return false;
        return password.matches(PASSWORD_PATTERN);
    }
}
