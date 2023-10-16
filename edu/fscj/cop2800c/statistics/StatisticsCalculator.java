// StatisticsCalculator.java
// W. Geesey
// 02/20/2023
// Interface for calculating statistics.


package edu.fscj.cop2800c.statistics;

// interface for statistical calculations
public interface StatisticsCalculator {

    // returns max from array of Double
    public Double max(Double[] inArray) throws ArrayIndexOutOfBoundsException;

    // returns mean from array of Double
    public Double mean(Double[] inArray) throws ArrayIndexOutOfBoundsException;;
    
    public Double stddev(Double[] inArray) throws ArrayIndexOutOfBoundsException;
}