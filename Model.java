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

//TODO: call drawOnUpdatedData() in every mutators

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
        // TODO
      } catch (ClassNotFoundException e2) {
        // TODO
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
      // TODO
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
    * Mutator of Order Data.
    */
  public void createOrder(Calendar checkInDate, Calendar checkOutDate,
                          int roomID) {
    orderData.add(new Order(checkInDate, checkOutDate, currentGuestID, roomID));
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
  }

  /**
    * Accessor of Room Data
    */
  public ArrayList<Room> getRoomData() { return roomData; }
  /**
    * Accessor of Room Data
    */
  public ArrayList<Integer> getAvailableRoomIDByDateAndPrice(Calendar checkInDate,
                                                    Calendar checkOutDate,
                                                    Integer lowerPriceBound,
                                                    Integer higherPriceBound) {

    //Exclude null case
    if(checkOutDate == null || checkOutDate == null || lowerPriceBound == null || higherPriceBound == null) return new ArrayList<Integer>();

    // Create hashSet
    HashSet<Integer> availableRoomID = new HashSet<Integer>();

    // Put all rooms that satisfy price range into hashSet
    for (Room room : roomData) {
      if (lowerPriceBound <= room.getRoomPrice() &&
          room.getRoomPrice() <= higherPriceBound)
        availableRoomID.add(room.getRoomId());
    }

    // Delete the rooms that are occupied in target date interval
    for (Order order : orderData) {
      if (isOverlapped(order.getCheckInDate(), order.getCheckOutDate(),
                       checkInDate, checkOutDate))
        availableRoomID.remove(order.getRoomId());
    }
    return new ArrayList<Integer>(availableRoomID);
  }
  /**
    * Untility.
    */
  private boolean isOverlapped(Calendar in1, Calendar out1, Calendar in2,
                               Calendar out2) {
    if ((in1.compareTo(in2) < 0 && out1.compareTo(in2) < 0) || (in1.compareTo(out2) > 0 && out1.compareTo(out2) > 0))
      return false;
    else
      return true;
  }
}
