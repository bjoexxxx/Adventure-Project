package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
  private Room currentRoom;
  private ArrayList<Item> inventory;
  private Userinterface userinterface;

  public Player(Room currentRoom, ArrayList<Item> inventory) {
    this.currentRoom = currentRoom;
    this.inventory = inventory;
    this.userinterface = new Userinterface();
  }

  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public void open(String direction) {
    switch (direction) {
      case "south" -> {
        currentRoom.getDoorSouth().setOpen(true);
        userinterface.displayOpendDoor();
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
      userinterface.displayWalkedIntoWall();
    } else if (checkdoor(room)) { //checks if there is a looked door and checks locations
      userinterface.displayFoundLockedDoor();
    } else { //if player makes a valid move
      currentRoom.setIsVisited(true);
      currentRoom = room;
      if (!currentRoom.getIsVisited()) {
        userinterface.displayRoomDiscription(currentRoom);
      } else {
        userinterface.displayShortRoomDiscription(currentRoom);
      }
    }
  }

  private boolean checkdoor (Room room) {
    if (room == room.getNorth()) {
      return room.getNorth() != null && room.getDoorNorth().isOpen();
    } else if (room == room.getSouth()){
      return room.getSouth() != null && room.getDoorSouth().isOpen();
    } else if (room == room.getEast()) {
      return room.getEast() != null && room.getDoorEast().isOpen();
    } else if (room == room.getWest()) {
      return room.getDoorWest() != null && room.getDoorWest().isOpen();
    } else {
      return false;
    }

  }

  public void pickupItem(String name) {
    for (int i = 0; i < getCurrentRoom().getLootTable().size(); i++) {
      if (getCurrentRoom().getLootTable().get(i).getName().equals(name)) {
        this.inventory.add(getCurrentRoom().getLootTable().get(i));
        currentRoom.removeItem(getCurrentRoom().getLootTable().get(i));
      }
    }
  }

  public void dropItem(String name) {
    for (int i = 0; i < this.inventory.size(); i++) {
      if (inventory.get(i).getName().equals(name)) {
        currentRoom.getLootTable().add(inventory.get(i));
        this.inventory.remove(inventory.get(i));
      }
    }
  }

  public void checkInventory() {
    userinterface.displayPlayerInventory(inventory);
  }

  public void look() {
    Room room = currentRoom;

    userinterface.displayRoomDiscription(currentRoom);

    if (!currentRoom.getLootTable().isEmpty()) {
      userinterface.displayItems(currentRoom);
    }
    // the following code checks to see if player has tried going all directions, if yes, the available moves are displayed
    Room[] options = {room.getNorth(), room.getEast(), room.getSouth(), room.getWest()};
    if (triedRooms(room)) {
      userinterface.displayYouHaveOptionsDirections();
      for (int i = 0; i < options.length; i++) {
        if (options[i] != null) {
          userinterface.displayAvailableDirections(i);
        }
      }
      userinterface.newline();
    }
  }

  private boolean triedRooms(Room room) {
    return room.getTriedNorth() && room.getTriedEast() && room.getTriedSouth() && room.getTriedWest();
  }
}
