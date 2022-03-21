package com.company;

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
      case "north" -> {
        currentRoom.getDoorNorth().setOpen(true);
        userinterface.displayOpendDoor();
      }
      case "west" -> {
        currentRoom.getDoorWest().setOpen(true);
        userinterface.displayOpendDoor();
      }
      case "east" -> {
        currentRoom.getDoorEast().setOpen(true);
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
    } else if (!currentRoom.checkdoors(room)) { //checks if there is a looked door and checks locations
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

  public void takeItem(String itemName) {
    for (int i = 0; i < getCurrentRoom().getLootTable().size(); i++) {
      if (getCurrentRoom().getLootTable().get(i).getName().equals(itemName)) {
        Item item = getCurrentRoom().getLootTable().get(i);
        this.inventory.add(item);
        currentRoom.removeItem(item);
      }
    }
  }

  public void dropItem(String itemName) {
    for (int i = 0; i < this.inventory.size(); i++) {
      if (inventory.get(i).getName().equals(itemName)) {
        Item item = inventory.get(i);
        currentRoom.setLootTable(item);
        this.inventory.remove(item);
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
    if (currentRoom.triedRooms(room)) {
      userinterface.displayYouHaveOptionsDirections();
      for (int i = 0; i < options.length; i++) {
        if (options[i] != null) {
          userinterface.displayAvailableDirections(i);
        }
      }
      userinterface.newline();
    }
  }
}

