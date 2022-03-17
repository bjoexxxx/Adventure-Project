package com.company;

public class Door {

  private boolean isOpen;
  private Room startRoom;
  private Room endRoom;

  Door(boolean isOpen, Room startRoom, Room endRoom) {
    this.isOpen = isOpen;
    this.startRoom = startRoom;
    this.endRoom = endRoom;


  }

  public Room getStartRoom() {
    return startRoom;
  }

  public Room getEndRoom() {
    return endRoom;
  }

  public boolean isOpen() {
    return isOpen;
  }

  public void setOpen(boolean open) {
    isOpen = open;
  }
}
