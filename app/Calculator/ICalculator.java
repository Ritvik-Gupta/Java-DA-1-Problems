package app.Calculator;

import services.Console.Console;

public interface ICalculator<T extends Number> {
   T add(T val);
   T sub(T val);
   T multiply(T val);
   T divide(T val);

   default void print(T num) {
      Console.log("ADDITION : [ + ", num, " ] :\t", this.add(num), "\n");
      Console.log("SUBSTRACTION : [ - ", num, " ] :\t", this.sub(num), "\n");
      Console.log("MULTIPLY : [ * ", num, " ] :\t", this.multiply(num), "\n");
      Console.log("DIVIDE : [ / ", num, " ] :\t", this.divide(num), "\n");
   }
}
