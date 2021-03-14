package app.Calculator;

import java.util.Scanner;

import services.Console.Console;

public class IntegerCalc implements ICalculator<Integer> {
   private static final Scanner S = new Scanner(System.in);

   private final Integer value;

   public IntegerCalc(Integer value) {
      this.value = value;
   }

   public Integer add(Integer val) {
      return this.value + val;
   }

   public Integer sub(Integer val) {
      return this.value - val;
   }

   public Integer multiply(Integer val) {
      return this.value * val;
   }

   public Integer divide(Integer val) {
      return this.value / val;
   }

   public static void main(String[] args) {
      Console.log("\nEnter an Integer Value as the base :\t");
      IntegerCalc calc = new IntegerCalc(S.nextInt());

      Console.log("Enter another Integer Value to perform operations :\t");
      Integer num = S.nextInt();

      calc.print(num);
   }
}
