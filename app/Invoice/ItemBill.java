package app.Invoice;

public final class ItemBill extends Item {
   public ItemBill(String itemName, double price) throws Exception {
      super(itemName, price);
   }

   public String toString() {
      return "Quantity :\t" + this.getQuantity()
         + "\nPrice :\t" + this.getPrice()
         + "\nTotal Cost :\t" + this.totalCost();
   }
}
