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
    activeDate = new GregorianCalendar();
    table = new JTable(getTableOnActiveDate(), columnNames);
    labelCalendarTitle = new JLabel(getTableTitleOnActiveDate());
    checkInDate = new GregorianCalendar();;
    checkOutDate = new GregorianCalendar();;
    priceRange = new ArrayList<Integer>();
    priceRange.add(null);
    priceRange.add(null);
    textAreaAvailableRoom = new JTextArea((new ArrayList<Integer>()).toString());
    reservation = new ArrayList<Order>();


    JTextArea textAreaInstruction = new JTextArea("1. Enter Price Range.\n2.Select Check In and Out Date. Caustion: Check In Date should not be ealier than today. Check Out Date should be at most 60 days later than check in date.\n3.Enter room number of your choice. It must be one of available rooms of the price and dates you entered.\n4.Press confirm button to confirm your order.\n5.Press Done button to print receipt\n6.Press More Reservation button to refresh the window.\n7.Press Back To Main Menu button to start from the beginning\n");
    JPanel panelInstruction = new JPanel();
    panelInstruction.add(textAreaInstruction);


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
    JPanel panelCalendar = new JPanel();
    panelCalendar.add(paneCalendar);
    panelCalendar.add(labelCalendarTitle);
    panelCalendar.add(buttonPrev);
    panelCalendar.add(buttonNext);


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
        String l = textFieldLowerPriceBound.getText();
        String h = textFieldHigherPriceBound.getText();
        if(l.matches("^-?\\d+$")) priceRange.set(0, Integer.parseInt(l));
        if(h.matches("^-?\\d+$")) priceRange.set(1, Integer.parseInt(h));
        getView().drawOnUpdatedData();
      }
    });
    JPanel panelGuestOption = new JPanel();
    panelGuestOption.add(textFieldLowerPriceBound);
    panelGuestOption.add(textFieldHigherPriceBound);
    panelGuestOption.add(buttonCheckInDate);
    panelGuestOption.add(textAreaCheckInDate);
    panelGuestOption.add(buttonCheckOutDate);
    panelGuestOption.add(textAreaCheckOutDate);






    JTextField textFieldRoomIDToOrder = new JTextField("Enter Room ID to Order here!");
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
    JButton buttonDone = new JButton("Done ");
    buttonDone.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        getView().displayPanelPrint(reservation);
      }
    });
    JButton buttonMoreReservation = new JButton("More reservation? ");
    buttonMoreReservation.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        getView().displayPanelMakeReservation();
      }
    });
    JButton buttonBackToMainMenu = new JButton("Back To Main Menu");
    buttonBackToMainMenu.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        getModel().setCurrentGuestID(null);
        getView().displayPanelManagerOrGuest();
      }
    });
    JPanel panelReserve = new JPanel();
    panelReserve.add(textAreaAvailableRoom);
    panelReserve.add(textFieldRoomIDToOrder);
    panelReserve.add(buttonConfirm);
    panelReserve.add(buttonDone);
    panelReserve.add(buttonMoreReservation);
    panelReserve.add(buttonBackToMainMenu);



    add(panelInstruction);
    add(panelCalendar);
    add(panelGuestOption);
    add(panelReserve);
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
