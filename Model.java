import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;


public class Model {
  private View view;
  private String filePath;
  private ArrayList<Order> orderData;
  private ArrayList<Guest> guestData;
  private ArrayList<Room> roomData;

  public String currentGuestID; // active guest


  /**
    * Constructor
    */
  public Model(String fp) {
    this.view = null;
    this.filePath = fp;
    loadDateFromFile();
    currentGuestID = null;
  }
  /**
    * Initialize orderDate，guestData and roomData using file data.
    * If file does not exist, intialie orderDate and guestData as empty
   * ArrayList, initialize roomData with 20 specific roomes.
    */
  private void loadDateFromFile() {
    File f = new File(filePath);
    if (!f.exists() && !f.isDirectory()) {
      orderData = new ArrayList<Order>();
      guestData = new ArrayList<Guest>();
      initializeRoomData();
    } else {
      try {
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        orderData = (ArrayList<Order>)ois.readObject();
        guestData = (ArrayList<Guest>)ois.readObject();
        roomData = (ArrayList<Room>)ois.readObject();
        ois.close();
        fis.close();
      } catch (IOException e1) {
        ;
      } catch (ClassNotFoundException e2) {
        ;
      }
    }
  }
  /**
    * Initalize roomData instance variable.Called by loadDateFromFile()
    */
  private void initializeRoomData() {
    roomData = new ArrayList<Room>();
    for (int i = 0; i < 10; i++) {
      roomData.add(new Room(i, 200));
    }
    for (int i = 10; i < 20; i++) {
      roomData.add(new Room(i, 80));
    }
  }
  /**
    * Initialize view instance variable..
    */
  public void attach(View v) { this.view = v; }

  /**
    * Write modified hotel data to file.
    */
  public void writeDataToFile() {
    try {
      FileOutputStream fos = new FileOutputStream(filePath);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(orderData);
      oos.writeObject(guestData);
      oos.writeObject(roomData);
      oos.close();
      fos.close();
    } catch (IOException e) {
      ;
    }
  }

  /**
    * Accessor of Guest Data
    */
  public ArrayList<Guest> getGuestData() { return guestData; }
  /**
    * Accessor of Guest Data
    */
  public String getCurrentGuestID() { return currentGuestID; }
  /**
    * Verify guest. Check is valid guest or not. If is vailid guest, update
   * currentGuestID.
    */
  public boolean isGuest(String guestID, String password) {
    if(guestData == null){
      return false;
    }

    for (int i = 0; i < guestData.size(); i++) {
      if (guestData.get(i).getGuestID().equals(guestID) &&
          guestData.get(i).matchPassword(password)) {
        // this.currentGuestID = guestID;
        return true;
      }
    }
    return false;
  }
  /**
    * Mutator of Guest Data
    */
  public void setCurrentGuestID(String newGuestID) {
    this.currentGuestID = newGuestID;
  }
  /**
    * Mutator of Guest Data
    */
  public void createGuest(String guestID, String password, String guestName) {
    guestData.add(new Guest(guestID, password, guestName));
    view.drawOnUpdatedData();
  }

  /**
    * Accessor of Order Data
    */
  public ArrayList<Order> getOrderData() { return orderData; }
  /**
    * Accessor of Order Data
    */
  public ArrayList<Order> getOrderOfCurrentGuestID() {
    ArrayList<Order> result = new ArrayList<Order>();
    for (Order order : orderData) {
      if (order.getGuestID() == currentGuestID)
        result.add(order);
    }
    return result;
  }
  /**
    * Accessor of Order Data
    */
  public ArrayList<Order> getOrderOfRoomID(int roomID){
    ArrayList<Order> result = new ArrayList<Order>();
    for(Order order : orderData){
      if(order.getRoomID() == roomID) result.add(order);
    }
    return result;
  }
  /**
    * Accessor of Order Data
    */
  public ArrayList<Order> getOrderOfDate(Calendar date){
    if(date == null) return new ArrayList<Order>();
    ArrayList<Order> result = new ArrayList<Order>();
    for (Order order : orderData) {
      if (order.getCheckInDate().compareTo(date) <= 0 && order.getCheckOutDate().compareTo(date) >= 0)
        result.add(order);
    }
    return result;
  }

  /**
    * Mutator of Order Data.
    */
  public Order createOrder(Calendar checkInDate, Calendar checkOutDate,
                          int roomID) {
    //TODO: check conflit
    Order newOrder = new Order(checkInDate, checkOutDate, currentGuestID, roomID);
    orderData.add(newOrder);
    view.drawOnUpdatedData();
    return newOrder;
  }
  /**
    * Mutator of Order Data
    */
  public void deleteOrder(Order odr) {
    for (int i = 0; i < orderData.size(); i++) {
      if (orderData.get(i) == odr) {
        orderData.remove(i);
        break;
      }
    }
    view.drawOnUpdatedData();
  }

  /**
    * Accessor of Room Data
    */
  public ArrayList<Room> getRoomData() { return roomData; }
  /**
    * Accessor of roomData
    */
  public ArrayList<Integer> getAvailableRoomIDByDate(Calendar date) {

    if(date == null) return new ArrayList<Integer>();

    HashSet<Integer> availableRoomID = new HashSet<Integer>();

    // Put all rooms that satisfy price range into hashSet
    for (Room room : roomData) {
      availableRoomID.add(room.getRoomID());
    }

    // Delete the rooms that are occupied in target date interval
    for (Order order : orderData) {
      if (order.getCheckInDate().compareTo(date) <= 0 && date.compareTo( order.getCheckOutDate() ) <= 0)
        availableRoomID.remove(order.getRoomID());
    }
    return new ArrayList<Integer>(availableRoomID);
  }
  /**
    * Accessor of roomData
    */
  public ArrayList<Integer> getReservedRoomIDByDate(Calendar date) {
    if(date == null) return new ArrayList<Integer>();

    HashSet<Integer> reservedRoomID = new HashSet<Integer>();
    for (Order order : orderData) {
      reservedRoomID.add(order.getRoomID());
    }
    return new ArrayList<Integer>(reservedRoomID);

  }
  /**
    * Accessor of Room Data
    */

  public ArrayList<Integer> getAvailableRoomIDByDateAndPrice(Calendar checkInDate,
                                                    Calendar checkOutDate,
                                                    Integer lowerPriceBound,
                                                    Integer higherPriceBound) {

    //Exclude null case
    if(checkOutDate == null || checkOutDate == null || lowerPriceBound == null || higherPriceBound == null) return new ArrayList<Integer>();

    if(checkInDate.compareTo(new GregorianCalendar()) < 0) return new ArrayList<Integer>();
    
    long difference = checkOutDate.getTimeInMillis() - checkInDate.getTimeInMillis();
    int days = (int) (difference/ (1000*60*60*24));
    if(days < 0 || days > 60) return new ArrayList<Integer>();

    // Create hashSet
    HashSet<Integer> availableRoomID = new HashSet<Integer>();

    // Put all rooms that satisfy price range into hashSet
    for (Room room : roomData) {
      if (lowerPriceBound <= room.getRoomPrice() &&
          room.getRoomPrice() <= higherPriceBound)
        availableRoomID.add(room.getRoomID());
    }

    // Delete the rooms that are occupied in target date interval
    for (Order order : orderData) {
      if (isOverlapped(order.getCheckInDate(), order.getCheckOutDate(),
                       checkInDate, checkOutDate))
        availableRoomID.remove(order.getRoomID());
    }
    return new ArrayList<Integer>(availableRoomID);
  }
  /**
    * Untility.
    */
  private boolean isOverlapped(Calendar in1, Calendar out1, Calendar in2,
                               Calendar out2) {
    //TODO: check interval is valid
    if ((in1.compareTo(in2) < 0 && out1.compareTo(in2) < 0) || (in1.compareTo(out2) > 0 && out1.compareTo(out2) > 0))
      return false;
    else
      return true;
  }
}
