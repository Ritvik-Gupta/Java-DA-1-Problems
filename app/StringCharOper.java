package app;

import java.util.Scanner;

import services.Console.Console;

public final class StringCharOper {
   private static final Scanner S = new Scanner(System.in);
   /*
    * Write a menu driven program to i) compare two strings ii) get the character
    * in the specified position iii) extract a substring iv) replace a character
    * with the given character v) get the position of a specified
    * substring/character.
    */

   private final StringBuffer buffer;

   private StringCharOper(String str) {
      this.buffer = new StringBuffer(str);
   }

   private void compare() {
      Console.log("Enter the String to Compare to :\t");
      StringBuffer str = new StringBuffer(S.nextLine());

      Console.log("\nComparision Result :\t", this.buffer.compareTo(str));
   }

   private void getCh() throws Exception {
      try {
         Console.log("Enter the Position :\t");
         int pos = S.nextInt();

         if (pos < 0)
            throw new Exception("Position cannot be less than 0");
         if (pos >= this.buffer.length())
            throw new Exception("Position cannot be greater than Buffer Size");

         Console.log("\nCharacter at position :\t", this.buffer.charAt(pos));
      } finally {
         S.nextLine();
      }
   }

   private void substr() throws Exception {
      try {
         Console.log("Enter the Start Index :\t");
         int startIdx = S.nextInt();

         if (startIdx < 0)
            throw new Exception("Start Index cannot be less than 0");
         if (startIdx >= this.buffer.length())
            throw new Exception("Start Index cannot be greater than Buffer Size");

         Console.log("Enter the End Index :\t");
         int endIdx = S.nextInt();

         if (endIdx < 0)
            throw new Exception("End Index cannot be less than 0");
         if (endIdx >= this.buffer.length())
            throw new Exception("End Index cannot be greater than Buffer Size");

         if (startIdx > endIdx)
            throw new Exception("Start Index cannot be greater than End Index");

         Console.log("\nExtracted Substring :\t", this.buffer.substring(startIdx, endIdx));
      } finally {
         S.nextLine();
      }
   }

   private void replace() throws Exception {
      try {
         Console.log("Enter the Position :\t");
         int pos = S.nextInt();

         if (pos < 0)
            throw new Exception("Position cannot be less than 0");
         if (pos >= this.buffer.length())
            throw new Exception("Position cannot be greater than Buffer Size");

         Console.log("Enter the Update Character :\t");
         String str = S.next();

         if (str.length() != 1)
            throw new Exception("The Input should have only 1 Character");

         char updatedChr = str.charAt(0);
         this.buffer.setCharAt(pos, updatedChr);
         Console.log("\nUpdate String Buffer :\t", this.buffer);
      } finally {
         S.nextLine();
      }
   }

   private void getPos() {
      Console.log("Enter the Substring :\t");
      String str = S.nextLine();

      Console.log("\nIndex of Substring :\t", this.buffer.indexOf(str));
   }

   public static void main(String[] args) {
      Console.log("\nEnter a String to Store in the Buffer :\t");
      String str = S.nextLine();
      StringCharOper obj = new StringCharOper(str);

      Console.log("\nString Character Operations :\n");
      Console.log("\n\t(1) <COMPARE> :\tCompare a String to the String Buffer");
      Console.log("\n\t(2) <GET-CH> :\tGet a character in the specified position");
      Console.log("\n\t(3) <SUBSTR> :\tExtract a Substring");
      Console.log("\n\t(4) <REPLACE> :\tReplace a character with a given character");
      Console.log("\n\t(4) <GET-POS> :\tGet the position of a Substring");
      Console.log("\n\t(4) <PRINT> :\tPrint the String Buffer");
      Console.log("\n\t(5) <END> :\tEnd the Simulation");

      while (true) {
         Console.log("\n\nEnter a Command ::\t");
         String command = S.nextLine();

         try {
            if (command.equals("END")) {
               Console.log("\nTerminating ...\n\n");
               break;
            }

            else if (command.equals("COMPARE"))
               obj.compare();

            else if (command.equals("GET-CH"))
               obj.getCh();

            else if (command.equals("SUBSTR"))
               obj.substr();

            else if (command.equals("REPLACE"))
               obj.replace();

            else if (command.equals("GET-POS"))
               obj.getPos();

            else if (command.equals("PRINT"))
               Console.log(obj.buffer);

            else
               throw new Exception("Invalid Command Specified");
         } catch (Exception err) {
            Console.log("\nError :\t", err.getMessage());
         }
      }
   }
}
