package com.company;

import Characters.Enemy;
import Characters.Player;
import Items.Consume;
import Items.Item;

import java.util.ArrayList;
import java.util.Scanner;

public class Adventure {

  public final Scanner keyboard = new Scanner(System.in);
  private Player player;
  public WorldCreator creator;
  public Userinterface userinterface = new Userinterface();
  public boolean loop;

  private String firstWord(String fullCommand) {
    if (fullCommand.contains(" ")) {
      return fullCommand.substring(0, fullCommand.indexOf(' ')).toLowerCase();
    } else {
      return fullCommand.toLowerCase();
    }
  }

  private String secondWord(String fullCommand) {
    String actionWord = "";
    if (fullCommand.contains(" ")) {
      actionWord = capitalizeWord(fullCommand.substring(fullCommand.indexOf(' ') + 1));
      return actionWord;
    } else {
      return actionWord;
    }
  }

  private String capitalizeWord(String word) {
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
    if (room.getAllMonstersInRoom() != null) {
      userinterface.displayEnemies(room);
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
    Door doorToOpen = null;
    Room currentRoom = player.getCurrentRoom();
    Direction doorDirection = currentRoom.giveDirectionFromString(direction);

    switch (doorDirection) {
      case SOUTH -> doorToOpen = currentRoom.getDoorSouth();
      case NORTH -> doorToOpen = currentRoom.getDoorNorth();
      case WEST -> doorToOpen = currentRoom.getDoorWest();
      case EAST -> doorToOpen = currentRoom.getDoorEast();
    }

    //hvis der ikke er en lås på døren
    if (doorToOpen.getKey().isEmpty()) {
      doorToOpen.setOpen(false);
      userinterface.displayOpendDoor();
    } else { // hvis der er en lås på døren
      userinterface.displayYouNeedToUseAKey();
    }
  }

  private void checkInventory() {
    userinterface.displayPlayerInventory(player.getPlayerInventory(), player.getRightHand());

  }

  private void health() {
    userinterface.displayPlayerHealth(player.getHealth());
  }

  public void equip(String itemName) {
    if (player.setRightHandWeapon(itemName)) {
      userinterface.displayItemEquipped();
    } else {
      userinterface.displayInvalid();
    }

  }

  public void attack(String target) {
    Enemy mark = player.getCurrentRoom().searchAllMonstersInRoom(target);

    if (player.canPlayerAttack()) { // checks if player i able to attack
      userinterface.displayMustHaveWeapon();
    } else if (mark != null) { // attacks taget or moves on to air in case of non specified target
      player.attackTarget(mark);
      userinterface.displayCombatOutcome(mark, player.getHealth());
    } else {
      userinterface.dispalyHittingOnlyAir();
    }

    if (player.ischarecterAlive()) {
      exitGame();
      userinterface.displayGameOver();
    }
  }

  public void exitGame() {
    loop = false;
  }

  public void mainMenu() {

    // create the world
    creator = new WorldCreator();
    creator.createRooms();

    player = new Player(creator.getPlayerStartposition(), new ArrayList<>(), 100);
    player.setCurrentRoom(creator.getPlayerStartposition());

    userinterface.displayWelcome(player.getCurrentRoom());

    loop = true;
    while (loop) {
      userinterface.newline();
      String playerCommand = keyboard.nextLine();
      String firstWord = firstWord(playerCommand);
      String secondWord = secondWord(playerCommand);
      //todo make second int and make it work with allMonstersInRoom

      switch (firstWord) {

        case ("help") -> userinterface.help();
        case ("exit") -> exitGame();
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
