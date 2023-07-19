import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static final long[][] CARD_ISSUER_RANGES = {
            {4000000000000000L, 4999999999999999L},    // Visa
            {5100000000000000L, 5599999999999999L},    // Mastercard
            {340000000000000L, 379999999999999L},     // American Express
            {6011000000000000L, 6011999999999999L}     // Discover
    };

    private static final String[] ISSUERS = {
            "Visa", "Mastercard", "American Express", "Discover"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hi, welcome to my credit card validator!");

        long userCreditCardInput = 0;
        boolean isValidInput = false;

        // To validate that the user's input is a long number.
        while (!isValidInput) {
           System.out.print("Enter the credit card number you want to validate: ");
           if (scanner.hasNextLong()) {
               userCreditCardInput = scanner.nextLong();
               isValidInput = true;
           } else {
               System.out.println("Invalid input. Please enter a long number.");
               scanner.nextLine();
           }
        }

        System.out.println("Your input was: " + userCreditCardInput);
        scanner.close();

        if (isValid(userCreditCardInput)) {
            cardIssuerCheck(userCreditCardInput);
        }

    }

    public static boolean isValid(long number) {
        // Check the length and algorithm.
        if (lengthCheck(number) && luhnAlgorithmCheck(number)) {
            System.out.println("The credit card number: " + number + " is valid!");
            return true;
        } else {
            System.out.println("The credit card number is invalid.");
            return false;
        }
    }

    public static void cardIssuerCheck(long number) {
        // Checking for which issuer the user's credit card number is from.
        for (int i = 0; i < ISSUERS.length; i++) {
            long lowerBound = CARD_ISSUER_RANGES[i][0];
            long upperBound = CARD_ISSUER_RANGES[i][1];
            if (number >= lowerBound && number <= upperBound) {
                System.out.println("Your card issuer is: " + ISSUERS[i]);
            }
        }
    }

    public static boolean lengthCheck(long number) {
        String numberInString = String.valueOf(number);
        int length = numberInString.length();

        return length >= 13 && length <= 16; // Credit card number has to be between 13 and 16 digits long.
    }

    public static boolean luhnAlgorithmCheck(long number) {

        // Storing the credit card's digits into an ArrayList.
        ArrayList<Integer> creditCardDigits = new ArrayList<>();
        while (number > 0) {
            int digit = (int) (number % 10); // Retrieving the last digit of the credit card number.
            creditCardDigits.add(0,digit); // Taking that last digit, and storing it into the ArrayList.
            number /= 10; // Removing the last digit from the original credit card number.
        }

        // Iterating through ArrayList, and applying the Luhn Algorithm's requirements.
        int creditCardDigitsLength = creditCardDigits.size();
        switch (creditCardDigitsLength) {
            case 14, 16 -> { // 14 or 16 digit long numbers.
                for (int i = 0; i < creditCardDigits.size(); i++) {
                    if (i % 2 == 0) { // For even-numbered indexes.
                        int tempNumber = creditCardDigits.get(i);
                        if (tempNumber <= 4) {
                            creditCardDigits.set(i, tempNumber * 2);
                        } else {
                            tempNumber *= 2;
                            int anotherTempNumber = tempNumber % 10; // Gets the last digit
                            tempNumber /= 10; // Gets the first digit
                            tempNumber += anotherTempNumber; // Finds the sum of both digits
                            creditCardDigits.set(i, tempNumber);
                        }
                    }
                }
            }
            case 13, 15 -> { // 13 or 15 digit long numbers.
                for (int i = 0; i < creditCardDigits.size(); i++) {
                    if (i % 2 != 0) { // For odd-numbered indexes.
                        int tempNumber = creditCardDigits.get(i);
                        if (tempNumber <= 4) {
                            creditCardDigits.set(i, tempNumber * 2);
                        } else {
                            tempNumber *= 2;
                            int anotherTempNumber = tempNumber % 10; // Gets the last digit
                            tempNumber /= 10; // Gets the first digit
                            tempNumber += anotherTempNumber; // Finds the sum of both digits
                            creditCardDigits.set(i, tempNumber);
                        }
                    }
                }
            }
        }
        // Just to see the output, to ensure the algorithm worked as intended.
        // System.out.println(creditCardDigits);

        // Getting the sum of all the digits, and ensuring they are divisible by 10.
        int sum = 0;
        for (int digit : creditCardDigits) {
            sum += digit;
        }
        // Check
        // System.out.println(sum);
        return sum % 10 == 0;
    }
}