package app;

import java.util.Scanner;

import services.Console.Console;

public final class MultiplicationTable {
   private static Scanner S = new Scanner(System.in);

   public static void main(String[] args) {
     Console.print("\n\nMultiplication Table\n");

     Console.print("Enter the Multiplicand :\t");
      int multiplicand = S.nextInt();

     Console.print("Enter the Upper Limit of Multiplication :\t");
      int upperLimit = S.nextInt();
      upperLimit = upperLimit < 1 ? 10 : upperLimit;

     Console.print("\nMultiplicand :\t%o\n", multiplicand);
      for (int multiplier = 0; multiplier <= upperLimit; ++multiplier)
        Console.print("\t. %d = %d", multiplier, multiplicand * multiplier);

      S.close();
   }
}
