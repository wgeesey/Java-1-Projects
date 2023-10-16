//This is a program to take input from the user, in a range of 0 through 100, and then place the input 
//into an array. This array will then be used by index to calculate the average of the group, total of the 
//group and identify the highest and lowest numbers of the group.

import java.util.*;

public class ArrayProcessing {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        
//Declare and assign variables. SIZE will be a constant used for the size of the array. Lowest and highest 
//will reference built in Java classes to ease the comparison of the lowest/highest expressions.        
        final int SIZE = 20;
        double[] numberBank = new double[SIZE];
        int index = 0, i;
        double number = 0,total = 0, average = 0;
        double lowest = Double.MAX_VALUE;   //Anything is lower than the MAX_VALUE
        double highest = Double.MIN_VALUE;  //Anything is higher than the MIN_VALUE

//Inform the user of the applicable range of numbers to be used.
        System.out.println("Please provide 20 numbers ranging from 0 through 100.");
        
//While loop to get the input from the user, validate the input (ensure it is within the range and is a 
//double), then assign it to the referenced index and move to the next input. Try-catch implemented in 
//the case of a non-double entry, to avoid the InputMismatchException that will be thrown.        
        while (index <= SIZE - 1) {
            System.out.println("Enter your choice for number " + (index + 1) + ":");
            try {
                number = input.nextDouble();
                if (number < 0 || number > 100) {
                    System.out.println("Your number must be in the range of 0 through 100.");
                }
                else {
                    numberBank[index] = number;
                    index = index + 1;
                }
            }
            catch (InputMismatchException ex) {
                System.out.println("Your number must be in the range of 0 through 100.");
                input.next();
            }
        }
//For loop to traverse the array and identify the lowest and highest numbers then calculate the total of 
//all numbers in the array.        
        for (i = 0; i <= SIZE - 1; i++) {
            if (numberBank[i] < lowest) {
                lowest = numberBank[i];
            }
            if (numberBank[i] > highest) {
                highest = numberBank[i];
            }
            total = total + numberBank[i]; 
        }
//Calculate the average
        average = total / SIZE;
//Call to the showStats function to display the information.        
        showStats(lowest, highest, total, average);
    } 

//Function to receive the arguments passed from the main module, assign those to parameters, then 
//display the information to the user.    
    public static void showStats(double lowest, double highest, double total, double average) {
        System.out.println("The lowest number in the array is: " + lowest);
        System.out.println("The highest number in the array is: " + highest);
        System.out.println("The total of the numbers in the array is: " + total);
        System.out.println("The average of the numbers in the array is: " + average);
    }
}



