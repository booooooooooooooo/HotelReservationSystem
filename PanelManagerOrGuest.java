import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class PanelManagerOrGuest extends PanelPrototype {
  public PanelManagerOrGuest(Model m, View v) {
    super(m, v);
    JButton ButtonManager = new JButton("manager");
    JButton ButtonGuest = new JButton("guest");

    ButtonManager.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO
      }
    });

    ButtonGuest.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO
      }
    });

    add(ButtonManager);
    add(ButtonGuest);
  }
}
