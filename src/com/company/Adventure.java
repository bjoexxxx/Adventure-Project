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

    //Prints out items in the room, if any.
    ArrayList<Item> lootableItemsInTheRoom = room.getLootTable();
    if (!lootableItemsInTheRoom.isEmpty()) { // loot table must contain something
      userinterface.displayItems(lootableItemsInTheRoom);
    }
    //Checks to see if player has tried going all directions, if yes, the available moves are displayed
    if (room.triedRooms()) {
      ArrayList<Direction> availbleRooms = room.getAllAvailbleDirections();
      userinterface.displayAvailableDirections(availbleRooms);
    }
  }

  private void dropItem(String itemName) {

    if (player.dropItem(itemName)) {
      userinterface.displayItemDropped();
    } else {
      userinterface.displayItemNotFound();
    }
  }

  private void takeItem(String itemName) {

    if (player.takeItem(itemName)) {
      userinterface.displayItemTaken();
    } else {
      userinterface.displayItemNotFound();
    }
  }

  public void eat(String itemName) {
    if (player.getPlayerInventory().isEmpty()) {
      userinterface.displayItemNotFound();
    } else {
      Consume status = player.eatFood(itemName);
      userinterface.displayFoodStatus(status);
    }
  }

  private void move(String whereTo) {

    Room currentRoom = player.getCurrentRoom();
    Room nextRoom = null;

    Direction directionOfNextRoom = currentRoom.giveDirectionFromString(whereTo);

    switch (directionOfNextRoom) {
      case NORTH -> nextRoom = currentRoom.getNorth();
      case SOUTH -> nextRoom = currentRoom.getSouth();
      case EAST -> nextRoom = currentRoom.getEast();
      case WEST -> nextRoom = currentRoom.getWest();
    }

    // Now player tries to move to the next room
    if (nextRoom == null) {
      userinterface.displayWalkedIntoWall();
    } else if (currentRoom.checkIfDoorIsLocked(directionOfNextRoom)) {
      userinterface.displayFoundLockedDoor();
    } else {
      player.playerMoveToRoom(nextRoom);
      //Display long description only on first time visit
      if (!nextRoom.getRoomIsVisited()) {
        userinterface.displayRoomDiscription(nextRoom);
      } else {
        userinterface.displayShortRoomDiscription(nextRoom);
      }
    }

  }

  private void open(String direction) {
    Door door = null;
    Room room = player.getCurrentRoom();
    Direction doorDirection = room.giveDirectionFromString(direction);

    switch (doorDirection) {
      case SOUTH -> door = room.getDoorSouth();
      case NORTH -> door = room.getDoorNorth();
      case WEST -> door = room.getDoorWest();
      case EAST -> door = room.getDoorEast();
    }

    //hvis der ikke er en lås på døren
    if (door.getKey().isEmpty()) {
      door.setOpen(false);
      userinterface.displayOpendDoor();
    } else { // hvis der er en lås på døren
      userinterface.displayYouNeedToUseAKey();
    }
  }

  private void checkInventory() {
    userinterface.displayPlayerInventory(player.getPlayerInventory(), player.getRightHand());

  }

  private void health() {
    userinterface.displayHealth(player.getHealth());
  }

  public void equip(String itemName){
    if (player.setRightHandWeapon(itemName)) {
      userinterface.displayItemEquipped();
    } else {
      userinterface.displayInvalid();
    }

  }

  public void attack(String target){

    if (player.canPlayerAttack()) { // checks if player i able to attack
      userinterface.displayMustHaveWeapon();
    } else if(player.attackTarget(target)) { // attacks taget or moves on to air in case of non specified target
      userinterface.displayHittingEnemy();
    } else {
      userinterface.dispalyHittingOnlyAir();
    }
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
        case ("equip") -> equip(secondWord);
        case ("attack") -> attack(secondWord);

      }
    }
  }

  public static void main(String[] args) {
    new Adventure().mainMenu();

  }
}
