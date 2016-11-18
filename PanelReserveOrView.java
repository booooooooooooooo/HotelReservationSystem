import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class PanelReserveOrView extends PanelPrototype {
  public PanelReserveOrView(Model m, View v) {
    super(m, v);

    JButton buttonReserve = new JButton("Make a Reservation");
    JButton buttonView = new JButton("View/Cancel a Reservation");

    buttonReserve.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        getView().displayPanelMakeReservation();
      }
    });

    buttonView.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //TODO
      }
    });

    add(buttonReserve);
    add(buttonView);
  }

}