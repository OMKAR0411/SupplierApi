package com.example.supplier.Service;

public class InputSanitizer {
        public static String sanitize(String input) {
            if (input == null) {
                return null;
            }
            // Remove potentially harmful characters
            return input.replaceAll("[^a-zA-Z0-9 ]", "").trim();
    }

}
