import java.math.BigDecimal;
import java.math.MathContext;
import java.util.InputMismatchException;
import java.util.Scanner;
public class NaturalLogarithm {
   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       try {
           // Prompt the user to enter a positive number
           System.out.print("Enter a positive number: ");
           BigDecimal number = scanner.nextBigDecimal();
           // Check if the number is positive
           if (number.compareTo(BigDecimal.ZERO) <= 0) {
               throw new IllegalArgumentException("Number must be positive");
           }
           // Set precision for calculations
           MathContext mc = new MathContext(100);
           // Calculate the natural logarithm using the ln method
           BigDecimal result = ln(number, mc);
           System.out.println("Natural logarithm of " + number + " is: " + result);
       } catch (InputMismatchException e) {
           System.err.println("Invalid input. Please enter a valid number."); // Handle invalid input
       } catch (IllegalArgumentException e) {
           System.err.println(e.getMessage()); // Handle negative or zero input
       } catch (Exception e) {
           System.err.println("An unexpected error occurred: " + e.getMessage()); // Handle any other unexpected errors
       } finally {
           scanner.close(); // Ensure the scanner is closed
       }
   }
public static BigDecimal ln(BigDecimal x, MathContext mc) {
       if (x.compareTo(BigDecimal.ZERO) <= 0) {
           throw new IllegalArgumentException("Number must be positive"); // Ensure the number is positive
       }
       BigDecimal result = BigDecimal.ZERO; // Initialize result
       BigDecimal term;
       BigDecimal powerOfXMinusOne = x.subtract(BigDecimal.ONE); // Calculate (x - 1)
       // Use Taylor series expansion to approximate ln(x)
       for (int i = 1; i < mc.getPrecision(); i++) {
           term = powerOfXMinusOne.pow(i).divide(BigDecimal.valueOf(i), mc); // Calculate the ith term
           if (i % 2 == 0) {
               result = result.subtract(term); // Subtract even terms
           } else {
               result = result.add(term); // Add odd terms
           }
       }
       return result; // Return the calculated natural logarithm
   }
}