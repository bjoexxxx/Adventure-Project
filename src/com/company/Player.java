package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
  private Room currentRoom;
  private ArrayList<Item> inventory = new ArrayList<>();
  public Player(Room currentRoom, ArrayList<Item> inventory){
    this.currentRoom = currentRoom;
    this.inventory = inventory;
  }

  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public void open(String type){
    switch (type){
      case "door" -> {
        currentRoom.getDoor().setOpen(true);
        System.out.println("You open the door.");
      }
    }
  }

  public void move(String direction) {

    Room room = null;

    switch (direction) {
      case "north", "n" -> {
        currentRoom.setTriedNorth(true);
        room = currentRoom.getNorth();
      }
      case "south", "s" -> {
        currentRoom.setTriedSouth(true);
        room = currentRoom.getSouth();
      }
      case "east", "e" -> {
        currentRoom.setTriedEast(true);
        room = currentRoom.getEast();
      }
      case "west", "w" -> {
        currentRoom.setTriedWest(true);
        room = currentRoom.getWest();
      }
    }

    if (room == null) { //checks if the next room is a wall
      System.out.println("you walked into a wall, ouch");
    } else if (currentRoom.getDoor() != null && !currentRoom.getDoor().isOpen() && checkdoor(room)) { //checks if there is a looked door and checks locations
      System.out.println("You found a found a door that is locked.");
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
    boolean a = currentRoom == currentRoom.getDoor().getStartRoom() && room == currentRoom.getDoor().getEndRoom();
    boolean b = currentRoom == currentRoom.getDoor().getEndRoom() && room == currentRoom.getDoor().getStartRoom();
    return a || b;
  }


}
