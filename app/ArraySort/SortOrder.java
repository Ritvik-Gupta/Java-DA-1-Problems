package app.ArraySort;

import services.Priority.PriorityType;

public enum SortOrder {
   ASC(PriorityType.LOW), DESC(PriorityType.HIGH);

   public final PriorityType usingPriority;

   private SortOrder(PriorityType usingPriority) {
      this.usingPriority = usingPriority;
   }
}
