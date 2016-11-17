import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class ReservationSystem {
  public static void main(String args[]) {
    Model model = new Model("./hotelData");
    View view = new View(model);
    model.attach(view);
  }
}
