import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.*;

import java.util.*;

public class PanelDone extends PanelPrototype {

  private final ArrayList<Order> reservation;


  public PanelDone(Model m, View v, ArrayList<Order> res ) {
    super(m, v);
    //Initialize instance variables
    this.reservation = res;

    //TODO



  }
}
