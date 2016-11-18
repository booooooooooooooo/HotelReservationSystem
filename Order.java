import java.io.*;
import java.util.*;

public class Order implements java.io.Serializable {
  private Calendar checkInDate;
  private Calendar checkOutDate;
  private String guestID;
  private int roomID;
  public Order(Calendar in, Calendar out, String gid, int rid) {
    this.checkInDate = in;
    this.checkOutDate = out;
    this.guestID = gid;
    this.roomID = rid;
  }

  public Calendar getCheckInDate() { return checkInDate; }

  public Calendar getCheckOutDate() { return checkOutDate; }
  public String getGuestID() { return guestID; }
  public int getRoomID() { return roomID; }
  @Override
  public String toString() {
    return "Chenk In: " + checkInDate.getTime() + "\n" 
        + "Check Out: " + checkOutDate.getTime() + "\n"  + "Guest ID: " + guestID + "\n" + "  "
        + "Room ID: " + roomID + "\n";
  }
}
