package com.company;

public class Door {

  private boolean isOpen;
  private String typeOfDoor;
  private Room startRoom;
  private Room endRoom;

  Door(boolean isOpen, String typeOfDoor, Room startRoom, Room endRoom) {
    this.isOpen = isOpen;
    this.typeOfDoor = typeOfDoor;
    this.startRoom = startRoom;
    this.endRoom = endRoom;
  }

  public Room getStartRoom() {
    return startRoom;
  }

  public Room getEndRoom() {
    return endRoom;
  }

  public String getTypeOfDoor() {
    return typeOfDoor;
  }

  public boolean isOpen() {
    return isOpen;
  }

  public void setOpen(boolean open) {
    isOpen = open;
  }
}
