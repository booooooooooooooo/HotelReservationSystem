public class ComprehensiveFormatter implements ReceiptFormatter{
  //With a comprehensive formation, the receipt shows user id, name, and all valid reservations made by this user with corresponding total amount due.
  public String formatHeader(){
    return "*******Comprehensive Receipt*******\n";
  };
  public String formatLineItem(Order order){
    return order.toString();
  };
  public String formatFooter(){
    return "Thank you for choosing this hotel!\n";
  };
}
