package app.PhoneDirectory;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import services.Console.Console;
import services.Constrained.Constrained;
import src.Trie.Trie;

public final class PhoneDirectory {
   private static final Scanner S = new Scanner(System.in);
   private static final Pattern searchRegex = Pattern.compile("^SEARCH\\s\\w*$");

   private final Trie<TelephoneIndex> trie;

   private PhoneDirectory() {
      this.trie = new Trie<>();
   }

   private void createPhoneRecord() throws Exception {
      Console.log("\nEnter the folowing details to Create a new Phone Record :\n");

      Console.log("\tCustomer Name :\t");
      String customerName = S.nextLine();

      Console.log("\tPhone Number :\t");
      Constrained phoneNum = Telephone.constrainedPhoneNum.with(S.nextLine());

      TelephoneIndex phoneRecord = new TelephoneIndex(customerName, phoneNum);
      this.trie.insert(phoneRecord.customerName, phoneRecord);
   }

   private void updateRecordCustomerName() throws Exception {
      Console.log("\nEnter the folowing details to Update a Phone Record :\n");

      Console.log("\tCustomer Name :\t");
      String customerName = S.nextLine();

      Console.log("\tUpdated Name :\t");
      String updatedName = S.nextLine();

      TelephoneIndex phoneRecord = this.trie.delete(customerName);
      String prevName = phoneRecord.customerName;
      phoneRecord.updateName(updatedName);
      this.trie.insert(phoneRecord.customerName, phoneRecord);
      Console.log("\nUpdated :\t' ", prevName, " ' -> ' ", updatedName, " '");
   }

   private void updateRecordPhoneNum() throws Exception {
      Console.log("\nEnter the folowing details to Update a Phone Record :\n");

      Console.log("\tCustomer Name :\t");
      String customerName = S.nextLine();

      Console.log("\tUpdated Phone Number :\t");
      Constrained updatedPhoneNum = Telephone.constrainedPhoneNum.with(S.nextLine());

      TelephoneIndex phoneRecord = this.trie.fetch(customerName);
      String prevPhoneNum = phoneRecord.phoneNum.value;
      phoneRecord.updatePhoneNo(updatedPhoneNum);
      Console.log("\nUpdated :\t' ", prevPhoneNum, " ' -> ' ", phoneRecord.phoneNum, " '");
   }

   public static void main(String[] args) {
      PhoneDirectory directory = new PhoneDirectory();

      Console.log("\nPhone Directory :\n");
      Console.log("\n\t(1) <CREATE> :\tCreate a new Phone Record");
      Console.log("\n\t(2) <PRINT> :\tPrint all the Phone Records");
      Console.log("\n\t(3) <UPDATE-NAME> :\tUpdate the Customer Name of a Phone Record");
      Console.log("\n\t(4) <UPDATE-PHONE> :\tUpdate the Phone Number of a Phone Record");
      Console.log("\n\t(5) <SEARCH #A> :\tSearch for all the Phone Records starting with a substring");
      Console.log("\n\t(6) <END> :\tEnd the Simulation");

      while (true) {
         Console.log("\n\nEnter a Command ::\t");
         String command = S.nextLine();

         try {
            if (command.equals("PRINT")) {
               var totalEntries = directory.trie.entryMap();

               Console.log("\nTotal Entries Found :\t", totalEntries.size());
               totalEntries.forEach((key, telephone) -> {
                  Console.log("\n\t", key, "\t->\t", telephone.phoneNum);
               });
            }

            else if (command.equals("END")) {
               Console.log("\nTerminating ...\n\n");
               break;
            }

            else if (command.equals("CREATE"))
               directory.createPhoneRecord();

            else if (command.equals("UPDATE-NAME"))
               directory.updateRecordCustomerName();

            else if (command.equals("UPDATE-PHONE"))
               directory.updateRecordPhoneNum();

            else if (searchRegex.matcher(command).matches()) {
               String keyword = command.substring(7);
               var partialEntries = directory.trie.partialSearch(keyword);

               Console.log("\nPossible Entries Found :\t", partialEntries.size());
               partialEntries.forEach((key, telephone) -> {
                  Console.log("\n\t", key, "\t->\t", telephone.phoneNum);
               });
            }

            else
               throw new Exception("Invalid Command Specified");
         } catch (PatternSyntaxException err) {
            Console.log("\nPattern Mismatch Error :\t", err.getDescription());
            Console.log("\nAllowed Format :\t", err.getPattern());
         } catch (Exception err) {
            Console.log("\nError :\t", err.getMessage());
         }
      }
   }
}
