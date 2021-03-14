package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import services.Console.Console;

public final class Fibonacci {
   private static final Scanner S = new Scanner(System.in);

   private static List<Integer> calcSeries(int size) {
      ArrayList<Integer> series = new ArrayList<>(List.of(0));
      if (size > 1) {
         series.add(1);
         for (int pos = 2; pos < size; ++pos)
            series.add(series.get(pos - 1) + series.get(pos - 2));
      }
      return series;
   }

   public static void main(String[] args) {
      Console.log("\nEnter the size of Fibonacci Series : \t");
      int size = S.nextInt();
      Console.println(calcSeries(size));
   }
}
