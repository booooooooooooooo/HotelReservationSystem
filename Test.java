public class Test{
  public static void main(String[] args){
    Model model = new Model("./hotelData");
    System.out.println(model.getOrderData());
    System.out.println(model.getGuestData());
    System.out.println(model.getRoomData());
    model.writeDataToFile();
  }
}
