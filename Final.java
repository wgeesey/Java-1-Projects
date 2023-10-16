import java.util.*;
import java.io.*;

public class Final {
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) throws IOException {
        int [] numArray;
        int total;
 
//Call functions to input  numbers.
    numArray = inputNumbers();
//Call function to add numbers
    total = addNums(numArray);
    System.out.println("\nThe total of the numbers is " + total + ".\n");
// Write array contents to a file.
    writeArray(numArray);
//Read and display numbers in the file
    readArray();
    }

    public static int[] inputNumbers() {	
    
    int index = 0, number = 0;
    int[] numArray = new int [5];
    
    	System.out.println("Enter 5 numbers between 1 and 10 (inclusive).");
    	while (index <= 4) {
            System.out.println("Enter your choice for number " + (index + 1)+":");
       	    try {
                number = input.nextInt();
                if (number < 1 || number > 10) {
                    System.out.println("Your number must be in the range of 1 through 10.");
                }
                else {
                    numArray[index] = number;
                    index = index + 1;
                }
            }
            catch (InputMismatchException ex) {
                System.out.println("Your number must be in the range of 1 through 10.");
                input.next();
            }
        }
    return numArray;
    }
 
    public static int addNums(int [] numArray) {
	int numTotal = 0;
	int i = 0;
	for (i = 0; i <=4; i++){
            numTotal = numTotal + numArray[i];
        }
	return numTotal;
    }

    public static void writeArray(int[] numArray) throws IOException {
        int inputNumber;
        int i = 0;

	PrintWriter arrayNumbersFile = new PrintWriter("Exam2.dat");
	
        for (i = 0; i <=4; i++){
            inputNumber = numArray[i];
            arrayNumbersFile.println(inputNumber);
        }
        System.out.println("Writing numbers to a file.....\n");
	arrayNumbersFile.close();
    }

    public static void readArray() throws IOException {
	int aboveFive = 0, fiveOrBelow = 0,arrayNumber = 0;

	
        Scanner numArrayIn = new Scanner(new File("Exam2.dat"));
        System.out.println("Reading numbers from a file.....\n");
    	System.out.println("The numbers in the 'Exam2.dat' file are: ");

	while(numArrayIn.hasNext()) {
		arrayNumber = numArrayIn.nextInt();
		System.out.println(arrayNumber);
                    if (arrayNumber > 5) {
                        aboveFive = aboveFive + 1;
                    }
                    if (arrayNumber <= 5) {
			fiveOrBelow = fiveOrBelow + 1;
                    }
        }
	System.out.println(aboveFive + " of the numbers are greater than 5.");
	System.out.println(fiveOrBelow + " of the numbers are 5 or less.");
	numArrayIn.close();
    }
} 