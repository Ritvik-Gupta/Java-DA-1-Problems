package app;

import java.util.ArrayList;

import services.Console.Console;

public final class Factorial {

   private static int factorial(int num) {
      int factorial = 1;
      for (int pos = 1; pos <= num; ++pos)
         factorial *= pos;
      return factorial;
   }

   public static void main(String[] args) throws Exception {
      ArrayList<Integer> factorialNumbers = new ArrayList<Integer>();

      for (int pos = 0; pos < args.length; ++pos)
         try {
            factorialNumbers.add(factorial(Integer.parseInt(args[pos])));
         } catch (NumberFormatException err) {
            throw new Exception(String.format("Argument ' %s ' at pos ( %d ) is not an Integer", args[pos], pos));
         }

      factorialNumbers.forEach(elm -> Console.print("%d\n", elm));
   }
}
