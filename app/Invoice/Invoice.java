package app.Invoice;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import services.Console.Console;

public final class Invoice {
   private static final Scanner S = new Scanner(System.in);

   private static final Pattern incrementRegex = Pattern.compile("^INCREASE\\s\\w+$");
   private static final Pattern changeRegex = Pattern.compile("^CHANGE\\s\\w+\\s\\d+$");

   public static ItemBill createItem() throws Exception {
      Console.log("\nEnter the following details to create an Item Bill :\n");

      Console.log("\tItem Name :\t");
      String itemName = S.nextLine();

      Console.log("\tPrice of Item :\t");
      double price = S.nextDouble();

      S.nextLine();
      return new ItemBill(itemName, price);
   }

   public static void displayItemBill(ItemBill item) {
      Console.log("\n--- Item Bill :\t", item.getItemName(), " ---\n", item);
   }

   public static void main(String[] args) {
      HashMap<String, ItemBill> shoppingCart = new HashMap<>();

      Console.log("\nItem Bill Invoice Generator :\n");
      Console.log("\n\t(1) <CREATE> :\tCreate a new Item Bill");
      Console.log("\n\t(5) <PRINT> :\tPrint the complete Bill Invoice");
      Console.log("\n\t(2) <INCREASE #S> :\tIncrease the Quantity of an Item with Name S");
      Console.log("\n\t(2) <CHANGE #S #N> :\tChange the Quantity of an Item with Name S");
      Console.log("\n\t(6) <END> :\tEnd the Simulation");

      while (true) {
         Console.log("\n\nEnter a Command ::\t");
         String command = S.nextLine();

         try {
            if (command.equals("END")) {
               Console.log("\nTerminating ...\n\n");
               break;
            }

            else if (command.equals("PRINT"))
               shoppingCart.forEach((__, item) -> displayItemBill(item));

            else if (command.equals("CREATE")) {
               ItemBill item = createItem();

               if (!shoppingCart.containsKey(item.getItemName()))
                  shoppingCart.put(item.getItemName(), item);
               else {
                  ItemBill containedItem = shoppingCart.get(item.getItemName());
                  if (containedItem.getPrice() != item.getPrice())
                     throw new Exception("Item with Same Name Found in Cart but Prices are unequal");
                  containedItem.increaseQuantity();
                  item = containedItem;
               }
               displayItemBill(item);
            }

            else if (incrementRegex.matcher(command).matches()) {
               String itemName = command.substring(9);

               if (!shoppingCart.containsKey(itemName))
                  throw new Exception("Specified Item Name is not in the Cart");
               ItemBill item = shoppingCart.get(itemName);
               item.increaseQuantity();
               displayItemBill(item);
            }

            else if (changeRegex.matcher(command).matches()) {
               String[] tokens = command.split(" ");
               String itemName = tokens[1];
               int updatedQuantity = Integer.parseInt(tokens[2]);

               if (!shoppingCart.containsKey(itemName))
                  throw new Exception("Specified Item Name is not in the Cart");
               ItemBill item = shoppingCart.get(itemName);
               item.setQuantity(updatedQuantity);
               displayItemBill(item);
            }

            else
               throw new Exception("Invalid Command Specified");
         } catch (Exception err) {
            Console.log("\nError :\t", err.getMessage());
         }
      }
   }
}
