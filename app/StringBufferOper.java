package app;

import java.util.Scanner;

import services.Console.Console;

public final class StringBufferOper {
   private static final Scanner S = new Scanner(System.in);

   private final StringBuffer buffer;

   private StringBufferOper() {
      this.buffer = new StringBuffer();
   }

   private void append() {
      Console.log("Enter the String to Append:\t");
      String str = S.nextLine();

      Console.log("\nUpdated String Buffer :\t", this.buffer.append(str));
   }

   private void insert() throws Exception {
      try {
         Console.log("Enter the String to Insert:\t");
         String str = S.nextLine();

         Console.log("Enter the Offset :\t");
         int offset = S.nextInt();

         if (offset < 0)
            throw new Exception("Offset cannot be less than 0");
         if (offset >= this.buffer.length())
            throw new Exception("Offset Larger than Buffer Size. Use Append Operation instead.");

         Console.log("\nUpdated String Buffer :\t", this.buffer.insert(offset, str));
      } finally {
         S.nextLine();
      }
   }

   private void delete() throws Exception {
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
            throw new Exception("Start Index should be less than or equal to End Index");

         Console.log("\nUpdated String Buffer :\t", this.buffer.delete(startIdx, endIdx));
      } finally {
         S.nextLine();
      }
   }

   public static void main(String[] args) {
      StringBufferOper obj = new StringBufferOper();

      Console.log("\nString Buffer Operations :\n");
      Console.log("\n\t(1) <APPEND> :\tAppend a String to the String Buffer");
      Console.log("\n\t(2) <INSERT> :\tInsert a String to the String Buffer");
      Console.log("\n\t(3) <DELETE> :\tDelete a String from the String Buffer");
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

            else if (command.equals("APPEND"))
               obj.append();

            else if (command.equals("INSERT"))
               obj.insert();

            else if (command.equals("DELETE"))
               obj.delete();

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
