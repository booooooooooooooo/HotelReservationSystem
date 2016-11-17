import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class PanelSign extends PanelPrototype {
  public PanelSign(Model m, View v) {
    super(m, v);

    JLabel labelSignIn = new JLabel("Sign In Area");
    JTextField textFieldGuestIDSignIn = new JTextField("Guest ID");
    JTextField textFieldGuestPasswordSignIn = new JTextField("Guest Password");
    JButton buttonSignIn = new JButton("Sign In");
    buttonSignIn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO
      }
    });


    JLabel labelSignUp = new JLabel("Sign Up Area");
    JTextField textFieldGuestIDSignUp = new JTextField("Guest ID");
    JTextField textFieldGuestNameSignUp = new JTextField("Guest Name");
    JTextField textFieldGuestPasswordSignUp = new JTextField("Guest Password");
    JButton buttonSignUp = new JButton("Sign Up");
    buttonSignUp.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO
      }
    });


    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 1;
    add(labelSignIn, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 0;
    c.gridwidth = 1;
    add(textFieldGuestIDSignIn, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 1;
    c.gridwidth = 1;
    add(textFieldGuestPasswordSignIn, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 2;
    c.gridwidth = 1;
    add(buttonSignIn, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 2;
    c.gridy = 0;
    c.gridwidth = 1;
    add(labelSignUp, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 3;
    c.gridy = 0;
    c.gridwidth = 1;
    add(textFieldGuestIDSignUp, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 3;
    c.gridy = 1;
    c.gridwidth = 1;
    add(textFieldGuestNameSignUp, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 3;
    c.gridy = 2;
    c.gridwidth = 1;
    add(textFieldGuestPasswordSignUp, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 3;
    c.gridy = 3;
    c.gridwidth = 1;
    add(buttonSignUp, c);


  }
}
