import java.io.*;

public class Room implements java.io.Serializable {
  private int roomID;
  private int roomPrice;
  public Room(int id, int price) {
    this.roomID = id;
    this.roomPrice = price;
  }
  public int getRoomID() { return roomID; }
  public int getRoomPrice() { return roomPrice; }
  @Override
  public String toString() {
    return "Room ID: " + roomID + "  "
        + "Room Price: " + roomPrice + "\n";
  }
}
