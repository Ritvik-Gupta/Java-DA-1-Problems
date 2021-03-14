package app;

import java.util.Scanner;
import java.util.regex.Pattern;

import services.Console.Console;

public final class Armstrong {
   private static final Scanner S = new Scanner(System.in);

   private static boolean isArmstrong(String numericStr) {
      int totalSum = 0;
      for (int pos = 0; pos < numericStr.length(); ++pos)
         totalSum += Math.pow(Integer.parseInt(numericStr.substring(pos, pos + 1)), 3);
      return totalSum == Integer.parseInt(numericStr);
   }

   private static boolean isArmstrong(int num) {
      int totalSum = 0, numCopy = num;
      for (; num > 0; num /= 10)
         totalSum += Math.pow(num % 10, 3);
      return totalSum == numCopy;
   }

   private static boolean isNumeric(String numericStr) {
      return Pattern.matches("^\\d+$", numericStr);
   }

   public static void main(String[] args) throws Exception {
      Console.print("\nEnter a Number to check if it is an Armstrong Number or not :\t");
      String numericStr = S.next();
      if (!isNumeric(numericStr))
         throw new Exception("Given Input Argument is not a Numeric number");
      Console.log(
         "Given Number = ", numericStr, " is ", 
         isArmstrong(numericStr) && isArmstrong(Integer.parseInt(numericStr)) ? "" : "not ", 
         "an Armstrong Number\n\n"
      );
   }
}
