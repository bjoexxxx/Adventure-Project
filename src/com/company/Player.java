package com.company;

import Items.Consume;
import Items.Food;
import Items.Item;
import Items.Weapon;

import java.util.ArrayList;

public class Player {
  private Room currentRoom;
  private ArrayList<Item> inventory;
  private int health;
  private Weapon rightHand;
  private Weapon leftHand;

  public Player(Room currentRoom, ArrayList<Item> inventory, int health) {
    this.currentRoom = currentRoom;
    this.inventory = inventory;
    this.health = health;
    this.leftHand = null;
    this.rightHand = null;
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
    Item itemFromRoom = currentRoom.searchItemsInRoom(itemName);
    if (itemFromRoom != null) {
      inventory.add(itemFromRoom);
      currentRoom.removeItem(itemFromRoom);
      return true;
    } else {
      return false;
    }
  }

  public boolean dropItem(String itemName) {
    Item itemFromInventory = searchItemsInInventory(itemName);
    if (itemFromInventory != null) {
      inventory.remove(itemFromInventory);
      currentRoom.setLootTable(itemFromInventory);
      return true;
    } else {
      return false;
    }
  }

  private Item searchItemsInInventory(String itemName) {
    if (inventory == null) {
      return null;
    } else {
      for (int i = 0; i < inventory.size(); i++) {
        Item temp = inventory.get(i);
        if (temp.getName().equals(itemName)) {
          return temp;
        }
      }
    }
    return null;
  }

  public boolean setRightHandWeapon(String weaponname){
    Item weaponFromInventory = searchItemsInInventory(weaponname);

    if (weaponFromInventory != null && this.rightHand == null ){
      if (weaponFromInventory instanceof Weapon weapon){
        inCaseHandIsEquipped();
        inventory.remove(weapon);
        this.rightHand = weapon;
        return true;
      }
    }
return false;
  }

  private void inCaseHandIsEquipped(){
    if (this.rightHand != null){
      inventory.add(this.rightHand);
    }
  }

  public Weapon getRightHand() {
    return rightHand;
  }

  public void attack(){
   if (this.rightHand != null){

   }
  }

  public ArrayList<Item> getInventory() {
    return inventory;
  }

  public void setHealth(int adjustHealth) {
    this.health += adjustHealth;
  }

  public int getHealth() {
    return this.health;
  }

  public Consume eatFood(String itemName) {
    Item itemFromInventory = searchItemsInInventory(itemName);

    if (itemFromInventory != null) {
      if (itemFromInventory instanceof Food food) {
        inventory.remove(food);
        digestingFood(food.getConsume());
        checkMaxHealth(getHealth());
        return food.getConsume();
      }
    }
    return Consume.INVALID;
  }

  private void checkMaxHealth(int health) {
    if (health > 99) {
      this.health = 100;
    }
  }

  private void digestingFood(Consume consume) {

    switch (consume) {
      case EDIBLE -> setHealth(20);
      case POISONOUS -> setHealth(-25);

    }
  }

}


