package cs.cs489.project.airlinebookingsystem.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class CodeUtil {

    private static final String SALT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static String buildRandomCode() {
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) {
            int index = rnd.nextInt(SALT_CHARS.length());
            salt.append(SALT_CHARS.charAt(index));
        }
        return salt.toString();
    }
}
