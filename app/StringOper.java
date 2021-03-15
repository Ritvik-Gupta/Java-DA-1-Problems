package app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import services.Console.Console;

public final class StringOper {
   private static final Scanner S = new Scanner(System.in);

   public static int wordCount(String str) {
      return str.split(" ").length;
   }

   public static String arrayToString(char[] charArr) {
      return new String(charArr);
   }

   public static HashSet<Character> mostFrequent(String str) {
      HashMap<Character, Integer> frequency = new HashMap<>();
      int debouncedMaxFreq = 0;
      for (int pos = 0; pos < str.length(); ++pos) {
         char ch = str.charAt(pos);
         int currentFreq = frequency.containsKey(ch) ? frequency.get(ch) : 0;
         frequency.put(ch, ++currentFreq);
         if (currentFreq > debouncedMaxFreq)
            debouncedMaxFreq = currentFreq;
      }

      final int maxFreq = debouncedMaxFreq;
      HashSet<Character> mostFrequentChar = new HashSet<>();
      frequency.forEach((keyCh, freq) -> {
         if (freq == maxFreq)
            mostFrequentChar.add(keyCh);
      });
      return mostFrequentChar;
   }

   public static void main(String[] args) {
      Console.log("\nEnter a Sentence to calculate the word count :\t");
      String str = S.nextLine();
      Console.log("\nWord Count :\t", wordCount(str));

      Console.log("\nEnter the size of the Array to Characters :\t");
      int size = S.nextInt();
      char[] arrCh = new char[size];
      for (int pos = 0; pos < size; ++pos) {
         Console.log("( ", pos + 1, " ) :\t");
         arrCh[pos] = S.next().charAt(0);
      }
      Console.log("\nComplete String :\t", arrayToString(arrCh));

      Console.log("\nEnter a String to find its Most Frequent Characters :\t");
      str = S.next();
      Console.log("Most Frequent Characters :\n");
      Console.println(mostFrequent(str));
   }
}
