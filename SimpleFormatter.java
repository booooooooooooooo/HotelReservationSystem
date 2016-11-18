public class SimpleFormatter implements ReceiptFormatter{
  //With a simple format, the receipt shows user id, name, and reserved rooms and corresponding total amount dues only made through this particular transaction.
  public String formatHeader(){
    return "***Simple Receipt***\n";
  };
  public String formatLineItem(Order order){
    return "Guest: " + order.getGuestID() + "  "
        + "Room: " + order.getRoomID() + "\n";
  };
  public String formatFooter(){
    return "Thank you for choosing this hotel!\n";
  };
}
