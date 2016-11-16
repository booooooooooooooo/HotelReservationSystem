import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class Model{
  // private ArrayList<Guest> guestData;
  // private ArrayList<Manager> manageData;
  // private ArrayLIst<Room> roomData;
  private View view;
  private String filePath;

  public Model(String fp){
    this.view = null;
    this.filePath = fp;
    //TODO Load date from file to instanciate variables
  }
  
  public void attach(View v){
    this.view = v;
  }
}
