package com.company;

import Characters.Enemy;
import Items.Consume;
import Items.Equipable;
import Items.Item;
import Items.Weapon;

import java.util.ArrayList;

public class Userinterface {

  public Userinterface() {
  }

  public void displayWelcome(Room room) {
    System.out.println("Welcome to the game!");
    System.out.println("In case of confusion input \"help\".");
    System.out.println("\n" + room.getDescription());
  }

  public void help() {
    System.out.println(); // all the help funktions listed below
    System.out.println("Its always a good idea to check for spelling errors, if a command doesn't work.");
    System.out.println("You have the following options in the game:");
    System.out.println("-\"exit\" will end the game. ");
    System.out.println("-\"look\" will display the description of the room you're in. ");
    System.out.println("-\"go north\" will move you north, if that direction is clear." +
        " The same applies for go \"south\", \"go east\" and \"go west\".");
    System.out.println("- If there seems to be more than one door in a room, you need to type \"open south\" or \"open north\" ect.");
    System.out.println("-\"health\" will display your health. ");
    System.out.println("-\"take (item name)\" will add an item from the room to your inventory. ");
    System.out.println("-\"inventory\" will display your inventory. ");
    System.out.println("-\"equip (weapon name)\" will move a weapon from your inventory to your hand. ");
    System.out.println("-\"attack (enemy name)\" will use the weapon in your hand to strike an enemy. ");
    System.out.println("-\"reload\" will give your ranged weapon 10 new bullets. ");
    System.out.println("-\"backstory\" will display the setting for the game. ");
    System.out.println();

  }

  public void displayEnemies(Room currentRoom) {
    System.out.print("This room has the following enemies: ");
    System.out.print(currentRoom.getAllMonstersInRoom());

  }

  public void displayItemEquipped(Equipable status) {
    if (status == Equipable.EQUIPING) {
      System.out.println("Item equipped");
    } else {
      System.out.println("Invalid item");
    }
  }

  public void displayInvalid() {

  }

  public void displayFoodStatus(Consume status) {
    switch (status) {
      case EDIBLE -> System.out.println("Edible");
      case INVALID -> System.out.println("invalid");
      case POISONOUS -> System.out.println("poisonous");
    }
  }

  public void displayItemTaken() {
    System.out.println("You took the item.");
  }

  public void displayRoomDiscription(Room room) {
    System.out.println(room.getDescription());
  }

  public void displayShortRoomDiscription(Room room) {
    System.out.println(room.getShortDescription());
  }

  public void displayAvailableDirections(ArrayList<Direction> availableDirections) {
    newline();
    System.out.print("You can go in these directions: ");
    System.out.print(availableDirections.toString());
    newline();

  }

  public void displayItems(ArrayList<Item> itemsInRoom) {
    System.out.println();
    System.out.print("Around you there is: ");
    System.out.print(itemsInRoom);
    System.out.println();
  }

  public void newline() {
    System.out.println();
  }

  public void displayWalkedIntoWall() {
    System.out.println("you walked into a wall, ouch");
  }

  public void displayOpendDoor() {
    System.out.println("You open the door.");
  }

  public void displayFoundLockedDoor() {
    System.out.println("You found a found a door that is locked.");
  }

  public void displayCombatOutcome(Enemy enemy, int healt) {
    System.out.println("Hitting the enemy");
    System.out.println(enemy.getName() + ": " + enemy.getHealth() + " remaining health");
    displayPlayerHealth(healt);
  }

  public void dispalyHittingOnlyAir() {
    System.out.println("You attack the air");
  }

  public void displayMustHaveWeapon() {
    System.out.println("You can only attack when a functioning weapon is equipped");
  }

  public void displayPlayerInventory(ArrayList<Item> inventory, Weapon rightHandWeapon) {
    System.out.println("In your inventory is:");
    for (int i = 0; i < inventory.size(); i++) {

      Item inventoryItem = inventory.get(i);
      System.out.print("- " + inventoryItem.getName() + " : " + inventoryItem.getDescription());
      if (inventoryItem instanceof Weapon weapon) {
        System.out.print(" , remaining uses: " + weapon.getRemainingUses());
      }
      newline();
    }
    if (rightHandWeapon != null) {
      System.out.println("\nYou are holding " + rightHandWeapon.getName() + " , with remaining uses: " + rightHandWeapon.getRemainingUses());
    }
  }

  public void displayPlayerHealth(int health) {
    System.out.print("You have " + health + " health - ");
    if (health <= 0) {
      System.out.println("R.I.P.");
    } else if (health < 25) {
      System.out.print("You are close to death");
    } else if (health > 25 && health < 50) {
      System.out.print("Be careful going forward");
    } else if (health > 50 && health < 75) {
      System.out.print("Consider getting some more health");
    } else {
      System.out.print("Go forth and fight!");
    }
    newline();


  }

  public void displayGameOver() {
    System.out.println("Game over!");
  }

  public void displayItemNotFound() {
    System.out.println("Item not found");
  }

  public void displayItemDropped() {
    System.out.println("You dropped the item");
  }

  public void displayYouNeedToUseAKey() {
    System.out.println("You need to use a key first.");
  }

  public void displayAmmoSucces(Equipable status) {
    switch (status){
      case INVALID -> System.out.println("no vaild ammo found.");
      case EQUIPING -> System.out.println("Reloading ammo into weapon");
    }
  }
  public void displayBackstory(){
    System.out.println();
    System.out.println("""
        One day a man approached you.
        He struck up a conversation.
        He seemed kind yet the whole time you could not shake, how eerie you felt.
        You attempted to leave, and when you did the whole world went dark.
        There was only you and the man.
        He had only these word for you.
        "Mortal, you time has ended. I am the reaper and i have come for your soul.
        Yet as i am bored, i will give you a chance!
        If you can be beat me in a game of luck and wits i will give you a prize.
        the prize is ten years of life.
        Good luck and entertain me well!"
        
        Then you woke up in the dungeon, to begin your search.
        """);
    System.out.println();
  }
}
