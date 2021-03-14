package app;

import java.util.HashMap;

import services.Console.Console;

public final class Prime {
   private static boolean isPrimeNum(int num) {
      for (int pos = 2; pos < num; ++pos)
         if (num % pos == 0)
            return false;
      return true;
   }

   public static void main(String[] args) throws Exception {
      HashMap<Integer, Boolean> primeMap = new HashMap<Integer, Boolean>();

      for (int pos = 0; pos < args.length; ++pos)
         try {
            primeMap.put(pos, isPrimeNum(Integer.parseInt(args[pos])));
         } catch (NumberFormatException err) {
            throw new Exception(String.format("Argument ' %s ' at pos ( %d ) is not an Integer", args[pos], pos));
         }

      primeMap.forEach((idx, isPrime) -> {
         Console.print("Integer < %s >\t%s a Prime Number", args[idx], isPrime ? "is" : "is not");
      });
   }
}
