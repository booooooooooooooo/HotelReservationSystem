import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class View extends JFrame {
  private Model model;
  private final int FRAME_WIDTH = 1000;
  private final int FRAME_HEIGHT = 600;
  private PanelPrototype currentPanel;
  public View(Model m) {
    this.model = m;

    currentPanel = new PanelManagerOrGuest(model, this);
    getContentPane().add(currentPanel);

    setSize(FRAME_WIDTH, FRAME_HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public void displayPanelSign(){
      getContentPane().removeAll();
      currentPanel = new PanelSign(model, this);
      getContentPane().add(currentPanel);
      revalidate();
  }
}
