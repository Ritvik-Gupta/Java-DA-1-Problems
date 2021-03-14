package app;

import static java.lang.Integer.toBinaryString;
import static java.lang.Integer.toHexString;
import static java.lang.Integer.toOctalString;

import java.util.LinkedList;
import java.util.Scanner;

import services.Console.Console;

public final class DecimalConversion {
   private static final Scanner S = new Scanner(System.in);

   private static LinkedList<Integer> toBinary(int num) {
      LinkedList<Integer> convertedNum = new LinkedList<>();
      int parity = 1;
      while (parity <= num) {
         convertedNum.addFirst((num & parity) == parity ? 1 : 0);
         parity *= 2;
      }
      return convertedNum;
   }

   private static LinkedList<Integer> convertToBase(int base, int num) {
      LinkedList<Integer> convertedNum = new LinkedList<>();
      for (; num > 0; num /= base)
         convertedNum.addFirst(num % base);
      return convertedNum;
   }

   public static void main(String[] args) {
      Console.log("\nEnter an Integer Number to Convert to different bases :\t");
      Integer num = S.nextInt();

      Console.log("\nBinary Representation using 'toBinaryString' method :\t", toBinaryString(num));
      Console.log("\nBinary Representation using 'convertToBase' method :\t", convertToBase(2, num));
      Console.log("\nBinary Representation using 'toBinary' method :\t", toBinary(num), "\n");

      Console.log("\nOctal Representation using 'toBinaryString' method :\t", toOctalString(num));
      Console.log("\nOctal Representation using 'convertToBase' method :\t", convertToBase(8, num), "\n");

      Console.log("\nHexa-D Representation using 'toBinaryString' method :\t", toHexString(num));
      Console.log("\nHexa-D Representation using 'convertToBase' method :\t", convertToBase(16, num), "\n");
   }
}
