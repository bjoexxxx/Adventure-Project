package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Adventure {

  public final Scanner keyboard = new Scanner(System.in);
  private Player player;
  public WorldCreator creator;

  public boolean triedRooms (Room room) {
    return room.getTriedNorth() && room.getTriedEast() && room.getTriedSouth() && room.getTriedWest();
  }

  private void look() {
    Room room = player.getCurrentRoom();
    System.out.println(room.getDescription());
    if (!player.getCurrentRoom().getLootTable().isEmpty()){
    System.out.println(player.getCurrentRoom().getLootTable());}
    // the following code checks to see if player has tried going all directions, if yes, the available moves are displayed
    Room[] options = {room.getNorth(), room.getEast(), room.getSouth(), room.getWest()};
    String[] directions = {"north", "east", "south", "west"};
    if (triedRooms(room)){
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

  public String firstWord(String fullCommand) {
    if (fullCommand.contains(" ")) {
      return fullCommand.substring(0, fullCommand.indexOf(' '));
    } else {
      return fullCommand;
    }
  }

  public String secondWord(String fullCommand) {
    if (fullCommand.contains(" ")){
      return fullCommand.substring(fullCommand.indexOf(' ')+1);
    } else {
      return "";
    }
  }

  public void mainMenu() {

    //creator.setPlayerPosition(null);
    creator = new WorldCreator();
    creator.buildRooms();

    player = new Player(creator.playerPosition,new ArrayList<>());
    player.setCurrentRoom(creator.getPlayerPosition());

    System.out.println("Welcome to the game!");
    System.out.println("In case of confusion input \"help\".");
    System.out.println("\n" + player.getCurrentRoom().getDescription());

    boolean loop = true;
    while (loop) {

      String playerCommand = keyboard.nextLine().toLowerCase();
      String firstWord = firstWord(playerCommand);
      String secondWord = secondWord(playerCommand);

      switch (firstWord) {

        case ("help") -> help();
        case ("exit") -> loop = false;
        case ("look") -> look();
        case ("go") -> player.move(secondWord);
        case ("open") -> player.open(secondWord);

      }
    }

  }

  public static void main(String[] args) {
    // write your code here

    new Adventure().mainMenu();


  }
}
