import java.io.*;

public class Guest implements java.io.Serializable {
  private String guestID;       // email
  private String guestPassword; // size = 8
  private String guestName;
  public Guest(String id, String pw, String name) {
    this.guestID = id;
    this.guestPassword = pw;
    this.guestName = name;
  }
  public String getGuestID() { return guestID; }
  public String getGuestName() { return guestName; }
  public void changeGuestPassword(String npw) { this.guestPassword = npw; }
  public boolean matchPassword(String pw) {
    if (guestPassword == pw)
      return true;
    else
      return false;
  }
  @Override
  public String toString() {
    return "Guest ID: " + guestID + "  "
        + "Guest Name: " + guestName + "\n";
  }
}
