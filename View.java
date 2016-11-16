import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class View extends JFrame{
  private Model model;
  private final int FRAME_WIDTH = 1000;
  private final int FRAME_HEIGHT = 600;
  private MyPanel currentPanel;
  public View(Model m){
    this.model = m;


    setSize(FRAME_WIDTH, FRAME_HEIGHT);
    currentPanel = new ManagerOrGuestPanel(model, this);
    add(currentPanel);
    setLayout(new FlowLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
}
