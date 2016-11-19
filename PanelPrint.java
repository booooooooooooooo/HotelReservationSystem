import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.*;

import java.util.*;

public class PanelPrint extends PanelPrototype {

  private final Receipt receipt;
  private final JTextArea textAreaReceipt;


  public PanelPrint(Model m, View v, ArrayList<Order> res ) {
    super(m, v);
    //Initialize instance variables
    this.receipt = new Receipt(res);
    textAreaReceipt = new JTextArea("To display Receipt");


    JButton buttonSimple = new JButton("Simple Receipt");
    JButton buttonComprehensive = new JButton("Comprehensive Receipt");

    buttonSimple.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO
        textAreaReceipt.setText(receipt.format(new SimpleFormatter()));
        getView().drawOnUpdatedData();
      }
    });

    buttonComprehensive.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //TODO
        textAreaReceipt.setText(receipt.format(new ComprehensiveFormatter()));
        getView().drawOnUpdatedData();
      }
    });

    JButton buttonBackToMainMenu = new JButton("Back To Main Menu");
    buttonBackToMainMenu.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        getView().displayPanelManagerOrGuest();
      }
    });
    add(buttonBackToMainMenu);

    add(buttonSimple);
    add(buttonComprehensive);
    add(textAreaReceipt);
  }
}
