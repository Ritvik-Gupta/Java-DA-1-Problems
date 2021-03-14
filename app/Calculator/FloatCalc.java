package app.Calculator;

import java.util.Scanner;

import services.Console.Console;

public class FloatCalc implements ICalculator<Float> {
   private static final Scanner S = new Scanner(System.in);

   private final Float value;

   public FloatCalc(Float value) {
      this.value = value;
   }

   public Float add(Float val) {
      return this.value + val;
   }

   public Float sub(Float val) {
      return this.value - val;
   }

   public Float multiply(Float val) {
      return this.value * val;
   }

   public Float divide(Float val) {
      return this.value / val;
   }

   public static void main(String[] args) {
      Console.log("\nEnter an Float Value as the base :\t");
      FloatCalc calc = new FloatCalc(S.nextFloat());

      Console.log("Enter another Float Value to perform operations :\t");
      Float num = S.nextFloat();

      calc.print(num);
   }
}
