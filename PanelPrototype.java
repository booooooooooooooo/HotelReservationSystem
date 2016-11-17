import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class PanelPrototype extends JPanel {
  private Model model;
  private View view;
  public PanelPrototype(Model m, View v) {
    this.model = m;
    this.view = v;
  }
  protected Model getModel() { return model; }

  protected View getView() { return view; }

  protected void updateData() {}
}
