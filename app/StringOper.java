package app;

import java.util.HashMap;
import java.util.HashSet;

public final class StringOper {
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
   }
}
