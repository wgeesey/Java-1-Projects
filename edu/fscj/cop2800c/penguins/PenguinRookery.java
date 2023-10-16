// PenguinRookery.java
// W. Geesey
// 02/20/2023
// Class representing a Penguin Rookery with different penguin species.

package edu.fscj.cop2800c.penguins;

import java.util.Arrays;
import edu.fscj.cop2800c.table.TableFormatter;
import edu.fscj.cop2800c.statistics.StatisticsCalculator;
import java.lang.ArrayIndexOutOfBoundsException;
 
public class PenguinRookery implements TableFormatter, StatisticsCalculator {

    // Constant for the number of Penguin Species in the rookery.
    public static final int MAX_PENGUINS = 1000;
   
    // Array instance variable to hold the array passed to the constructor.
    private Penguin[] penguins;
   
    // Default constructor.
    public PenguinRookery() { }
   
    // Overloaded constructor.
    public PenguinRookery(int penguinCount, Penguin[] penguins) {
        this.penguins = new Penguin[penguinCount];
        for (int count = 0; count < penguinCount; count++) {
            this.penguins[count] = penguins[count];
        }
    }
   
    // toString
    @Override
    public String toString() {
        
        String retString = "Our rookery contains the following penguins:\n";
        
        for (int penguinCount = 0; penguinCount < this.penguins.length; 
             penguinCount++)
               retString += this.penguins[penguinCount] + "\n";

        return retString;
    }
   
   // return contents in tabular format
    public String table() {
      final String DATAFORMAT = "%-12s%-12s%-12s%-12s%-12s%-12s%-12s\n";
      
      // Header
      String retString = "Palmer Penguin Rookery:\n";
      
      // Data Headers
      retString += String.format(DATAFORMAT, "SampleNum", "Species", "CulmenLen",
                                "CulmenDepth", "FlipperLen", "BodyMass", "Sex");
      
      // Data
      for (int penguinCount = 0; penguinCount < this.penguins.length; 
             penguinCount++) {
             
           String penguinStr = String.format(DATAFORMAT, this.penguins[penguinCount].getSampleNum(),
                           this.penguins[penguinCount].getSpecies(),
                           this.penguins[penguinCount].getCulmenLen(),
                           this.penguins[penguinCount].getCulmenDepth(),
                           this.penguins[penguinCount].getFlipperLen(),
                           this.penguins[penguinCount].getBodyMass(),
                           this.penguins[penguinCount].getSex());
           retString += penguinStr;
      }
      
      return retString;
    }
    
    // Return the max from an array of doubles
    public Double max(Double[] inArray) {
    
      if (inArray.length ==0)
         throw new ArrayIndexOutOfBoundsException("Error: 0 length array passed");
         
      Double max = 0.0;
      for (Double d : inArray) {
          if (max < d)
              max = d;
      }
      return max;
    }
   
    // Find the min from an array of doubles
    public Double min(Double[] inArray) {
    
      if (inArray.length ==0)
         throw new ArrayIndexOutOfBoundsException("Error: 0 length array passed");
         
      Double min = Double.MAX_VALUE;
      for (Double d : inArray) {
          if (min > d)
              min = d;
      }
      return min;
    }
   
    // Find the mean(average) value in the array
    public Double mean(Double[] inArray) {
    
      if (inArray.length ==0)
         throw new ArrayIndexOutOfBoundsException("Error: 0 length array passed");
         
      Double total = 0.0;
      for (Double d : inArray) 
              total += d;
              
      total /= inArray.length;
      
      return total;
    }
   
    // Find the standard deviation in the array
    public Double stddev(Double[] inArray) {
    
      if (inArray.length ==0)
         throw new ArrayIndexOutOfBoundsException("Error: 0 length array passed");
      
      // Call to the mean method to get the mean   
      Double theMean = mean(inArray);
      
      // Calculate the deviation from the mean for each element, by subtracting the
      // mean, square it and store it in devFromMeanSq
      Double devFromMeanSq = 0.0;
      
      // Accumulator
      Double total = 0.0;
      
      // Loop through the array, subtract mean and square it
      for (Double d : inArray) {
          devFromMeanSq = Math.pow((d - theMean), 2);
          total += devFromMeanSq;
      }
      
      // Now divide by the population size (array size) and find the Square Root
      total /= inArray.length;
      total = Math.sqrt(total);
    
      // Return the standard deviation
      return total;
    }
    
    public void showSummaryStats() {

      System.out.println("Summary Statistics for our Penguins!:");
      
      // Extract arrays for relevant data
      Double[] flipperLen = new Double[penguins.length];
      Double[] culmenLen = new Double[penguins.length];
      Double[] culmenDepth = new Double[penguins.length];
      Double[] bodyMass = new Double[penguins.length];
      
      int count = 0;
      for (Penguin p : penguins) {
         flipperLen[count] = p.getFlipperLen();
         culmenLen[count] = p.getCulmenLen();
         culmenDepth[count] = p.getCulmenDepth();
         bodyMass[count] = p.getBodyMass().doubleValue();
         count ++;
      }
      
      System.out.printf("Flipper Length Min: %4.2f\n", min(flipperLen));
      System.out.printf("Flipper Length Max: %4.2f\n", max(flipperLen));
      System.out.printf("Flipper Length Mean: %4.2f\n", mean(flipperLen));
      System.out.printf("Flipper Length STDDEV: %4.2f\n", stddev(flipperLen));
 
      System.out.printf("Culmen Length Min: %4.2f\n", min(culmenLen)); 
      System.out.printf("Culmen Length Max: %4.2f\n", max(culmenLen));
      System.out.printf("Culmen Length Mean: %4.2f\n", mean(culmenLen));
      System.out.printf("Culmen Length STDDEV: %4.2f\n", stddev(culmenLen));
 
      System.out.printf("Culmen Depth Min: %4.2f\n", min(culmenDepth));
      System.out.printf("Culmen Depth Max: %4.2f\n", max(culmenDepth));
      System.out.printf("Culmen Depth Mean: %4.2f\n", mean(culmenDepth));
      System.out.printf("Culmen Depth STDDEV: %4.2f\n", stddev(culmenDepth));
      
      System.out.printf("Body Mass Min: %4.2f\n", min(bodyMass));
      System.out.printf("Body Mass Max: %4.2f\n", max(bodyMass));
      System.out.printf("Body Mass Mean: %4.2f\n", mean(bodyMass));
      System.out.printf("Body Mass STDDEV: %4.2f\n", stddev(bodyMass));

    }

    public static void main(String[] args) {
      
        String[][] inputArray = PenguinReader.read(MAX_PENGUINS);
       
       // Creating an empty array to store the maximum specified elements
       Penguin[] penguinArray = new Penguin[MAX_PENGUINS];
       int penguinCount = 0;
       
       // Fill array with elements up to the number read 
       for (int rows = 1; rows < MAX_PENGUINS && inputArray[rows][0] != null; rows++) {
       
            Integer newSampleNum = Integer.parseInt(inputArray[rows][0]);
            PenguinSpecies newSpecies = PenguinSpecies.parseSpecies(inputArray[rows][1]);
            Double newCulmenLen = Double.parseDouble(inputArray[rows][2]);
            Double newCulmenDepth = Double.parseDouble(inputArray[rows][3]);
            Double newFlipperLen = Double.parseDouble(inputArray[rows][4]);
            Integer newBodyMass = Integer.parseInt(inputArray[rows][5]);
            Sex newSex = Sex.parseSex(inputArray[rows][6]);
       
            penguinArray[penguinCount++] = new Penguin(newSampleNum, newCulmenLen, 
                                                       newCulmenDepth, newBodyMass, 
                                                       newSex,newSpecies,newFlipperLen); 
       } 
      
       // Using the overloaded constructor to create our penguin rookery.
       PenguinRookery penRook = new PenguinRookery(penguinCount, penguinArray);
       
       // create the database
       PenguinRookeryDB.createDB(penRook.penguins);
       
//        // Tests for toString and equals.
//        Penguin test1 = new Penguin(128, 33.3, 15.1, 3800, Sex.F, PenguinSpecies.GENTOO, 
//                            200.4); // 128+ causes == to return false for sampleNum, 127 
//                                    // causes it to return true  
//        Penguin test2 = new Penguin(128, 33.3, 15.1, 3800, Sex.F, PenguinSpecies.GENTOO, 
//                                    200.4);
// 
//       System.out.println("Penguin p1 and p2 == ? " + (test1 == test2)); 
//       // System.out.println(test1);  used to check the contents of the test objects.
//       // System.out.println(test2);
//       System.out.println("Penguin p1 and p2 equal ? " + test1.equals(test2));
//         
//       System.out.println(penRook);
// 
//        
//        // Calling the table method to format output.
//        System.out.println(penRook.table());
//        
//        // show the statistics
//        penRook.showSummaryStats();

    }
   
    

    
   
}
