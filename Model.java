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


public class Model{
  private View view;
  private String filePath;
  private ArrayList<Order> orderData;
  private ArrayList<Guest> guestData;
  private ArrayList<Room> roomData;

  public String currentGuestID; //active guest

  /**
    * Constructor
    */
  public Model(String fp){
    this.view = null;
    this.filePath = fp;
    loadDateFromFile();
    currentGuestID = null;
  }
  /**
    * Initialize orderDate，guestData and roomData using file data.
    * If file does not exist, intialie orderDate and guestData as empty ArrayList, initialize roomData with 20 specific roomes.
    */
  private void loadDateFromFile(){
    File f = new File(filePath);
    if (!f.exists() && !f.isDirectory()) {
      orderData = new ArrayList<Order>();
      guestData = new ArrayList<Guest>();
      initializeRoomData();
    } else {
      try {
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        orderData = ( ArrayList<Order> )ois.readObject();
        guestData = ( ArrayList<Guest>  )ois.readObject();
        roomData = ( ArrayList<Room>  )ois.readObject();
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
  private void initializeRoomData(){
    roomData = new ArrayList<Room>();
    for(int i = 0; i < 10; i++){
      roomData.add(new Room(i, 200));
    }
    for(int i = 10; i < 20; i++){
      roomData.add(new Room(i, 80));
    }
  }
  /**
    * Initialize view instance variable..
    */
  public void attach(View v){
    this.view = v;
  }


  /**
    * Write modified hotel data to file.
    */
  public void writeDataToFile(){
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
  public ArrayList<Guest> getGuestData(){
    return guestData;
  }
  /**
    * Accessor of Guest Data
    */
  public String getCurrentGuestID(){
    return currentGuestID;
  }
  /**
    * Accessor of Guest Data
    */
  public boolean isGuest(String guestID, String password){
    //TODO: check is valid guest or not. If is vailid guest, update currentGuestID.
  }
  /**
    * Mutator of Guest Data
    */
  public void setCurrentGuestID(String newGuestID){
    this.currentGuestID = newGuestID;
  }
  /**
    * Mutator of Guest Data
    */
  public void createGuest(String guestID, String password, String guestName){
    //TODO
  }


  /**
    * Accessor of Order Data
    */
  public ArrayList<Order> getOrderData(){
    return orderData;
  }
  /**
    * Accessor of Order Data
    */
  public ArrayList<Order> getOrderOfCurrentGuestID(){
    //TODO
  }
  /**
    * Mutator of Order Data
    */
  public void createOrder(Calendar checkInDate, Calendar checkOutDate, int roomID){
    //TODO
  }
  /**
    * Mutator of Order Data
    */
  public void deleteOrder(Order odr){
    //TODO
  }


  /**
    * Accessor of Room Data
    */
  public ArrayList<Room> getRoomData(){
    return roomData;
  }
  /**
    * Accessor of Room Data
    */
  public int[] getAvailableRoomIDByDate(Calendar checkInDate, Calendar checkOutDate, int price){
    //TODO
  }

}
