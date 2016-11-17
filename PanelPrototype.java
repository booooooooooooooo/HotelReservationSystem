import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class PanelPrototype extends JPanel{
  protected Model model;
  protected View view;
  public PanelPrototype(Model m, View v){
    this.model = model;
    this.view = view;
  }
  protected void updateDate(){}
}
