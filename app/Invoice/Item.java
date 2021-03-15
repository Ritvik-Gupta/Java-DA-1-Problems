package app.Invoice;

public class Item {
   private String itemName;
   private int quantity;
   private double price;

   public Item(String itemName, double price) throws Exception {
      this.itemName = itemName;
      this.quantity = 1;
      if (price < 0)
         throw new Exception("Minimum Price allowed is 0");
      this.price = price;
   }

   public double totalCost() {
      return this.price * this.quantity;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public int getQuantity() {
      return this.quantity;
   }

   public void increaseQuantity() {
      ++this.quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public double getPrice() {
      return this.price;
   }

   public void setPrice(double price) {
      this.price = price;
   }
}
