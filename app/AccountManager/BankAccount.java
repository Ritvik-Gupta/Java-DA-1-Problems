package app.AccountManager;

import services.Constrained.Constrained;
import services.Constrained.IConstrainedFor;

public abstract class BankAccount {
   public static final String accountNumPattern = "ACCN-\\d{4}";

   public static final IConstrainedFor constrainedDob = Constrained.compile(
      "^(1[0-2]|0[1-9])(?<D>[-|.])(3[0-1]|[1-2]\\d|0[1-9])\\k<D>(19\\d|20[01])\\d$", 
      "Invalid Date of Birth format",
      "MM|DD|YYYY ('.', '|', '-' as seperators and supported years are [1900-2020] )"
   );
   public static final IConstrainedFor constrainedAccountNum = Constrained.compile(
      "^" + accountNumPattern + "$",
      "Invalid Account Number specified",
      "ACCN-xxxx ( where x is a digit [0-9])"
   );

   private final String customerName, address;
   private final Constrained customerDob;
   public final Constrained accountNum;
   protected long balance;

   public BankAccount(
      String customerName, String address, Constrained customerDob, 
      Constrained accountNum, long balance
   ) {
      this.customerName = customerName;
      this.address = address;
      this.customerDob = customerDob;
      this.accountNum = accountNum;
      this.balance = balance;
   }

   public String toString() {
      return "\nCustomer Name :\t" + this.customerName
         + "\nAddress :\t" + this.address
         + "\nDate of Birth :\t" + this.customerDob
         + "\nBalance :\t" + this.balance;
   }
}
