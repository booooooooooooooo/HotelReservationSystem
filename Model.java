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


  public Model(String fp){
    this.view = null;
    this.filePath = fp;
    loadDateFromFile();



  }
  /**
    * Load date from file to instanciate orderDate， guestData and roomData
    * If file does not exist, intialie orderDate and guestData empty ArrayList, initialize roomData with 20 roomes.
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

  private void initializeRoomData(){
    roomData = new ArrayList<Room>();
    for(int i = 0; i < 10; i++){
      roomData.add(new Room(i, 200));
    }
    for(int i = 10; i < 20; i++){
      roomData.add(new Room(i, 80));
    }
  }

  public void attach(View v){
    this.view = v;
  }

  public ArrayList<Order> getOrderData(){
    return orderData;
  }
  public ArrayList<Guest> getGuestData(){
    return guestData;
  }
  public ArrayList<Room> getRoomData(){
    return roomData;
  }
}
