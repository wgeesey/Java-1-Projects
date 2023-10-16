// Penguin.java
// W. Geesey
// 02/20/2023
// Class for representing penguins.

package edu.fscj.cop2800c.penguins;

// Class for a penguin.
public class Penguin extends Bird {
   
   private Double flipperLen;
   private PenguinSpecies species;
   
   // Constructor (Default).
   public Penguin() {
   
      this.flipperLen = 0.0;
      this.species = PenguinSpecies.NO_SPECIES;
   }
   
   // Overloaded Constructor accepting a species parameter.
   public Penguin(Integer sampleNum, Double culmenLen, Double culmenDepth, 
                  Integer bodyMass, Sex sex,  PenguinSpecies species, Double flipperLen) {
        super(sampleNum, culmenLen, culmenDepth, bodyMass, sex);
        this.species = species;
        this.flipperLen = flipperLen;
   }

    // flipperLen accessor
    public Double getFlipperLen() {
      return this.flipperLen;
    } 
    
    // species accessor
    public PenguinSpecies getSpecies() {
      return this.species;
    }  

   // toString override.
   @Override
   public String toString() {
   
        String returnStr = super.toString() + "(" +
                           this.getFlipperLen() + "," +
                           this.getSpecies().name() + ")";   
        return returnStr;
   }
   
   // equals override
    @Override
    public boolean equals(Object o) {
       boolean result = false;
        
       if (o instanceof Penguin) {
           Penguin p = (Penguin)o;
         
           if   ((super.equals(p) == true) &&
                  this.getFlipperLen().equals(p.getFlipperLen()) &&
                  this.getSpecies().name() == p.getSpecies().name())
                  result = true;
         }
                     
        return result;
    }
}
