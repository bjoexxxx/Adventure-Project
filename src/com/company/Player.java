package com.company;

import Items.Consume;
import Items.Food;
import Items.Item;
import Items.Weapon;

import java.util.ArrayList;

public class Player {
  private Room currentRoom;
  private ArrayList<Item> playerInventory;
  private int health;
  private Weapon rightHand;
  private Weapon leftHand;

  public Player(Room currentRoom, ArrayList<Item> inventory, int health) {
    this.currentRoom = currentRoom;
    this.playerInventory = inventory;
    this.health = health;
    this.leftHand = null;
    this.rightHand = null;
  }

  public boolean canPlayerAttack () {
    if (this.rightHand == null) {
      return true;
    } else if (this.rightHand.getRemainingUses() > 0) {
     return false;
    } else {
      return true;
    }
  }


  public boolean attackTarget (String nameOfTarget) {

    if (nameOfTarget.isEmpty()) {
      rightHand.setRemainingUses();
      return false;
    } else {
      rightHand.setRemainingUses();
      return true;
    }
  }

  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public void playerMove(Room newRoom) {
    currentRoom.setRoomIsVisted(true);
    currentRoom = newRoom;
  }

  public boolean takeItem(String itemName) {
    Item itemFromRoom = currentRoom.searchItemsInRoom(itemName);
    if (itemFromRoom != null) {
      playerInventory.add(itemFromRoom);
      currentRoom.removeItem(itemFromRoom);
      return true;
    } else {
      return false;
    }
  }

  public boolean dropItem(String itemName) {
    Item itemFromInventory = searchItemsInInventory(itemName);
    if (itemFromInventory != null) {
      playerInventory.remove(itemFromInventory);
      currentRoom.setLootTable(itemFromInventory);
      return true;
    } else {
      return false;
    }
  }

  private Item searchItemsInInventory(String itemName) {
    if (playerInventory == null) {
      return null;
    } else {
      for (int i = 0; i < playerInventory.size(); i++) {
        Item temp = playerInventory.get(i);
        if (temp.getName().equals(itemName)) {
          return temp;
        }
      }
    }
    return null;
  }

  public boolean setRightHandWeapon(String weaponName){
    Item weaponFromInventory = searchItemsInInventory(weaponName);

    if (weaponFromInventory != null || this.rightHand == null ){
      if (weaponFromInventory instanceof Weapon weapon){
        inCaseRightHandIsEquipped();
        playerInventory.remove(weapon);
        this.rightHand = weapon;
        return true;
      }
    }
return false;
  }

  private void inCaseRightHandIsEquipped(){
    if (this.rightHand != null){
      playerInventory.add(this.rightHand);
    }
  }

  public Weapon getRightHand() {
    return this.rightHand;
  }

  public ArrayList<Item> getPlayerInventory() {
    return playerInventory;
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
        playerInventory.remove(food);
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


