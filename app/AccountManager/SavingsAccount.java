package app.AccountManager;

import services.Constrained.Constrained;

public final class SavingsAccount extends BankAccount {
   public SavingsAccount(
      String customerName, String address, Constrained customerDob, 
      Constrained accountNum, long balance
   ) {
      super(customerName, address, customerDob, accountNum, balance);
   }

   public void deposit(long amount) {
      this.balance += amount;
   }

   public void withdraw(long amount) throws Exception {
      if (this.balance < amount)
         throw new Exception("Not enough Balance Left");
      this.balance -= amount;
   }
}
