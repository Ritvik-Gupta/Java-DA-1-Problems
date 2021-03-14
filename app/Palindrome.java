package app;

import java.util.Scanner;

import services.Console.Console;

public final class Palindrome {
   private static final Scanner S = new Scanner(System.in);

   private static boolean isPalindrome(String str) {
      int size = str.length();
      for (int pos = 0; pos < size / 2; ++pos)
         if (str.charAt(pos) != str.charAt(size - 1 - pos))
            return false;
      return true;
   }

   public static void main(String[] args) {
      Console.log("\nEnter a String to check if it is a Palindrome or not :\n\t");
      String str = S.next();
      Console.log("The given String = '", str, "' is ", isPalindrome(str) ? "": "not ", "a Palindrome\n");
   }
}
