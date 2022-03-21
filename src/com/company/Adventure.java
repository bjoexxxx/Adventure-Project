package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Adventure {

  public final Scanner keyboard = new Scanner(System.in);
  private Player player;
  public WorldCreator creator;
  public Userinterface userinterface = new Userinterface();

  private String firstWord(String fullCommand) {
    if (fullCommand.contains(" ")) {
      return fullCommand.substring(0, fullCommand.indexOf(' '));
    } else {
      return fullCommand;
    }
  }

  private String secondWord(String fullCommand) {
    if (fullCommand.contains(" ")) {
      return fullCommand.substring(fullCommand.indexOf(' ') + 1);
    } else {
      return "";
    }
  }

  public void mainMenu() {

    //creator.setPlayerPosition(null);
    creator = new WorldCreator();
    creator.createRooms();

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
        case ("look") -> player.look();
        case ("go") -> player.move(secondWord);
        case ("open") -> player.open(secondWord);
        case ("take") -> player.takeItem(secondWord);
        case ("inventory") -> player.checkInventory();
        case ("drop") -> player.dropItem(secondWord);

      }
    }
  }

  public static void main(String[] args) {
      new Adventure().mainMenu();
  }
}
