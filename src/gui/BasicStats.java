package gui;

import java.util.*;

/**
 * A simple class to compute basic, descriptive statistics.
 */
public class BasicStats {

    public static void main(String ... args) {
        System.out.println("Let's do some basic statistics...");
    }

    /**
     * Compute the mean of an array of numbers.
     * 
     * @param numbers An array of doubles that contains the list of numbers
     * @return The mean of all the numbers provided
     */
    public static double mean(double ... numbers) {
      if(numbers == null) {
        throw new IllegalArgumentException("The array must be non-null.");
      }

      if(numbers.length == 0) {
        throw new IllegalArgumentException("The array must be of length non-zero.");
      }
      double sum = 0;
      for (double num : numbers) {
          sum += num;
      }
      return sum / numbers.length;
    }

    /**
     * Compute the median of an array of numbers.
     * 
     * @param numbers An array of doubles that contains the list of numbers
     * @return The median of all the numbers provided
     */
    public static double median(double ... numbers) {
      if(numbers == null) {
        throw new IllegalArgumentException("The array must be non-null.");
      }

      if(numbers.length == 0) {
        throw new IllegalArgumentException("The array must be of length non-zero.");
      }

      Arrays.sort(numbers);

      int size = numbers.length;
      double median = 0.0;

      if (size > 0) {
        if (size % 2 == 0) {
          median = (numbers[size >> 1] + numbers[(size >> 1) - 1])/2;
        }
        else {
          median = numbers[size >> 1];
        }
      }

      return median;
    }

    /**
     * Compute the mode of an array of numbers.
     * 
     * @param numbers An array of doubles that contains the list of numbers
     * @return The mode of all the numbers provided
     */
    public static double mode(double ... numbers) {
      if(numbers == null) {
        throw new IllegalArgumentException("The array must be non-null.");
      }

      if(numbers.length == 0) {
        throw new IllegalArgumentException("The array must be of length non-zero.");
      }
      
      double mode = 0.0;
      int modeCount = 0;

      for (double num: numbers) {

        int count = 0;

        for (double otherNum: numbers) {
          if (num == otherNum) {
            count++;
          }
        }

        if (count > modeCount) {
          modeCount = count;
          mode = num;
        }

      }

      return mode;
    }

    /**
     * Compute the maximum value in an array of numbers.
     * 
     * @param numbers An array of doubles that contains the list of numbers
     * @exception IllegalArgumentException if the given array is null or when the array length is zero
     * @return The maximum value of all the numbers provided
     */
    public static double maximum(double ... numbers) {
      double maxNum = Double.NEGATIVE_INFINITY;
      if(numbers == null) {
        throw new IllegalArgumentException("The array must be non-null.");
      }

      if(numbers.length == 0) {
        throw new IllegalArgumentException("The array must be of length non-zero.");
      }

      for (double num: numbers) {
        if(num > maxNum) {
          maxNum = num;
        }
      }
      return maxNum;
  }
}
