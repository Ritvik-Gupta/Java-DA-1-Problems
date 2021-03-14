package app;

import java.util.ArrayList;
import java.util.Scanner;

import services.Console.Console;

public final class Average {
   private static final Scanner S = new Scanner(System.in);

   public static void main(String[] args) {
      Console.log("\nEnter the total number of Elements :\t");
      int size = S.nextInt();
      ArrayList<Double> list = new ArrayList<>();

      for (int pos = 0; pos < size; ++pos) {
         Console.log("Enter the Element ( ", pos + 1, " ) :\t");
         list.add(S.nextDouble());
      }

      double sum = list.stream().reduce(0.0, Double::sum);
      Console.log("\nSum of Elements :\t", sum, "\nAverage of Elements :\t", (sum / size), "\n");
   }
}
