package com.company;

import Items.Consume;
import Items.Item;

import java.util.ArrayList;
import java.util.Scanner;

public class Adventure {

  public final Scanner keyboard = new Scanner(System.in);
  private Player player;
  public WorldCreator creator;
  public Userinterface userinterface = new Userinterface();

  private String firstWord(String fullCommand) {
    if (fullCommand.contains(" ")) {
      return fullCommand.substring(0, fullCommand.indexOf(' ')).toLowerCase();
    } else {
      return fullCommand.toLowerCase();
    }
  }

  private String secondWord(String fullCommand) {
    if (fullCommand.contains(" ")) {
      return capitalize(fullCommand.substring(fullCommand.indexOf(' ') + 1));
    } else {
      return "";
    }
  }

  private String capitalize(String word) {
    return word.substring(0, 1).toUpperCase() + word.substring(1);
  }

  private void look() {
    Room room = player.getCurrentRoom();

    userinterface.displayRoomDiscription(room);

    if (!player.getCurrentRoom().getLootTable().isEmpty()) { // loot table must contain something
      userinterface.displayItems(room);
    }
    // the following code checks to see if player has tried going all directions, if yes, the available moves are displayed
    if (room.triedRooms()) {
      userinterface.displayYouHaveOptionsDirections();
      Room[] options = room.getAllDirections();
      for (int i = 0; i < options.length; i++) {
        if (options[i] != null) {
          userinterface.displayAvailableDirections(i);
        }
      }
    }
  }

  private void dropItem(String searchWord) {

    if (player.dropItem(searchWord)) {
      userinterface.displayItemDropped();
    } else {
      userinterface.displayItemNotFound();
    }
  }

  private void takeItem(String searchWord) {

    if (player.takeItem(searchWord)) {
      userinterface.displayItemTaken();
    } else {
      userinterface.displayItemNotFound();
    }
  }

  public void eat(String secondWord) {
    if (player.getInventory().isEmpty()) {
      userinterface.displayItemNotFound();
    } else {
      Consume status = player.eatFood(secondWord);
      userinterface.displayFoodStatus(status);
    }
  }

  private void move(String direction) {

    Room room = player.getCurrentRoom();

    switch (direction) {
      case "North", "N" -> {
        room.setTriedNorth(true);
        room = room.getNorth();
      }
      case "South", "S" -> {
        room.setTriedSouth(true);
        room = room.getSouth();
      }
      case "East", "E" -> {
        room.setTriedEast(true);
        room = room.getEast();
      }
      case "West", "W" -> {
        room.setTriedWest(true);
        room = room.getWest();
      }
    }
    // the steps to make a move
    if (room == null) { //checks if the next room is a wall
      userinterface.displayWalkedIntoWall();
    } else if (!player.getCurrentRoom().checkdoors(room)) { //checks if there is a looked door and checks locations
      userinterface.displayFoundLockedDoor();
    } else { //if player makes a valid move
      player.playerMove(room);

      //Display long description only on first time visit
      if (!room.getIsVisited()) {
        userinterface.displayRoomDiscription(player.getCurrentRoom());
      } else {
        userinterface.displayShortRoomDiscription(player.getCurrentRoom());
      }
    }
  }

  private void open(String direction) {
    Door door = null;
    Room room = player.getCurrentRoom();

    switch (direction) {
      case "South" -> door = room.getDoorSouth();
      case "North" -> door = room.getDoorNorth();
      case "West" -> door = room.getDoorWest();
      case "East" -> door = room.getDoorEast();
    }

    //hvis der ikke er en lås på døren
    if (door.getKey().isEmpty()) {
      door.setOpen(true);
      userinterface.displayOpendDoor();
    } else { // hvis der er en lås på døren
      userinterface.displayYouNeedToUseAKey();
    }
  }

  private void checkInventory() {
    userinterface.displayPlayerInventory(player.getInventory());
  }

  private void health() {
    int health = player.getHealth();
    userinterface.displayHealth(health);

  }

  public void mainMenu() {

    // create the world
    creator = new WorldCreator();
    creator.createRooms();

    player = new Player(creator.playerPosition, new ArrayList<>(), 100);
    player.setCurrentRoom(creator.getPlayerPosition());

    userinterface.displayWelcome(player.getCurrentRoom());

    boolean loop = true;
    while (loop) {
      userinterface.newline();
      String playerCommand = keyboard.nextLine();
      String firstWord = firstWord(playerCommand);
      String secondWord = secondWord(playerCommand);

      switch (firstWord) {

        case ("help") -> userinterface.help();
        case ("exit") -> loop = false;
        case ("look") -> look();
        case ("go") -> move(secondWord);
        case ("open") -> open(secondWord);
        case ("take") -> takeItem(secondWord);
        case ("inventory") -> checkInventory();
        case ("drop") -> dropItem(secondWord);
        case ("health") -> health();
        case ("eat") -> eat(secondWord);

      }
    }
  }

  public static void main(String[] args) {
    new Adventure().mainMenu();

  }
}
