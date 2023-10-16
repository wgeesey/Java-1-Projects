// LambdaTripleMultiplier.java
// W. Geesey
// 4/2/2023
// Implements and tests Multiplier functional interface (SAM).

import java.util.Arrays;
import java.util.ArrayList;

// Interface that returns a multiplication result.
interface Multiplier {

   // multiply num1 * num2 * num3 and return the result.
   double multiply(double num1, double num2, double num3);
   
}

// Class to receive an array of Triple objects, and extract
// the values within as double types.
class Triple {
   private double num1, num2, num3;
 
   public Triple() {}
   public Triple(double num1, double num2, double num3) {
      this.num1 = num1;
      this.num2 = num2;
      this.num3 = num3;
   }
   
   public double getNum1() { return num1; }
   public double getNum2() { return num2; }
   public double getNum3() { return num3; }
}

// Class to create the Triple object array, instantiate 
// an ArrayList<> using the Triple obj. array, and then 
// pass the numbers with the ArrayList to the Lambda for 
// calculation.
public class LambdaTripleMultiplier {

   public static void main(String[] args) {

      Triple[] tArray = { new Triple(2.1, 4.5, 7.8), 
                          new Triple(3.1, 6.3, 9.4), 
                          new Triple(4.2, 7.8, 11.4) };
      ArrayList<Triple> tList = new ArrayList<>(Arrays.asList(tArray));
      
      Multiplier mply = (num1,num2,num3) -> num1 * num2 * num3;
 
      for (Triple triple : tList) {
         System.out.format("Multiply " + triple.getNum1() + " * " + triple.getNum2() +
                           " * " + triple.getNum3() + " = " + "%.2f%n", 
                           mply.multiply(triple.getNum1(), 
                           triple.getNum2(), triple.getNum3()));
      }
   }         
}