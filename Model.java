import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class Model{
  // private ArrayList<Guest> guest_L;
  // private ArrayList<Manager> manager_L;
  // private ArrayLIst<Room> room_L;
  private View view;

  public Model(String filePath){
    // this.guest_L = null;
    // this.manager_L = null;
    // this.room_L = null;
    this.view = null;
    //TODO Load date from file and initialize guest_L, manager_L and room_L
  }

  public void attach(View v){
    this.view = v;
  }
}
