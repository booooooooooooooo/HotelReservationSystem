import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class PanelMakeReservation extends PanelPrototype {
  public PanelMakeReservation(Model m, View v) {
    super(m, v);

    JButton buttonCheckInDate = new JButton("Click to select CheckInDate");
    buttonCheckInDate.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        String name = JOptionPane.showInputDialog(getView(),
                        "What is your name?", null);
      }
    });
    JTextArea textAreaCheckInDate = new JTextArea("MM/DD/YYYY");

    add(buttonCheckInDate);
    add(textAreaCheckInDate);



  }

  @Override
  protected void updateData(){
    //TODO
  }
}
