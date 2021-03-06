import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.*;

import java.util.*;

public class PanelManagerView extends PanelPrototype {
  private final Calendar activeDate; // bound to Calendar scrollPane
  private final JTable table;
  private final JLabel labelCalendarTitle;
  private final JTextArea textAreaRoomInformation;

  private final String[] columnNames = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
  private final String[] monthNames = {"January", "Febrary", "March", "April", "May", "June", "July", "Auguest", "September", "October", "November", "December"};
  private final String[] tableRoomIDColumnNames = {"", "", "", "", ""};


  public PanelManagerView(Model m, View v) {
    super(m, v);
    activeDate = new GregorianCalendar();
    table = new JTable(getTableOnActiveDate(), columnNames);
    labelCalendarTitle = new JLabel(getTableTitleOnActiveDate());
    textAreaRoomInformation = new JTextArea(getRoomInformationOnActivedate());



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
        String entry = (String)target.getModel().getValueAt(row, column);
        if (entry == "") {
          getView().drawOnUpdatedData(); // Not a valid day. RE-Mark previous activeDate
        } else
          activeDate.set(activeDate.DAY_OF_MONTH, Integer.parseInt(entry));//update activeDate
          getView().drawOnUpdatedData();
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
    JPanel panelMonthView = new JPanel();
    panelMonthView.add(paneCalendar);
    panelMonthView.add(labelCalendarTitle);
    panelMonthView.add(buttonPrev);
    panelMonthView.add(buttonNext);
    panelMonthView.add(textAreaRoomInformation);




    final JTextArea textAreaInformationOfSelectedRoom = new JTextArea("Information of the selected room goes here.");
    final JLabel labelCalendarTitle = new JLabel("Click the room ID you would like to view Orders");
    JTable tableRoomID = new JTable( makeTableArrayRoomID() , tableRoomIDColumnNames);
    tableRoomID.setCellSelectionEnabled(true);
    tableRoomID.setPreferredScrollableViewportSize(
        new Dimension(300, 100));
    tableRoomID.setFillsViewportHeight(true);
    tableRoomID.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        JTable target = (JTable)e.getSource();
        int row = target.getSelectedRow();
        int column = target.getSelectedColumn();
        String entry = (String)target.getModel().getValueAt(row, column);
        int roomID = Integer.parseInt(entry);
        textAreaInformationOfSelectedRoom.setText(getInformationOfRoomID(roomID));
        getView().drawOnUpdatedData();
      }
    });
    JPanel panelRoomView = new JPanel();
    panelRoomView.add(labelCalendarTitle);
    panelRoomView.add(tableRoomID);
    panelRoomView.add(textAreaInformationOfSelectedRoom);




    JButton buttonBackToMainMenu = new JButton("Back To Main Menu");
    buttonBackToMainMenu.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        getView().displayPanelManagerOrGuest();
          getModel().setCurrentGuestID(null);
      }
    });
    JPanel panelButtonBackToMainMenu = new JPanel();
    panelButtonBackToMainMenu.add(buttonBackToMainMenu);



    add(panelMonthView);
    add(panelRoomView);
    add(panelButtonBackToMainMenu);





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
  public String getRoomInformationOnActivedate(){

    String available = getModel().getAvailableRoomIDByDate(activeDate).toString();
    String reserved = getModel().getOrderOfDate(activeDate).toString();
    return "Room information\n\n" + "Available rooms:\n" + available + "\nReserved Rooms:\n" + reserved;
  }


  public String[][] makeTableArrayRoomID(){

    String[][] contentArr = new String[4][5];
    int count = 0;
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 5; j++){
        contentArr[i][j] = Integer.toString(count);
        count++;
      }
    }
    return contentArr;
  }
  public String getInformationOfRoomID(int roomID){
    if(getModel().getOrderOfRoomID(roomID).isEmpty()) return "No Order for this room!";
    else return getModel().getOrderOfRoomID(roomID).toString();
  }


  @Override
  protected void updateData(){
    table.setModel(new DefaultTableModel(getTableOnActiveDate(), columnNames));
    markActiveDateOnTable();
    labelCalendarTitle.setText(getTableTitleOnActiveDate());
    textAreaRoomInformation.setText( getRoomInformationOnActivedate() );

  }
}
