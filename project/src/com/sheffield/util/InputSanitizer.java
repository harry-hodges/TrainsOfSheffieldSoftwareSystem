package com.sheffield.util;

public class InputSanitizer {
    public static String trimMiddleWhitespaces(String input) {
        if (input == null) {
            return null;
        }
        return input.trim().replaceAll("\\s{2,}", " ");
        // Replace consecutive middle whitespaces with a single space
    }

    public static boolean isInteger(String input) {
        if (input == null) {
            return false;
        }
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isLettersOnly(String input) {
        if (input == null) {
            return false;
        }
        return input.matches("^[a-zA-Z]*$"); // Check if input contains only letters
    }

    public static String sanitizeInput(String input) {
        if (input == null) {
            return null;
        }
        return input.trim(); // Trim leading and trailing whitespaces
    }

    public static String normalizeName(String input) {
        if (input == null || input.isEmpty()) {
            return input; // Return input as-is for null or empty strings
        }

        StringBuilder normalized = new StringBuilder();

        boolean capitalizeNext = true;
        for (char c : input.trim().toCharArray()) {
            if (Character.isWhitespace(c)) {
                capitalizeNext = true;
                normalized.append(c); // Add the whitespace to maintain spaces between parts
            } else if (capitalizeNext) {
                normalized.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                normalized.append(Character.toLowerCase(c));
            }
        }
        return normalized.toString();
    }

    public static void main(String[] args) {
        String userInput = "   some input with spaces   ";
        String sanitizedInput = sanitizeInput(userInput);

        System.out.println("Original input: '" + userInput + "'");
        System.out.println("Sanitized input: '" + sanitizedInput + "'");

        String noSpaces = trimMiddleWhitespaces(userInput);
        System.out.println("Input without spaces: '" + noSpaces + "'");

        String testInteger = "12345";
        String testString = "abcDEF";

        System.out.println("Is '" + testInteger + "' an integer: " + isInteger(testInteger));
        System.out.println("Is '" + testString + "' a string: " + isLettersOnly(testString));

        String nameInput = "joHn DoE"; // Example name input
        String normalizedName = normalizeName(nameInput);
        System.out.println("Normalized name: " + normalizedName);
    }
}

