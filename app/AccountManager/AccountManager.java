package app.AccountManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import services.Console.Console;
import services.Constrained.Constrained;

public final class AccountManager {
   private static final Scanner S = new Scanner(System.in);

   private static final Pattern depositRegex = 
      Pattern.compile("^" + BankAccount.accountNumPattern + "\\sDEPOSIT\\s\\d+$");
   private static final Pattern withdrawRegex = 
      Pattern.compile("^" + BankAccount.accountNumPattern + "\\sWITHDRAW\\s\\d+$");
   private static final Pattern deleteRegex = 
      Pattern.compile("^" + BankAccount.accountNumPattern + "\\sDELETE$");

   private static SavingsAccount createAccount() {
      Console.log("\nEnter the following details to create a Savings Account :\n");

      Console.log("\tName :\t");
      String name = S.nextLine();

      Console.log("\tAddress :\t");
      String address = S.nextLine();

      Console.log("\tDate of Birth :\t");
      Constrained dob = BankAccount.constrainedDob.with(S.nextLine());

      Console.log("\tAccount Number :\t");
      Constrained accountNum = BankAccount.constrainedAccountNum.with(S.nextLine());

      Console.log("\tBalance :\t");
      long balance = S.nextLong();

      S.nextLine();
      return new SavingsAccount(name, address, dob, accountNum, balance);
   }

   private static Map.Entry<String, Long> transactionEntry(String command) {
      String[] tokens = command.split(" ");
      String accountNum = tokens[0];
      long amount = Long.parseLong(tokens[2]);
      return Map.entry(accountNum, amount);
   }

   private static void displayAccount(SavingsAccount account) {
      Console.log("\n--- Account :\t", account.accountNum, " ---\n", account);
   }

   public static void main(String[] args) {
      HashMap<String, SavingsAccount> accountsTable = new HashMap<>();

      Console.log("\nBank Account Manager :\n");
      Console.log("\n\t(1) <CREATE> :\tCreate a new Account");
      Console.log("\n\t(5) <PRINT> :\tPrint all the Account Details");
      Console.log("\n\t(2) <#N DEPOSIT #A> :\tDeposit in an Account with Account Number N");
      Console.log("\n\t(3) <#N WITHDRAW #A> :\tWithdraw from an Account with Account Number N");
      Console.log("\n\t(4) <#N DELETE> :\tDelete an Account with Account Number N");
      Console.log("\n\t(6) <END> :\tEnd the Simulation");

      while (true) {
         Console.log("\n\nEnter a Command ::\t");
         String command = S.nextLine();

         try {
            if (command.equals("PRINT"))
               accountsTable.forEach((__, account) -> displayAccount(account));

            else if (command.equals("END")) {
               Console.log("\nTerminating ...\n\n");
               break;
            }

            else if (command.equals("CREATE")) {
               SavingsAccount account = createAccount();
               accountsTable.put(account.accountNum.value, account);
            }

            else if (depositRegex.matcher(command).matches()) {
               Map.Entry<String, Long> entry = transactionEntry(command);
               if (!accountsTable.containsKey(entry.getKey()))
                  throw new Exception("Account with given Account Number not found");
               SavingsAccount account = accountsTable.get(entry.getKey());
               account.deposit(entry.getValue());
               displayAccount(account);
            }

            else if (withdrawRegex.matcher(command).matches()) {
               Map.Entry<String, Long> entry = transactionEntry(command);
               if (!accountsTable.containsKey(entry.getKey()))
                  throw new Exception("Account with given Account Number not found");
               SavingsAccount account = accountsTable.get(entry.getKey());
               account.withdraw(entry.getValue());
               displayAccount(account);
            }

            else if (deleteRegex.matcher(command).matches()) {
               String accountNum = command.split(" ")[0];
               if (!accountsTable.containsKey(accountNum))
                  throw new Exception("Account with given Account Number not found");
               accountsTable.remove(accountNum);
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
