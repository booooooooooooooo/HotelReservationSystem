import java.util.*;
public class Receipt{
  private ArrayList<Order> reservation;
  public Receipt(ArrayList<Order> res){
    this.reservation = res;
  }
  public String format(ReceiptFormatter formatter){
    String result = formatter.formatHeader();
    for(int i = 0; i < reservation.size(); i++){
      result += formatter.formatLineItem(reservation.get(i));
    }
    result += formatter.formatFooter();
    return result;
  }
}
