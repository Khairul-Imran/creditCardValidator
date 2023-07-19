# creditCardValidator

This Credit Card Validator is a Java Application that validates credit card numbers and identifies the card's issuing company.

## Introduction
This project is a simple credit card validator designed to verify the authenticity of credit card numbers entered by users. It checks whether the provided credit card number is valid based on its **length** and the **Luhn Algorithm**. It also determines the card's **issuing company** (Visa, Mastercard, American Express, Discover) based on its BIN (Bank Identification Number) prefix.

## Features
- Provides validation for the user's input (ensures that a long number is provided).
- Validates credit card number using its length and the Luhn Algorithm.
- Determines the card's issuing company based on its BIN prefix.

## Usage
1. Open the project in your favourite Java IDE (e.g. Intellij IDEA).
2. Locate the 'Main.java' file, which contains the main method.
3. Run the 'Main.java' file to start the application.
4. Enter the credit card number when prompted. Recommended to generate from: [Online fake credit card number generator](https://www.creditcardvalidator.org/generator)
5. The application will display whether the credit card number is valid, along with its issuing company (Visa, Mastercard, American Express, Discover)

## Credit Card Data Format and Luhn Algorithm Implementation
- Credit card Data Format: https://www.groundlabs.com/blog/anatomy-of-a-credit-card/
- Luhn Algorithm: https://www.geeksforgeeks.org/luhn-algorithm/

## Disclaimer
This project is a beginner-level implementation and may not be the most optimal solution. It was created as I was curious how it could be done, and I wanted to practice writing simple programs on my own. As a result, you might encounter certain inefficiencies while using the application.

- The credit card issuing companies available are limited to the four stated above.
- My implementation of the Luhn Algorithm doesn't strictly follow the requirement of starting from the right side of the credit card number, and instead focuses on determining whether the credit card number's length is odd or even. That helps me to determine which indexes need to be multiplied by two without having to worry about the check digit (last digit). This method still achieves the same outcome.
