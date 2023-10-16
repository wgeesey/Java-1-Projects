// Bird.java
// W. Geesey
// 2/26/2023
// Bird super class for Penguins in a rookery.

package edu.fscj.cop2800c.penguins;

import java.util.*;

public class Bird {
   
   private Integer sampleNum = 0;
   private Double culmenLen = 0.0, culmenDepth = 0.0;
   private Integer bodyMass = 0;
   private Sex sex = Sex.NO_SEX;

   public Bird () {}
   
   public Bird(Integer sampleNum, Double culmenLen,
               Double culmenDepth, Integer bodyMass,
               Sex sex) {
               
      this.sampleNum = sampleNum;
      this.culmenLen = culmenLen;
      this.culmenDepth = culmenDepth;
      this.bodyMass = bodyMass;
      this.sex = sex;
      
   }

   // sampleNum accessor
   public Integer getSampleNum() {
      return this.sampleNum;
   }
   
   // culmenLen accessor
    public Double getCulmenLen() {
      return this.culmenLen;
    }
    
    // culmenDepth accessor
    public Double getCulmenDepth() {
      return this.culmenDepth;
    }

   // bodyMass accessor
    public Integer getBodyMass() {
      return this.bodyMass;
    }
    
    // sex accessor
    public Sex getSex() {
      return this.sex;
    }

   // toString
    @Override
    public String toString() {
      String returnStr =  this.getSampleNum() + "," +
                          this.getCulmenLen() + "," +
                          this.getCulmenDepth() + "," +
                          this.getBodyMass() + "," +
                          this.getSex().name();
      return returnStr;
    }
    
         // equals override
    @Override
    public boolean equals(Object o) {
       boolean result = false;
        
       if (o instanceof Bird) {
           Bird p = (Bird)o;
         
           if   (p.getSampleNum().equals(this.getSampleNum()) &&
                 p.getBodyMass().equals(this.getBodyMass()) &&
                 p.getSex().name() == this.getSex().name() &&
                 p.getCulmenLen().equals(this.getCulmenLen()) &&
                 p.getCulmenDepth().equals(this.getCulmenDepth()))
                 result = true;
                 // Used the print below to identify which evaluation above was
                 // returning false...it was sampleNum. Using == did not work,
                 // I needed to change it to use the equal method.
                 //System.out.println(p.getSampleNum() == this.getSampleNum());
        }
                     
        return result;
    }

}