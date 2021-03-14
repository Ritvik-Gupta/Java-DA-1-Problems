package app.PhoneDirectory;

import services.Constrained.Constrained;

public final class TelephoneIndex extends Telephone {
   public TelephoneIndex(String customerName, Constrained phoneNo) {
      super(customerName, phoneNo);
   }

   public void updateName(String customerName) {
      this.customerName = customerName;
   }

   public void updatePhoneNo(Constrained phoneNo) {
      this.phoneNum = phoneNo;
   }
}
