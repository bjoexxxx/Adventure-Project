package Characters;

import Items.*;
import com.company.Room;

import java.util.ArrayList;

public class Player extends Character {
  private Weapon leftHand;

  public Player(Room currentRoom, ArrayList<Item> inventory, int health) {
    super(currentRoom, inventory, health);
    this.leftHand = null;

  }

  public boolean canPlayerAttack() {
    if (this.rightHand == null || this.rightHand.getRemainingUses() == 0) { //must have weapon
      return true;
    } else {
      return false;
    }
  }

  public void attackTarget(Enemy nameOfTarget) {

    Weapon playerWeapon = rightHand;

    if (nameOfTarget == null) { //non specified target
      playerWeapon.itemUsed(playerWeapon);
    } else {
      playerWeapon.itemUsed(playerWeapon);
      nameOfTarget.isAttacked(playerWeapon, this);
    }
  }

  public void isAttacked(Weapon enemyWeapon) {
    int damageTakenFromEnemy = enemyWeapon.getDamage();
    tookDamage(damageTakenFromEnemy);
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

  public boolean setRightHandWeapon(String weaponName) {
    Item weaponFromInventory = searchItemsInInventory(weaponName);

    if (weaponFromInventory != null || this.rightHand == null) {
      if (weaponFromInventory instanceof Weapon weapon) {
        inCaseRightHandIsEquipped();
        inventory.remove(weapon);
        this.rightHand = weapon;
        return true;
      }
    }
    return false;
  }

  private void inCaseRightHandIsEquipped() {
    if (this.rightHand != null) {
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
        setHealth(food.getNutrition());
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

  public Equipable reloadWeapon() {
    Ammo ammo = (Ammo) searchItemsInInventory("Ammo");

    if (this.rightHand instanceof Ranged && ammo != null) {
      this.rightHand.setRemainingUses(10);
      inventory.remove(ammo);
      return Equipable.EQUIPING;
    } else {
      return Equipable.INVALID;
    }
  }
}

