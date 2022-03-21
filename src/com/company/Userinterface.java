package com.company;

import java.util.ArrayList;

public class Userinterface {

  public Userinterface() {
  }

  public void displayWelcome (Room room) {
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
    System.out.println();

  }

  public void displayRoomDiscription (Room room){
    System.out.println(room.getDescription());
  }

  public void displayShortRoomDiscription (Room room){
    System.out.println(room.getShortDescription());
  }

  public void displayYouHaveOptionsDirections () {
    System.out.println("You have these options:");
  }

  public void displayAvailableDirections (int directionNumber) {
    String[] directions = {"north", "east", "south", "west"};
    System.out.println(directions[directionNumber] + " is available");
  }

  public void displayItems (Room room) {
    System.out.println();
    System.out.print("Around you there is: ");
    System.out.print(room.getLootTable());
    System.out.println();
  }

  public void newline(){
    System.out.println();
  }

  public void displayWalkedIntoWall (){
    System.out.println("you walked into a wall, ouch");
  }

  public void displayOpendDoor () {
    System.out.println("You open the door.");
  }

  public void displayFoundLockedDoor () {
    System.out.println("You found a found a door that is locked.");
  }

  public void displayPlayerInventory (ArrayList<Item> inventory) {
    System.out.println("In your inventory is:");
    inventory.forEach((n) -> System.out.println(n));
  }

  public void displayItemNotFound () {
    System.out.println("Item not found");
  }
}
