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
      case "South" -> {
        currentRoom.getDoorSouth().setOpen(true);
        userinterface.displayOpendDoor();
      }
      case "North" -> {
        currentRoom.getDoorNorth().setOpen(true);
        userinterface.displayOpendDoor();
      }
      case "West" -> {
        currentRoom.getDoorWest().setOpen(true);
        userinterface.displayOpendDoor();
      }
      case "East" -> {
        currentRoom.getDoorEast().setOpen(true);
        userinterface.displayOpendDoor();
      }
    }
  }

  public void move(String direction) {

    Room room = null;
    userinterface.newline();

    switch (direction) {
      case "North", "N" -> {
        currentRoom.setTriedNorth(true);
        room = currentRoom.getNorth();
      }
      case "South", "S" -> {
        currentRoom.setTriedSouth(true);
        room = currentRoom.getSouth();
      }
      case "East", "E" -> {
        currentRoom.setTriedEast(true);
        room = currentRoom.getEast();
      }
      case "West", "W" -> {
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
    Item foundItem = currentRoom.findItem(itemName);
    if (foundItem != null) {
      inventory.add(foundItem);
      currentRoom.removeItem(foundItem);
    } else {
      userinterface.displayItemNotFound();
    }
  }

  public void dropItem(String itemName) {
    if (inventory.isEmpty()) {
      userinterface.displayItemNotFound();
    } else {
      for (int i = 0; i < inventory.size(); i++) {
        Item temp = inventory.get(i);
        if (temp.getName().equals(itemName)) {
          Item foundItem = inventory.get(i);
          inventory.remove(foundItem);
          currentRoom.setLootTable(foundItem);
        }
      }
    }
  }

  public void checkInventory() {
    userinterface.displayPlayerInventory(inventory);
  }

  public void look() {
    Room room = currentRoom;

    userinterface.displayRoomDiscription(currentRoom);

    if (!currentRoom.getLootTable().isEmpty()) { // loot table must contain something
      userinterface.displayItems(currentRoom);
    }
    // the following code checks to see if player has tried going all directions, if yes, the available moves are displayed
    currentRoom.availableDirections(room);
    userinterface.newline();
    }

}


