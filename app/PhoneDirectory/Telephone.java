package app.PhoneDirectory;

import services.Constrained.Constrained;
import services.Constrained.IConstrainedFor;

public abstract class Telephone {
   public static final IConstrainedFor constrainedPhoneNum = Constrained.compile(
      "^\\d{3}(?<D>[-.])\\d{3}\\k<D>\\d{4}$",
      "Invalid Phone Number format",
      "XXX-XXX-XXXX or XXX.XXX.XXXX ( where X is a digit [0-9] )"
   );

   public String customerName;
   public Constrained phoneNum;

   public Telephone(String customerName, Constrained phoneNum) {
      this.customerName = customerName;
      this.phoneNum = phoneNum;
   }
}
