import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;

public class PanelViewOrCancel extends PanelPrototype {
  private final JTable tableOrder;
  private final String[] columnNames = {"Your Orders"};
  private int indexOrOrderDataToDelete;

  public PanelViewOrCancel(Model m, View v) {
    super(m, v);
    tableOrder = new JTable( new DefaultTableModel(getTableOnOrderData(), columnNames) );
    indexOrOrderDataToDelete = -1;

    tableOrder.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        JTable target = (JTable)e.getSource();
        int row = target.getSelectedRow();
        System.out.printf("Row : %d\n", row);
        System.out.printf("List Size : %d\n", getModel().getOrderData().size() );
        setIndexOrOrderDataToDelete(row);
      }
    });
    JScrollPane paneOrder = new JScrollPane(tableOrder);

    JButton buttonCancelSelectedOrder = new JButton("Cancel Selected Order");
    buttonCancelSelectedOrder.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        deleteSelectedOrder();
      }
    });

    add(paneOrder);
    add(buttonCancelSelectedOrder);
  }

  public String[][] getTableOnOrderData(){
    ArrayList<Order> orderData = getModel().getOrderData();
    String[][] result = new String[orderData.size()][1];
    for (int i = 0; i < orderData.size(); i++) {
      for (int j = 0; j < 1; j++) {
        result[i][j] = orderData.get(i).toString();
      }
    }
    return result;
  }

  private void setIndexOrOrderDataToDelete(int index){
    this.indexOrOrderDataToDelete = index;
  }
  private void deleteSelectedOrder(){
    if(indexOrOrderDataToDelete == -1) return;
    else getModel().deleteOrder( getModel().getOrderData().get(indexOrOrderDataToDelete) );
  }

  public void updateData(){
    tableOrder.setModel(new DefaultTableModel(getTableOnOrderData(), columnNames));
  }

}
