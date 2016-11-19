import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class PanelManagerOrGuest extends PanelPrototype {
  public PanelManagerOrGuest(Model m, View v) {
    super(m, v);
    JButton buttonManager = new JButton("Manager");
    JButton buttonGuest = new JButton("Guest");

    buttonManager.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        getView().displayPanelManagerDoWhat();
      }
    });

    buttonGuest.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        getView().displayPanelSign();
      }
    });

    add(buttonManager);
    add(buttonGuest);
  }
}
