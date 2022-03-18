package com.company;

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
    System.out.println("You have the following options in the game:");
    System.out.println("-\"exit\" will end the game. ");
    System.out.println("-\"look\" will display the description of the room you're in. ");
    System.out.println("-\"go north\" will move you north, if that direction is clear." +
        " The same applies for go \"south\", \"go east\" and \"go west\".");
    System.out.println();

  }

  public void displayRoomDiscription (Room room){
    System.out.println(room.getDescription());
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

}
