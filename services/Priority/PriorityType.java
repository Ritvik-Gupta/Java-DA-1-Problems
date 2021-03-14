package services.Priority;

public enum PriorityType {
   LOW(-1), EQUAL(0), HIGH(1);

   public final int sortValue;

   private PriorityType(int sortValue) {
      this.sortValue = sortValue;
   }
}
