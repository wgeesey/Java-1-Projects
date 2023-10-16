// Sex.java
// W. Geesey
// 02/20/2023
// Enum with penguin sex.

package edu.fscj.cop2800c.penguins;

// Enum for the species values.
public enum Sex {

   NO_SEX, M, F;
   
   public static Sex parseSex(String sexStr) {
         Sex retVal = NO_SEX;
         
         switch (sexStr.toLowerCase()) {
            case "male":
               retVal = M;
               break;
            case "female":
               retVal = F;
               break;
         }
          return retVal;
    }   
 }