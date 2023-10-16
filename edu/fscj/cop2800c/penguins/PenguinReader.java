// PenguinReader.java
// W. Geesey
// 02/20/2023
// Class which reads penguin data from a CSV file

package edu.fscj.cop2800c.penguins;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PenguinReader {

    public static final int DATA_DIMENSIONS = 7;

    // read data from a CSV file, return a 2D array
    public static String[][] read(final int MAX_PENGUINS) {
          final String FILENAME = "PalmerPenguins.csv";
          String[][] penguins = null;

          // create a file instance
          File file = new File(FILENAME);
          try (Scanner input = new Scanner(file);) {
               penguins = new String[MAX_PENGUINS][DATA_DIMENSIONS];
               for (int rows = 0; rows < MAX_PENGUINS; rows++)
                   for (int cols = 0; cols < DATA_DIMENSIONS; cols++)
                       penguins[rows][cols] = null;

                // read data from file
                int rowCount = 0;
                // constants for the columns
                final int PENGUIN_SAMPLENUM_COL = 0;
                final int PENGUIN_SPECIES_COL = 1;
                final int PENGUIN_CULMENL_COL = 2;
                final int PENGUIN_CULMEND_COL = 3;
                final int PENGUIN_FLIPPERL_COL = 4;
                final int PENGUIN_BODYMASS_COL = 5;
                final int PENGUIN_SEX_COL = 6;

                // loop while there is more input               
                while (input.hasNext()) {
                      String line = input.nextLine();
                      String[] sp = line.split(",", DATA_DIMENSIONS);
                      penguins[rowCount][PENGUIN_SAMPLENUM_COL] = sp[PENGUIN_SAMPLENUM_COL];
                      penguins[rowCount][PENGUIN_SPECIES_COL] = sp[PENGUIN_SPECIES_COL];
                      penguins[rowCount][PENGUIN_CULMENL_COL] = sp[PENGUIN_CULMENL_COL];
                      penguins[rowCount][PENGUIN_CULMEND_COL] = sp[PENGUIN_CULMEND_COL];
                      penguins[rowCount][PENGUIN_FLIPPERL_COL] = sp[PENGUIN_FLIPPERL_COL];
                      penguins[rowCount][PENGUIN_BODYMASS_COL] = sp[PENGUIN_BODYMASS_COL];
                      penguins[rowCount][PENGUIN_SEX_COL] = sp[PENGUIN_SEX_COL];                       
  
                      // increment rowCount to get ready for the next row
                      rowCount++;
                }
          } catch (IOException ex) {
                System.err.println("Exception! " + ex);
          } 
                
          return(penguins);
     }
}