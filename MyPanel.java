import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class MyPanel extends JPanel{
  private Model model;
  private View view;
  public MyPanel(Model m, View v){
    this.model = model;
    this.view = view;
  }
}
