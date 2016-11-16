import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class ManagerOrGuestPanel extends MyPanel{
  public ManagerOrGuestPanel(Model m, View v){
    super(m, v);
    JButton manager_B = new JButton("manager");
    JButton guest_B = new JButton("guest");

    manager_B.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        //TODO
      }
    });

    guest_B.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        //TODO
      }
    });

    add(manager_B);
    add(guest_B);
  }
}
