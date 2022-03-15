package com.company;

import java.util.Scanner;

public class Adventure {

  public final Scanner keyboard = new Scanner(System.in);
  public Room playerPosition;

  private void buildRooms() {
    Room room1 = new Room("Staging Grounds", "you find yourself at the entrance of a dungeon. " +
        "This is where heroes pick up their arms and venture forth", "Staging area at the start of the dungeon.");
    Room room2 = new Room("Forsaken Chapel", "A room of holy congregation long abandoned", "Forsaken chapel");
    Room room3 = new Room("Inner Sanctum", "Where priests keep their secrets", "Inner sanctum ");
    Room room4 = new Room("Dank Cave", "A dark narrow cavern", "A dank cave");
    Room room5 = new Room("Boss Room", "A great ornate hall, with a massive figure towering over you", "Great ornate hall");
    Room room6 = new Room("Room Of Forbidden Idols", "Odd trinkets adorn this room, you have never seen their like before", " Room of forbidden Idols");
    Room room7 = new Room("Dark Portal", "A grouping of stone, crackling with power that gives an ominous feeling ", "A dark portal");
    Room room8 = new Room("Blood Grounds", "A great carnage has been committed here", "Blood soaked grounds");
    Room room9 = new Room("Torture Room", "Blood racks and chains litter the room, some even have human remains on them", "the torture room");

    // oversigt over dungeon:
//    ------------------------
//   | room1 = room 2 = room3 |
//   |   ||  ----------  ||   |
//   | room4 | room5  | room6 |
//   |   ||  |   ||   |  ||   |
//   | room7 = room8  = room9 |
//    ------------------------

    // added a door & true means open, false means closed
    room1.setDoor(new Door(false, "steel door", room1, room4));

    //connecting the rooms
    room1.setEast(room2);
    room1.setSouth(room4);
    room2.setEast(room3);
    room3.setSouth(room6);
    room4.setSouth(room7);
    room5.setSouth(room8);
    room6.setSouth(room9);
    room7.setEast(room8);
    room8.setEast(room9);
    room9.setWest(room8);
    playerPosition = room1;


  }

  private void look() {
    System.out.println(playerPosition.getDescription());
    // the following code checks to see if player has tried going all directions, if yes, the available moves are displayed
    Room[] options = {playerPosition.getNorth(), playerPosition.getEast(), playerPosition.getSouth(), playerPosition.getWest()};
    String[] directions = {"north", "east", "south", "west"};
    if (playerPosition.getTriedNorth() &&
        playerPosition.getTriedEast() &&
        playerPosition.getTriedSouth() &&
        playerPosition.getTriedWest()) {
      System.out.println("You have these options:");
      for (int i = 0; i < options.length; i++) {
        if (options[i] != null) {
          System.out.println(directions[i] + " is available");
        }
      }
      System.out.println();
    }
  }

  private void help() {
    System.out.println(); // all the help funktions listed below
    System.out.println("You have the following options in the game:");
    System.out.println("-\"exit\" will end the game. ");
    System.out.println("-\"look\" will display the description of the room you're in. ");
    System.out.println("-\"go north\" will move you north, if that direction is clear." +
        " The same applies for go \"south\", \"go east\" and \"go west\".");
    System.out.println();


  }

  public boolean checkdoor(Room room) {
    //comparing to see if the current room and the next room has a door between them
    return playerPosition == playerPosition.getDoor().getStartRoom() && room == playerPosition.getDoor().getEndRoom();
  }

  public void openDoor() {
    playerPosition.getDoor().setOpen(true);
    System.out.println("You open the " + playerPosition.getDoor().getTypeOfDoor() + ".");
  }

  public void movePlayer(Room room) {
    if (room == null) { //checks if the next room is a wall
      System.out.println("you walked into a wall, ouch");
    } else if (playerPosition.getDoor() != null && !playerPosition.getDoor().isOpen()  && checkdoor(room)) { //checks if there is a looked type of door and checks locations
      System.out.println("You found a " + playerPosition.getDoor().getTypeOfDoor() + " that is locked.");
    } else { //if player makes a valid move
      playerPosition.setIsVisited(true);
      playerPosition = room;
      if (!playerPosition.getIsVisited()) {
        System.out.println(playerPosition.getDescription());
      } else {
        System.out.println(playerPosition.getShortDescription());
      }
    }
  }

  public void mainMenu() {

    buildRooms();

    System.out.println("Welcome to the game!");
    System.out.println("In case of confusion input \"help\".");
    System.out.println("\n" + playerPosition.getDescription());

    boolean loop = true;
    while (loop) {

      String fullAnswer = keyboard.nextLine().toLowerCase();

      switch (fullAnswer) {

        case ("help") -> help();
        case ("exit") -> loop = false;
        case ("look") -> look();
        case ("go north") -> movePlayer(playerPosition.getNorth());
        case ("go south") -> movePlayer(playerPosition.getSouth());
        case ("go east") -> movePlayer(playerPosition.getEast());
        case ("go west") -> movePlayer(playerPosition.getWest());
        case ("open door") -> openDoor();

      }
    }

  }

  public static void main(String[] args) {
    // write your code here
    new Adventure().mainMenu();


  }
}
