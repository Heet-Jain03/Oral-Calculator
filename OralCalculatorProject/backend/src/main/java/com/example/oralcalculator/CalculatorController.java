package com.example.oralcalculator;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    @PostMapping("/calculate")
    public String calculate(@RequestBody String input) {
        // Replace symbols with words for output formatting
        String spokenInput = input.replace("+", "plus").replace("-", "minus")
                                  .replace("*", "times").replace("/", "divided");
        
        // Handle calculation based on symbols
        int num1, num2, result = 0;
        String operator;
        
        // Split input based on spaces
        String[] parts = input.split(" ");
        if (parts.length < 3) {
            return "Invalid input format. Expected format: '<number> <operator> <number>'";
        }

        try {
            num1 = Integer.parseInt(parts[0]);
            num2 = Integer.parseInt(parts[2]);
            operator = parts[1];

            // Perform calculation based on the operator
            switch (operator) {
                case "plus":
                    result = num1 + num2;
                    break;
                case "minus":
                    result = num1 - num2;
                    break;
                case "times":
                    result = num1 * num2;
                    break;
                case "divided":
                    result = num1 / num2;
                    break;
                default:
                    return "Invalid operation";
            }
        } catch (NumberFormatException e) {
            return "Invalid number format";
        } catch (ArithmeticException e) {
            return "Error in calculation: " + e.getMessage();
        }
        
        // Return formatted result
        return "You said: " + spokenInput + ".\nResult: " + result;
    }
}
