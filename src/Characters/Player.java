package Characters;

import Items.Consume;
import Items.Food;
import Items.Item;
import Items.Weapon;
import com.company.Room;

import java.util.ArrayList;

public class Player extends Character {
  private Weapon leftHand;

  public Player(Room currentRoom, ArrayList<Item> inventory, int health) {
    super(currentRoom,inventory,health);
    this.leftHand = null;

  }

  public boolean canPlayerAttack () {
    if (this.rightHand == null) { //must have weapon
      return true;
    } else if (this.rightHand.getRemainingUses() > 0) { //must be a useable weapon
     return false;
    } else { // else its not an option
      return true;
    }
  }

  public boolean attackTarget (String nameOfTarget) {

    if (nameOfTarget.isEmpty()) { //non specified target
      rightHand.itemUsed();
      return false;
    } else { // TODO add something with enemy here as condition
      rightHand.itemUsed();
      return true;
    }
  }

  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public void playerMoveToRoom(Room newRoom) {
    currentRoom.setRoomIsVisted(true);
    currentRoom = newRoom;
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

  public boolean setRightHandWeapon(String weaponName){
    Item weaponFromInventory = searchItemsInInventory(weaponName);

    if (weaponFromInventory != null || this.rightHand == null ){
      if (weaponFromInventory instanceof Weapon weapon){
        inCaseRightHandIsEquipped();
        inventory.remove(weapon);
        this.rightHand = weapon;
        return true;
      }
    }
return false;
  }

  private void inCaseRightHandIsEquipped(){
    if (this.rightHand != null){
      inventory.add(this.rightHand);
    }
  }

  public Weapon getRightHand() {
    return this.rightHand;
  }

  public ArrayList<Item> getPlayerInventory() {
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

  private void checkMaxHealth(int currentHealth) {
    if (currentHealth > 99) {
      this.health = 100;
    }
  }

  private void digestingFood(Consume consumeFood) {

    switch (consumeFood) {
      case EDIBLE -> setHealth(20);
      case POISONOUS -> setHealth(-25);

    }
  }

}


