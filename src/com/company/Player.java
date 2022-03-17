package com.company;

public class Player {
  Room currentRoom;

  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public void move(Room room) {

    if (room == null) { //checks if the next room is a wall
      System.out.println("you walked into a wall, ouch");
    } else if (currentRoom.getDoor() != null && !currentRoom.getDoor().isOpen()  && checkdoor(room)) { //checks if there is a looked door and checks locations
      System.out.println("You found a " + currentRoom.getDoor().getTypeOfDoor() + " that is locked.");
    } else { //if player makes a valid move
      currentRoom.setIsVisited(true);
      currentRoom = room;
      if (!currentRoom.getIsVisited()) {
        System.out.println(currentRoom.getDescription());
      } else {
        System.out.println(currentRoom.getShortDescription());
      }
    }
  }

  public boolean checkdoor(Room room) {
    //comparing to see if the current room and the next room has a door between them
    return currentRoom == currentRoom.getDoor().getStartRoom() && room == currentRoom.getDoor().getEndRoom();
  }

  public void openDoor() {
    currentRoom.getDoor().setOpen(true);
    System.out.println("You open the " + currentRoom.getDoor().getTypeOfDoor() + ".");
  }


}
