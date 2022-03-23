package com.company;

import java.util.ArrayList;

public class Player {
  private Room currentRoom;
  private ArrayList<Item> inventory;
  private int health;

  public Player(Room currentRoom, ArrayList<Item> inventory, int health) {
    this.currentRoom = currentRoom;
    this.inventory = inventory;
    this.health = health;
  }

  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public void playerMove(Room room) {
    currentRoom.setIsVisited(true);
    currentRoom = room;
  }

  public boolean takeItem(String itemName) {
    Item foundItem = currentRoom.findItem(itemName);
    if (foundItem != null) {
      inventory.add(foundItem);
      currentRoom.removeItem(foundItem);
      return true;
    } else {
      return false;
    }
  }

  public void dropItem(String itemName) {

      for (int i = 0; i < inventory.size(); i++) {
        Item temp = inventory.get(i);
        if (temp.getName().equals(itemName)) {
          Item foundItem = inventory.get(i);
          inventory.remove(foundItem);
          currentRoom.setLootTable(foundItem);
        }
      }
    }


  public ArrayList<Item> getInventory() {
    return inventory;
  }

  public void setHealth (int health) {
    this.health = health;
  }

  public int getHealth () {
    return this.health;
  }
  public Consume eatFood(String itemName){
    for (int i = 0; i < inventory.size(); i++) {
      Item temp = inventory.get(i);
      if (temp.getName().equals(itemName)) {
        Item foundItem = inventory.get(i);
        inventory.remove(foundItem);
        currentRoom.setLootTable(foundItem);
        if (foundItem instanceof Food){

        }
      }

    }
    return null;
  }

}


