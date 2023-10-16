// PenguinSpecies.java
// W. Geesey
// 02/20/2023
// Enum with penguin species.

package edu.fscj.cop2800c.penguins;

// Enum for the species values.
public enum PenguinSpecies {

   NO_SPECIES, CHINSTRAP, GENTOO, ADELIE;
   
   public static PenguinSpecies parseSpecies(String speciesStr) {
         PenguinSpecies retVal = NO_SPECIES;
         
         switch (speciesStr.toLowerCase()) {
            case "chinstrap":
               retVal = CHINSTRAP;
               break;
            case "gentoo":
               retVal = GENTOO;
               break;
            case "adelie":
               retVal = ADELIE;
               break;
          }
          return retVal;
    }  
 }
