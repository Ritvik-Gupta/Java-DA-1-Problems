package app.ArraySort;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import services.Console.Console;
import services.Priority.IPriority;
import services.Priority.PriorityType;

public class ArraySort<T> {
   private final ArrayList<T> arr;
   private final IPriority<T> calculate;

   public ArraySort(IPriority<T> calculate) {
      this.arr = new ArrayList<>();
      this.calculate = calculate;
   }

   public void push(T value) {
      this.arr.add(value);
   }

   private void swap(int posA, int posB) {
      T temp = this.arr.get(posB);
      this.arr.set(posB, this.arr.get(posA));
      this.arr.set(posA, temp);
   }

   private PriorityType calcPriority(int posA, int posB) {
      return this.calculate.priority(this.arr.get(posA), this.arr.get(posB));
   }

   private PriorityType calcPriority(T elmA, int posB) {
      return this.calculate.priority(elmA, this.arr.get(posB));
   }

   private PriorityType calcPriority(int posA, T elmB) {
      return this.calculate.priority(this.arr.get(posA), elmB);
   }

   private void selectionSort(SortOrder sortOrder) {
      int size = this.arr.size();
      for (int posA = 0; posA < size - 1; ++posA) {
         int minIdx = posA;
         for (int posB = posA + 1; posB < size; ++posB)
            if (this.calcPriority(posB, minIdx) == sortOrder.usingPriority)
               minIdx = posB;
         this.swap(posA, minIdx);
      }
   }

   private void bubbleSort(SortOrder sortOrder) {
      int size = this.arr.size();
      for (int posA = 0; posA < size - 1; ++posA)
         for (int posB = 0; posB < size - posA - 1; ++posB)
            if (this.calcPriority(posB + 1, posB) == sortOrder.usingPriority)
               this.swap(posB, posB + 1);
   }

   private void insertionSort(SortOrder sortOrder) {
      int size = this.arr.size();
      for (int posA = 1; posA < size; ++posA) {
         T key = this.arr.get(posA);
         int posB = posA - 1;
         while (posB >= 0 && this.calcPriority(key, posB) == sortOrder.usingPriority) {
            this.arr.set(posB + 1, this.arr.get(posB));
            posB = posB - 1;
         }
         this.arr.set(posB + 1, key);
      }
   }

   private void quickSort(int lower, int upper, SortOrder sortOrder) {
      Stack<List<Integer>> boundsStack = new Stack<>();
      boundsStack.push(List.of(lower, upper));

      while (boundsStack.size() > 0) {
         List<Integer> bounds = boundsStack.pop();
         int lowerBound = bounds.get(0);
         int upperBound = bounds.get(1);

         if (lowerBound < upperBound) {
            T pivot = this.arr.get(upperBound);
            int posA = lowerBound - 1;

            for (int posB = lowerBound; posB < upperBound; ++posB)
               if (this.calcPriority(posB, pivot) == sortOrder.usingPriority) {
                  ++posA;
                  this.swap(posA, posB);
               }
            this.swap(posA + 1, upperBound);
            int partition = posA + 1;

            boundsStack.push(List.of(lowerBound, partition - 1));
            boundsStack.push(List.of(partition + 1, upperBound));
         }
      }
   }

   public void sort(SortAlgo sortAlgo, SortOrder sortOrder) {
      switch (sortAlgo) {
      case SELECTION:
         this.selectionSort(sortOrder);
         break;
      case BUBBLE:
         this.bubbleSort(sortOrder);
         break;
      case INSERTION:
         this.insertionSort(sortOrder);
         break;
      case QUICK:
         this.quickSort(0, this.arr.size() - 1, sortOrder);
         break;
      }
   }

   public static void main(String[] args) {
      List<Person> list = List.of(
         new Person("abc", 1),new Person("abc", 3),new Person("a", 5),new Person("abc", 2),
         new Person("a", 2),new Person("ab", 1),new Person("ab", 2),new Person("abc", 4),
         new Person("ab", 3),new Person("abc", 2),new Person("XYZ", 1),new Person("ab", 3)
      );
      Console.println(list);
      Console.log("\n");

      SortAlgo.iterate.forEach(sortAlgo -> {
         ArraySort<Person> array = new ArraySort<>((elmA, elmB) -> {
            if (elmA.name.length() > elmB.name.length())
               return PriorityType.HIGH;
            if (elmA.name.length() < elmB.name.length())
               return PriorityType.LOW;
            if (elmA.name.equals(elmB.name)) {
               if (elmA.age > elmB.age)
                  return PriorityType.LOW;
               if (elmA.age < elmB.age)
                  return PriorityType.HIGH;
            }
            return PriorityType.EQUAL;
         });
         list.forEach(array::push);
         array.sort(sortAlgo, SortOrder.DESC);
         Console.log("Sort Type :\t", sortAlgo, "\n");
         Console.println(array.arr);
         Console.log("\n");
      });
   }
}

class Person {
   public String name;
   public int age;

   public Person(String name, int age) {
      this.name = name;
      this.age = age;
   }

   public String toString() {
      return "Person :\t'" + this.name + "'\t->\t" + this.age;
   }
}
