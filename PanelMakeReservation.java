import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.*;

import java.util.*;

public class PanelMakeReservation extends PanelPrototype {
  private final Calendar activeDate; // bound to Calendar scrollPane
  private final JTable table;
  private final JLabel labelCalendarTitle;
  private final Calendar checkInDate;
  private final JTextArea textAreaCheckInDate;
  private final Calendar checkOutDate;
  private final JTextArea textAreaCheckOutDate;
  private final ArrayList<Integer> priceRange;
  private JTextArea textAreaAvailableRoom;
  private final ArrayList<Order> reservation;

  private final String[] columnNames = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
  private final String[] monthNames = {"January", "Febrary", "March", "April", "May", "June", "July", "Auguest", "September", "October", "November", "December"};



  public PanelMakeReservation(Model m, View v) {
    super(m, v);
    //Initialize instance variables
    activeDate = new GregorianCalendar();
    table = new JTable(getTableOnActiveDate(), columnNames);
    labelCalendarTitle = new JLabel(getTableTitleOnActiveDate());
    checkInDate = new GregorianCalendar();;
    checkOutDate = new GregorianCalendar();;
    priceRange = new ArrayList<Integer>();
    priceRange.add(null);
    priceRange.add(null);
    // availableRoomID = new ArrayList<Integer>();
    textAreaAvailableRoom = new JTextArea((new ArrayList<Integer>()).toString());
    reservation = new ArrayList<Order>();


    //Make paneCalendar
    table.setCellSelectionEnabled(true);
    table.setPreferredScrollableViewportSize(
        new Dimension(300, 100));
    table.setFillsViewportHeight(true);
    markActiveDateOnTable();
    table.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        JTable target = (JTable)e.getSource();
        int row = target.getSelectedRow();
        int column = target.getSelectedColumn();
        String entry = (String)table.getModel().getValueAt(row, column);
        if (entry == "") {
          getView().drawOnUpdatedData(); // Not a valid day. RE-Mark previous activeDate
        } else
          activeDate.set(activeDate.DAY_OF_MONTH, Integer.parseInt(entry));//update activeDate

        System.out.println(activeDate.getTime());
      }
    });
    JScrollPane paneCalendar = new JScrollPane(table);
    JButton buttonPrev = new JButton("<");
    buttonPrev.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        activeDate.add(activeDate.MONTH, -1);
        getView().drawOnUpdatedData();
      }
    });

    JButton buttonNext = new JButton(">");
    buttonNext.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        activeDate.add(activeDate.MONTH, 1);
        getView().drawOnUpdatedData();
      }
    });


    JTextField textFieldLowerPriceBound = new JTextField("Price Lower Bound");
    JTextField textFieldHigherPriceBound = new JTextField("Price Higher Bound");

    textAreaCheckInDate = new JTextArea(checkInDate.getTime().toString());
    JButton buttonCheckInDate = new JButton("Set CheckInDate ");
    buttonCheckInDate.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {

        checkInDate.set(checkInDate.YEAR, activeDate.get(activeDate.YEAR));
        checkInDate.set(checkInDate.MONTH, activeDate.get(activeDate.MONTH));
        checkInDate.set(checkInDate.DAY_OF_MONTH, activeDate.get(activeDate.DAY_OF_MONTH));
        //TODO: get lowerPriceBound and higherPriceBound
        String l = textFieldLowerPriceBound.getText();
        String h = textFieldHigherPriceBound.getText();
        if(l.matches("^-?\\d+$")) priceRange.set(0, Integer.parseInt(l));
        if(h.matches("^-?\\d+$")) priceRange.set(1, Integer.parseInt(h));
        getView().drawOnUpdatedData();
      }
    });

    textAreaCheckOutDate = new JTextArea(checkOutDate.getTime().toString());
    JButton buttonCheckOutDate = new JButton("Set CheckOutDate ");
    buttonCheckOutDate.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        checkOutDate.set(checkOutDate.YEAR, activeDate.get(activeDate.YEAR));
        checkOutDate.set(checkOutDate.MONTH, activeDate.get(activeDate.MONTH));
        checkOutDate.set(checkOutDate.DAY_OF_MONTH, activeDate.get(activeDate.DAY_OF_MONTH));
          //TODO: get lowerPriceBound and higherPriceBound
        String l = textFieldLowerPriceBound.getText();
        String h = textFieldHigherPriceBound.getText();
        if(l.matches("^-?\\d+$")) priceRange.set(0, Integer.parseInt(l));
        if(h.matches("^-?\\d+$")) priceRange.set(1, Integer.parseInt(h));
        getView().drawOnUpdatedData();
      }
    });







    //JTextField enter room number
    JTextField textFieldRoomIDToOrder = new JTextField("Enter Room ID to Order here!");

    //Confirm button. Button lister update model mutator thus also refresh view
    JButton buttonConfirm = new JButton("Confirm ");
    buttonConfirm.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        String id = textFieldRoomIDToOrder.getText();
        if(id.matches("^-?\\d+$")){
          Order order = getModel().createOrder(checkInDate, checkOutDate, Integer.parseInt(id));
          reservation.add(order);
        }else ;//TODO:prompt error msg to user
        getView().drawOnUpdatedData();
      }
    });


    //Done button. view.displayPanelPrint(ArrayList<Order> order)
    JButton buttonDone = new JButton("Done ");
    buttonDone.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        getView().displayPanelPrint(reservation);
      }
    });

    //more reservation button   view.displayPanelMakeReservation()
    JButton buttonMoreReservation = new JButton("More reservation? ");
    buttonMoreReservation.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        getView().displayPanelMakeReservation();
      }
    });


    add(buttonPrev);
    add(buttonNext);
    add(labelCalendarTitle);
    add(paneCalendar);
    add(buttonCheckInDate);
    add(textAreaCheckInDate);
    add(buttonCheckOutDate);
    add(textAreaCheckOutDate);
    add(textFieldLowerPriceBound);
    add(textFieldHigherPriceBound);
    add(textAreaAvailableRoom);
    add(textFieldRoomIDToOrder);
    add(buttonConfirm);
    add(buttonDone);
    add(buttonMoreReservation);



  }

  public String[][] getTableOnActiveDate(){
    Calendar temp = (Calendar)activeDate.clone();
    temp.set(temp.DAY_OF_MONTH, 1);
    int month = temp.get(temp.MONTH);

    String[][] result = new String[5][7];
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 7; j++) {
        if (temp.get(temp.MONTH) != month)
          result[i][j] = "";
        else if (i == 0 && temp.get(temp.DAY_OF_WEEK) - 1 > j)
          result[i][j] = "";
        else {
          result[i][j] = "" + temp.get(temp.DAY_OF_MONTH);
          temp.add(temp.DAY_OF_MONTH, 1);
        }
      }
    }
    return result;
  }
  public void markActiveDateOnTable(){
    int[] cell = getCellOfActiveDate();
    table.setRowSelectionInterval(cell[0], cell[0]);
    table.setColumnSelectionInterval(cell[1], cell[1]);
  }

  public int[] getCellOfActiveDate(){
    int[] cell = new int[2];

    Calendar temp = (Calendar)activeDate.clone();
    temp.set(temp.DAY_OF_MONTH, 1);
    int month = temp.get(temp.MONTH);

    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 7; j++) {
        if (i == 0 && temp.get(temp.DAY_OF_WEEK) - 1 > j)
          continue;
        else {
          if (activeDate.get(activeDate.MONTH) ==
                  temp.get(temp.MONTH) &&
              activeDate.get(activeDate.DAY_OF_MONTH) ==
                  temp.get(temp.DAY_OF_MONTH)) {
            cell[0] = i;
            cell[1] = j;
            // break for for loop
            i = 100;
            j = 100;
          }
          temp.add(temp.DAY_OF_MONTH, 1);
        }
      }
    }
    return cell;
  }

  public String getTableTitleOnActiveDate(){
    int month = activeDate.get(activeDate.MONTH);
    int year = activeDate.get(activeDate.YEAR);
    return "" + monthNames[month] + " " + year;
  }


  @Override
  protected void updateData(){
    //TODO
    String[][] result = getTableOnActiveDate();
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 7; j++) {
        table.getModel().setValueAt(result[i][j], i, j);
      }
    }
    markActiveDateOnTable();
    labelCalendarTitle.setText(getTableTitleOnActiveDate());
    textAreaCheckInDate.setText(checkInDate.getTime().toString());//TODO: pretty
    textAreaCheckOutDate.setText(checkOutDate.getTime().toString());//TODO: pretty
    textAreaAvailableRoom.setText(getModel().getAvailableRoomIDByDateAndPrice(checkInDate, checkOutDate, priceRange.get(0), priceRange.get(1)).toString());
  }
}
