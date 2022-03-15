package com.company;

import java.util.Scanner;

public class Main {

  public final Scanner keyboard = new Scanner(System.in);
  public Room playerPosition;

  private void buildRooms() {
    Room room1 = new Room("Staging Grounds", "Where heroes pick up their arms");
    Room room2 = new Room("Forsaken Chapel", "A room of holy congregation long abandoned");
    Room room3 = new Room("Inner Sanctum", "Where priests keep their secrets");
    Room room4 = new Room("Dank Cave", "A dark narrow cavern");
    Room room5 = new Room("Boss Room", "A great ornate hall, with a massive figure towering over you");
    Room room6 = new Room("Room Of Forbidden Idols", "Odd trinkets adorn this room, you have never seen their like before");
    Room room7 = new Room("Dark Portal", "A grouping of stone, crackling with power that gives an ominous feeling ");
    Room room8 = new Room("Blood Grounds", "A great carnage has been committed here");
    Room room9 = new Room("Torture Room", "Blood racks and chains litter the room, some even have human remains on them");


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

  private void look () {
    System.out.println(playerPosition.getDescription());
  }

  private void help () {
    System.out.println();
    System.out.println("You have the following options in the game:");
    System.out.println("-\"exit\" will end the game. ");
    System.out.println("-\"look\" will display the description of the room you're in. ");
    System.out.println("-\"go north\" will move you north, if that direction is clear." +
        " The same applies for go \"south\", \"go east\" and \"go west\".");
    System.out.println();


  }


  public void movePlayer(Room room) {
    if (room == null) {
      System.out.println("you walked into a wall, ouch");
    } else {
      playerPosition = room;
      System.out.println(playerPosition.getDescription());
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

      }
    }

  }

  public static void main(String[] args) {
    // write your code here
    new Main().mainMenu();


  }
}
