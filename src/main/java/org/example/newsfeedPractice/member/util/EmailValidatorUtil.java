package org.example.newsfeedPractice.member.util;

import java.util.regex.Pattern;

public class EmailValidatorUtil {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
    );

    public static boolean isValid(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
