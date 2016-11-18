import java.util.*;

public class TestModel {
  public static void main(String[] args) {
    Model model = new Model("./hotelData");

    log("\n\n=======Test Guest======");
    // model.createGuest("a", "a", "test");
    // model.setCurrentGuestID("bo.nov29@gmail.com");
    log(model.getGuestData());
    // log(model.getCurrentGuestID());
    log(model.isGuest("bo.nov29@gmail.com", "00000000"));

    // log("\n\n=======Test Room======");
    // log(model.getRoomData());
    log( model.getAvailableRoomIDByDateAndPrice(new GregorianCalendar(2016, 1, 1), new GregorianCalendar(2016, 11, 20), 0, 201) );
    //
    // log("\n\n=======Test Order======");
    // // model.createOrder(new GregorianCalendar(), new GregorianCalendar(2016, 10, 20), 1);
    // // model.createOrder(new GregorianCalendar(), new GregorianCalendar(2016, 10, 20), 2);
    // log(model.getOrderData());
    // log("\n");
    // log(model.getOrderOfCurrentGuestID() );
    // log("\n");
    // // model.deleteOrder(model.getOrderData().get(0));
    // log(model.getOrderData());
    // log("\n");


    // model.writeDataToFile();
  }

  public static void log(Object o) { System.out.println(o); }
}
