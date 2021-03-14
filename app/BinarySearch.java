package app;

import java.util.ArrayList;

import services.Console.Console;
import services.Priority.IPriority;
import services.Priority.PriorityType;

public final class BinarySearch<T> {
   private final ArrayList<T> arr;
   private final IPriority<T> calculate;

   public BinarySearch(IPriority<T> calculate) {
      this.arr = new ArrayList<>();
      this.calculate = calculate;
   }

   public void push(T value) {
      this.arr.add(value);
   }

   public void sort() {
      this.arr.sort((elmA, elmB) -> this.calculate.priority(elmA, elmB).sortValue);
   }

   private PriorityType calcPriority(int posA, T elmB) {
      return this.calculate.priority(this.arr.get(posA), elmB);
   }

   public Integer search(T elm) {
      int start = 0, end = this.arr.size() - 1;

      while (start <= end) {
         int mid = (start + end) / 2;
         PriorityType priority = this.calcPriority(mid, elm);

         if (priority == PriorityType.LOW)
            start = mid + 1;
         else if (priority == PriorityType.HIGH)
            end = mid - 1;
         else
            return mid;
      }
      return null;
   }

   public static void main(String[] args) {
      BinarySearch<Integer> binary = new BinarySearch<>((elmA, elmB) -> {
         if (elmA > elmB)
            return PriorityType.HIGH;
         if (elmA < elmB)
            return PriorityType.LOW;
         return PriorityType.EQUAL;
      });

      binary.push(1);
      binary.push(2);
      binary.push(-1);
      binary.push(8);
      binary.push(6);
      binary.push(100);
      binary.push(0);
      binary.push(-9);
      binary.push(-40);
      binary.push(35);

      Console.println(binary.arr);
      binary.sort();
      Console.println(binary.arr);
      Console.log(binary.search(0));
   }
}
