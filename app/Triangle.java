package app;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Scanner;

import org.w3c.dom.ranges.RangeException;

import services.Console.Console;

public final class Triangle {
   private static Scanner S = new Scanner(System.in);

   private static HashMap<String, IGridDrawable> triangles = new HashMap<>();
   private static HashMap<String, IFillTransform> fillTypes = new HashMap<>();

   static {
      fillTypes.put("Constant", (__, ___, ____) -> '*');
      fillTypes.put("Incremental-Row", (__, yIdx, ___) -> (char) (yIdx + 97));
      fillTypes.put("Incremental-Column", (xIdx, __, ___) -> (char) (xIdx + 97));
      fillTypes.put("Incremental", (__, ___, currentPos) -> (char) (currentPos + 97));

      triangles.put("Top-Left", (gridSize, fill) -> {
         Grid2D<Character> grid = new Grid2D<>(gridSize);
         int currentPos = 0;
         for (int xIdx = 0; xIdx < gridSize; ++xIdx) {
            for (int yIdx = 0; yIdx < gridSize - xIdx; ++yIdx)
               grid.get(xIdx).addLast(fill.transform(xIdx, yIdx, currentPos++));

            for (int yIdx = 0; yIdx < xIdx; ++yIdx)
               grid.get(xIdx).addLast(' ');
         }
         return grid;
      });

      triangles.put("Top-Right", (gridSize, fill) -> {
         Grid2D<Character> grid = new Grid2D<>(gridSize);
         int currentPos = 0;
         for (int xIdx = 0; xIdx < gridSize; ++xIdx) {
            for (int yIdx = 0; yIdx < xIdx; ++yIdx)
               grid.get(xIdx).addLast(' ');

            for (int yIdx = 0; yIdx < gridSize - xIdx; ++yIdx)
               grid.get(xIdx).addLast(fill.transform(xIdx, yIdx, currentPos++));
         }
         return grid;
      });

      triangles.put("Top-Center", (gridSize, fill) -> {
         Grid2D<Character> grid = new Grid2D<>(gridSize);
         int currentPos = 0;
         for (int xIdx = 0; xIdx < gridSize; ++xIdx) {
            for (int yIdx = 0; yIdx < xIdx; ++yIdx)
               grid.get(xIdx).addLast(' ');

            for (int yIdx = 0; yIdx < 2 * (gridSize - xIdx) - 1; ++yIdx)
               grid.get(xIdx).addLast(yIdx % 2 == 0 ? fill.transform(xIdx, yIdx / 2, currentPos++) : ' ');

            for (int yIdx = 0; yIdx < xIdx + 1 - gridSize; ++yIdx)
               grid.get(xIdx).addLast(' ');
         }
         return grid;
      });

      triangles.put("Bottom-Left", (gridSize, fill) -> {
         Grid2D<Character> grid = new Grid2D<>(gridSize);
         int currentPos = 0;
         for (int xIdx = 0; xIdx < gridSize; ++xIdx) {
            for (int yIdx = 0; yIdx <= xIdx; ++yIdx)
               grid.get(xIdx).addLast(fill.transform(xIdx, yIdx, currentPos++));

            for (int yIdx = 0; yIdx < gridSize - 1 - xIdx; ++yIdx)
               grid.get(xIdx).addLast(' ');
         }
         return grid;
      });

      triangles.put("Bottom-Right", (gridSize, fill) -> {
         Grid2D<Character> grid = new Grid2D<>(gridSize);
         int currentPos = 0;
         for (int xIdx = 0; xIdx < gridSize; ++xIdx) {
            for (int yIdx = 0; yIdx < gridSize - xIdx - 1; ++yIdx)
               grid.get(xIdx).addLast(' ');

            for (int yIdx = 0; yIdx < xIdx + 1; ++yIdx)
               grid.get(xIdx).addLast(fill.transform(xIdx, yIdx, currentPos++));
         }
         return grid;
      });

      triangles.put("Bottom-Center", (gridSize, fill) -> {
         Grid2D<Character> grid = new Grid2D<>(gridSize);
         int currentPos = 0;
         for (int xIdx = 0; xIdx < gridSize; ++xIdx) {
            for (int yIdx = 0; yIdx < gridSize - 1 - xIdx; ++yIdx)
               grid.get(xIdx).addLast(' ');

            for (int yIdx = 0; yIdx < 2 * xIdx + 1; ++yIdx)
               grid.get(xIdx).addLast(yIdx % 2 == 0 ? fill.transform(xIdx, yIdx / 2, currentPos++) : ' ');

            for (int yIdx = 0; yIdx < gridSize - 1 - xIdx; ++yIdx)
               grid.get(xIdx).addLast(' ');
         }
         return grid;
      });

      triangles.put("Diamond", (gridSize, fill) -> {
         Grid2D<Character> grid = triangles.get("Bottom-Center").createGrid(gridSize, fill);
         triangles.get("Top-Center").createGrid(gridSize - 1, fill).forEach(row -> {
            row.addFirst(' ');
            grid.addLast(row);
         });
         return grid;
      });
   }

   private static IGridDrawable getValidTriangleDraw(String typeofTriangle) throws RangeException {
      for (Entry<String, IGridDrawable> triangleType : triangles.entrySet())
         if (triangleType.getKey().equalsIgnoreCase(typeofTriangle))
            return triangleType.getValue();
      throw new RangeException((short) -1, "Invalid Typeof Triangle Specified");
   }

   private static IFillTransform getValidFillTransform(String typeofFill) throws RangeException {
      for (Entry<String, IFillTransform> fillType : fillTypes.entrySet())
         if (fillType.getKey().equalsIgnoreCase(typeofFill))
            return fillType.getValue();
      throw new RangeException((short) -1, "Invalid Typeof Fill Specified");
   }

   private static void drawGrid(Grid2D<Character> grid) {
      grid.forEach(row -> {
         row.forEach(chr -> Console.print("\t" + chr));
         Console.print("\n");
      });
   }

   public static void main(String[] args) throws RangeException {
      Console.print("\nEnter the Maximum Size of Triangle :\t");
      int gridSize = S.nextInt();

      Console.print("Enter a Valid Typeof Triangle :\t");
      IGridDrawable gridDrawable = getValidTriangleDraw(S.next());

      Console.print("Enter a Valid Typeof Fill :\t");
      IFillTransform fill = getValidFillTransform(S.next());

      Console.print("\n");
      drawGrid(gridDrawable.createGrid(gridSize, fill));
      Console.print("\n");

      // triangles.forEach((key, map) -> {
      // Console.println("Current Key :\t" + key + "\n\n");
      // drawGrid(map.createGrid(gridSize, fill));
      // Console.print("\n");
      // });
   }
}

class Grid2D<T> extends LinkedList<LinkedList<T>> {
   private static final long serialVersionUID = 0;

   public Grid2D(int gridSize) {
      for (int pos = 0; pos < gridSize; ++pos)
         this.addLast(new LinkedList<>());
   }
}

@FunctionalInterface
interface IGridDrawable {
   Grid2D<Character> createGrid(int gridSize, IFillTransform fill);
}

@FunctionalInterface
interface IFillTransform {
   char transform(int xIdx, int yIdx, int currentPos);
}
