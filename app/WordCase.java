package app;

import java.util.Scanner;

import services.Console.Console;

public final class WordCase {
   private static final Scanner S = new Scanner(System.in);

   private static Character flipCasing(Character ch) {
      if (Character.isAlphabetic(ch)) {
         if (Character.isLowerCase(ch))
            return Character.toUpperCase(ch);
         if (Character.isUpperCase(ch))
            return Character.toLowerCase(ch);
      }
      return ch;
   }

   private static String flipCasing(String str) {
      String flippedStr = "";
      for (int pos = 0; pos < str.length(); ++pos)
         flippedStr += flipCasing(str.charAt(pos));
      return flippedStr;
   }

   public static void main(String[] args) {
      Console.log("\nEnter a Word to change is Casing :\t");
      String word = S.next();
      Console.log("Flipped Word :\t", flipCasing(word), "\n");
   }
}
