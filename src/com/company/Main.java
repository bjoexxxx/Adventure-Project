package com.company;

import java.util.Scanner;

public class Main {

  public final Scanner keyboard = new Scanner(System.in);
  public Room playerPosition;

  public void buildRooms() {
    Room room1 = new Room("1", "derp");
    Room room2 = new Room("2", "derp");
    Room room3 = new Room("3", "derp");
    Room room4 = new Room("4", "derp");
    Room room5 = new Room("5", "derp");
    Room room6 = new Room("6", "derp");
    Room room7 = new Room("7", "derp");
    Room room8 = new Room("8", "derp");
    Room room9 = new Room("9", "derp");


    room1.setEast(room2);
    room1.setSouth(room4);
    room2.setEast(room3);
    room2.setWest(room1);
    room3.setWest(room2);
    room3.setSouth(room6);
    room4.setNorth(room1);
    room4.setSouth(room7);
    room5.setSouth(room8);
    room6.setNorth(room3);
    room6.setSouth(room9);
    room7.setNorth(room4);
    room7.setEast(room8);
    room8.setEast(room9);
    room8.setNorth(room5);
    room8.setWest(room7);
    room9.setWest(room8);
    room9.setNorth(room6);
    playerPosition = room1;

  }

  private void look () {
    System.out.println(playerPosition.getName() + ": " + playerPosition.getDescription());
  }

  private void help () {
    System.out.println();
    System.out.println("You have the following option:");
    System.out.println("-\"exit\" will end the game. ");
    System.out.println("-\"look\" will display the description of the room you're in. ");
    System.out.println("-\"go north\" will move you north, if that direction is clear." +
        " The same applies for go \"south\", \"go east\" and \"go west\".");


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

    boolean loop = true;
    while (loop) {

      String fullAnswer = keyboard.nextLine();

      switch (fullAnswer) {

        case ("help") -> help();
        case ("exit") -> loop = false;
        case ("look") -> look();
        case ("go north") -> movePlayer(playerPosition.goNorth());
        case ("go south") -> movePlayer(playerPosition.goSouth());
        case ("go east") -> movePlayer(playerPosition.goEast());
        case ("go west") -> movePlayer(playerPosition.goWest());

      }
    }
  }

  public static void main(String[] args) {
    // write your code here
    new Main().mainMenu();


  }
}
