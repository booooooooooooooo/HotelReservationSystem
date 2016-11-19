import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class PanelManagerDoWhat extends PanelPrototype {
  public PanelManagerDoWhat(Model m, View v) {
    super(m, v);
    JButton buttonLoad = new JButton("Load");
    JButton buttonView = new JButton("View");
    JButton buttonSave = new JButton("Save");
    JButton buttonQuit = new JButton("Quit");

    buttonView.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        getView().displayPanelManagerView();
      }
    });

    buttonSave.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        getModel().writeDataToFile();
      }
    });
    buttonQuit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        getModel().writeDataToFile();
        getView().dispatchEvent(new WindowEvent(getView(), WindowEvent.WINDOW_CLOSING));
      }
    });

    add(buttonLoad);
    add(buttonView);
    add(buttonSave);
    add(buttonQuit);
  }
}
