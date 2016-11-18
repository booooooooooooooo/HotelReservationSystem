public interface ReceiptFormatter{
  String formatHeader();
  String formatLineItem(Order order);
  String formatFooter();
}
