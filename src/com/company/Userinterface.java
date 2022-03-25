package com.company;

import Items.Consume;
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
    System.out.println();

  }

  public void displayUnknownCommand(){

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

  public void displayYouHaveOptionsDirections() {
    System.out.println("You have these options:");
  }

  public void displayAvailableDirections(int directionNumber) {
    String[] directions = {"north", "east", "south", "west"};
    System.out.println(directions[directionNumber] + " is available");
  }

  public void displayItems(Room room) {
    System.out.println();
    System.out.print("Around you there is: ");
    System.out.print(room.getLootTable());
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

  public void displayPlayerInventory(ArrayList<Item> inventory, Weapon weapon) {
    System.out.println("In your inventory is:");
    inventory.forEach((n) -> System.out.println(n));
    System.out.println("You are holding "+weapon.getName());
  }

  public void displayHealth(int health) {
    System.out.print("You have " + health + " health - ");
    if (health < 25) {
      System.out.print("You are close to death");
    } else if (health > 25 && health < 50) {
      System.out.print("Be careful going forward");
    } else if (health > 50 && health < 75) {
      System.out.print("Consider getting some more health");
    } else {
      System.out.print("Go forth and explore!");
    }
    newline();


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
}
