//This is a program to calculate the fat percentage of a food item based off user 
//input for fat grams and calories.

// ***Added validation for incorrect data type (String input when numerical input is expected*** 
import java.util.*;
import java.text.DecimalFormat;

public class FatGrams {

    public static void main(String[] args) {
        
//Variable to hold information to send to functions, or to call the functions.
        double fatGrams, calories;
        
        fatGrams = getFat();
        calories = getCalories(fatGrams);
        showPercent(fatGrams, calories);
    }

//Function to take user input of fat grams in the product, validate the information so 
//it isn’t negative, and return it to the main module.    
public static double getFat() {
        
        Scanner keyboard = new Scanner(System.in);

//Variable to hold the user input.        
        double fat;
        
//Do-While loop to validate user input and ensure the input is not a negative number. 
        do {
            System.out.println("Please enter the amount of fat grams: ");
        
            while (!keyboard.hasNextDouble())
            {
                System.out.println("Fat grams must be a valid number (greater than 0).");
                System.out.println("Please enter the amount of fat grams: ");
                keyboard.next();
            }
        
        fat = keyboard.nextDouble();
        if (fat < 0)
            System.out.println("Fat grams must be a valid number (greater than 0).");
        }while (fat < 0);
      
        return fat;
    } 

//Function to take user input of calories in the product, validate the information so 
//it isn’t negative or a string, and return it to the main module.
    public static double getCalories(double fatGrams) {
        
        Scanner keyboard = new Scanner(System.in);

//Variable to hold the user input.        
        double calories;
        
//Do-While loop to validate user input and ensure the input is not a negative number or string 
//and that it is not less than the amount of fat grams * 9.
        
        do {
            System.out.println("Please enter the amount of calories: ");
        
            while (!keyboard.hasNextDouble())
            {
              System.out.println("Calories must be a valid number and cannot be less than the number of fat grams * 9.");
              System.out.println("Please enter the amount of calories: ");
              keyboard.next();
            }
        
        calories = keyboard.nextDouble();
        if (calories <0 || calories < fatGrams * 9)
            System.out.println("Calories cannot be less than the number of fat grams * 9.");
        }while (calories < 0 || calories < fatGrams * 9); 
        
        return calories;
        
    }

//Function to take the user input, perform a calculation to determine the fat percentage, 
//and then display the result. If the percentage is below the stated threshold, output an additional message.    
    public static void showPercent(double fatGrams, double calories) {
    
//This is used to format the output of the fat percentage calculation into a percentage. 
//DONT FORGET THE IMPORT!    
        DecimalFormat decFormat = new DecimalFormat("#.#%");
        
//Variable to hold the calculation and result.    
        double fatPercent;
        
        fatPercent = fatGrams * 9 / calories;
        System.out.println("The fat percent for this item is: " + (decFormat.format(fatPercent)));
        
//If loop to check the percentage and if less than the threshold, display an additional message.        
        if (fatPercent < .3) {
            
            // Convert the calculation performed in the fatPercent to a percentage for display to the user.
            System.out.println("This food item is considered Low-fat (fat percent under 30%).");
        }
    }
}
