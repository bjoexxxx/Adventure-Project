package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Adventure {

  public final Scanner keyboard = new Scanner(System.in);
  private Player player;
  public WorldCreator creator;
  public Userinterface userinterface = new Userinterface();

  public boolean triedRooms(Room room) {
    return room.getTriedNorth() && room.getTriedEast() && room.getTriedSouth() && room.getTriedWest();
  }

  private void look() {
    Room room = player.getCurrentRoom();

    userinterface.displayRoomDiscription(player.getCurrentRoom());

    if (!player.getCurrentRoom().getLootTable().isEmpty()) {
      userinterface.displayItems(player.getCurrentRoom());
    }
    // the following code checks to see if player has tried going all directions, if yes, the available moves are displayed
    Room[] options = {room.getNorth(), room.getEast(), room.getSouth(), room.getWest()};
    String[] directions = {"north", "east", "south", "west"};
    if (triedRooms(room)) {
      System.out.println("You have these options:");
      for (int i = 0; i < options.length; i++) {
        if (options[i] != null) {
          System.out.println(directions[i] + " is available");
        }
      }
      userinterface.newline();

    }
  }

  public String firstWord(String fullCommand) {
    if (fullCommand.contains(" ")) {
      return fullCommand.substring(0, fullCommand.indexOf(' '));
    } else {
      return fullCommand;
    }
  }

  public String secondWord(String fullCommand) {
    if (fullCommand.contains(" ")) {
      return fullCommand.substring(fullCommand.indexOf(' ') + 1);
    } else {
      return "";
    }
  }

  public void mainMenu() {

    //creator.setPlayerPosition(null);
    creator = new WorldCreator();
    creator.buildRooms();

    player = new Player(creator.playerPosition, new ArrayList<>());
    player.setCurrentRoom(creator.getPlayerPosition());

    userinterface.displayWelcome(player.getCurrentRoom());

    boolean loop = true;
    while (loop) {

      String playerCommand = keyboard.nextLine();
      String firstWord = firstWord(playerCommand);
      String secondWord = secondWord(playerCommand);

      switch (firstWord) {

        case ("help") -> userinterface.help();
        case ("exit") -> loop = false;
        case ("look") -> look();
        case ("go") -> player.move(secondWord);
        case ("open") -> player.open(secondWord);
        case ("take") -> player.pickupItem(secondWord);
        case ("inventory") -> player.checkInventory();
        case ("drop") -> player.dropItem(secondWord);

      }
    }

  }

  public static void main(String[] args) {
    // write your code here

    new Adventure().mainMenu();


  }
}
