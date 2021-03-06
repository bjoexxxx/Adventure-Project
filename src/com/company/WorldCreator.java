package com.company;

import Characters.Enemy;
import Items.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class WorldCreator {
  private Room playerStartposition;
  Random roll = new Random();

  public WorldCreator() {
    this.playerStartposition = null;
  }

  public void createRooms() {
    Room room1 = new Room("Staging Grounds", "you find yourself at the entrance of a dungeon. " +
        "This is where heroes pick up their arms and venture forth", "Staging area at the start of the dungeon.");
    Room room2 = new Room("Forsaken Chapel", "A room of holy congregation long abandoned.\n" +
        "Books and half torn pages lie on the floor, written in a language that you do not understand.", "Forsaken chapel");
    Room room3 = new Room("Inner Sanctum", "In the back of the chapel, you find a small shrine,where the priests likely kept their secrets.\n" +
        "Whatever was kept here has long since been moved, and now this place seems a abandoned as the rest of the chapel", "Inner sanctum ");
    Room room4 = new Room("Dank Cave", "A dark narrow cavern", "A dank cave");
    Room room5 = buildBossroom();
    Room room6 = new Room("Room Of Forbidden Idols", "Odd trinkets adorn this room, you have never seen their like " +
        "before.\n Small statures of demons and miniatures of grotesque are placed around a central podium." +
        " On the podium lies a magical item.", " Room of forbidden Idols");
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
    room8.setWest(room7);
    room9.setWest(room8);
    playerStartposition = room1;

    // skriv rummets navn, den retning den skal sidde, lav new door, true means closed
    room4.setDoorNorth(new Door(true, ""));
    room2.setDoorWest(new Door(true, ""));
    room8.setDoorNorth(new Door(true, ""));
    room9.setDoorNorth(new Door(true, ""));

    // Make items
    room1.setLootTable(randomizeLoot(room1));
    room1.setLootTable(createSpecificMelee("dagger"));
    room1.setLootTable(createSpecificRanged("gun"));
    room2.setLootTable(randomizeLoot(room2));
    room3.setLootTable(randomizeLoot(room3));
    room4.setLootTable(randomizeLoot(room4));
    room6.setLootTable(randomizeLoot(room6));
    room7.setLootTable(randomizeLoot(room7));
    room8.setLootTable(randomizeLoot(room8));
    room9.setLootTable(randomizeLoot(room9));

    //Spawn enemies
    room1.setAllMonstersInRoom(randomizeEnemies(room1));
    room2.setAllMonstersInRoom(randomizeEnemies(room2));
    room3.setAllMonstersInRoom(randomizeEnemies(room3));
    room4.setAllMonstersInRoom(randomizeEnemies(room4));
    room5.setAllMonstersInRoom(new Enemy(room5,new ArrayList<Item>(),500,"Grimreaper",
        new Melee("Scythe","Favorite tool of the reaper",50,1000)));
    room6.setAllMonstersInRoom(randomizeEnemies(room6));
    room7.setAllMonstersInRoom(randomizeEnemies(room7));
    room8.setAllMonstersInRoom(randomizeEnemies(room8));
    room9.setAllMonstersInRoom(randomizeEnemies(room9));

  }

  private Room buildBossroom(){
    return new Room("Boss Room",
        "A great ornate hall, with a massive figure towering over you",
        "Great ornate hall");
  }

  public Room getPlayerStartposition() {
    return this.playerStartposition;
  }

  private Item randomizeLoot(Room room){
    int maxItems = roll.nextInt(0,4);
    for (int i=0; i<= maxItems; i++) {

      if (i == maxItems) {
        return createItem();
      }

      room.setLootTable(createItem());
    }
    return null;
  }

  private Enemy randomizeEnemies(Room room){
    int maxItems = roll.nextInt(0,4);
    for (int i=0; i<= maxItems; i++) {

      if (i == maxItems) {
        return createEnemy(room);
      }

      room.setAllMonstersInRoom(createEnemy(room));
    }
    return null;
  }

  private Enemy createEnemy(Room room) {
    int number = roll.nextInt(1, 8);
    switch (number) {


      case 1 -> {
        return new Enemy(room,new ArrayList<Item>(),40,"Succubus",createRanged());
      }
      case 2 -> {
        return new Enemy(room,new ArrayList<Item>(),30,"Ghoul",createMelee());
      }
      case 3 -> {
        return new Enemy(room,new ArrayList<Item>(),20,"Skeleton",createMelee());
      }
      case 4 -> {
        return new Enemy(room,new ArrayList<Item>(),10,"Goblin",createRanged());
      }
      case 5 -> {
        return new Enemy(room,new ArrayList<Item>(),50,"Orc",createMelee());
      }
      case 6 -> {
        return new Enemy(room,new ArrayList<Item>(),25,"Cultist",createRanged());
      }
      case 7 -> {
        return new Enemy(room,new ArrayList<Item>(),60,"Fiend",createMelee());
      }
      default -> {
        return null;
      }
    }
  }

  private Melee createMelee(){
    int number = roll.nextInt(1,8);
    switch (number) {

      case 1 -> {
        return new Melee("Sword", "Its very pointy", 5, 100);
      }
      case 2 -> {
        return new Melee("Halberd", "Sharp and Pointy", 7, 50);
      }
      case 3 -> {
        return new Melee("Axe", "Sharp", 9, 25);
      }
      case 4 -> {
        return new Melee("Mace", "Heavy", 15, 70);
      }
      case 5 -> {
        return new Melee("Dagger", "Light and dinky", 2, 200);
      }
      case 6 -> {
        return new Melee("Spear", "Long and pointy", 10, 70);
      }
      case 7 -> {
        return new Melee("Saber", "A one edged sword", 8, 90);
      }
      default -> {
        return null;
      }

    }
  }
  private Melee createSpecificMelee(String name){
    switch (name) {

      case "sword" -> {
        return new Melee("Sword", "Its very pointy", 5, 100);
      }
      case "halberd" -> {
        return new Melee("Halberd", "Sharp and Pointy", 7, 50);
      }
      case "axe" -> {
        return new Melee("Axe", "Sharp", 9, 25);
      }
      case "mace" -> {
        return new Melee("Mace", "Heavy", 15, 70);
      }
      case "dagger" -> {
        return new Melee("Dagger", "Light and dinky", 2, 200);
      }
      case "spear" -> {
        return new Melee("Spear", "Long and pointy", 10, 70);
      }
      case "saber" -> {
        return new Melee("Saber", "A one edged sword", 8, 90);
      }
      default -> {
        return null;
      }

    }
  }

  private Ranged createRanged() {
    int number = roll.nextInt(1,3);

    switch (number) {
      case 1 -> {
        return new Ranged("Rifle", "Bigger pew", 20, 5);
      }
      case 2 -> {
        return new Ranged("Gun", "pew pew", 10, 10);
      }
      default -> {
        return null;
      }

    }
  }
  private Ranged createSpecificRanged(String name) {

    switch (name) {
      case "rifle" -> {
        return new Ranged("Rifle", "Bigger pew", 20, 5);
      }
      case "gun" -> {
        return new Ranged("Gun", "pew pew", 10, 10);
      }
      default -> {
        return null;
      }

    }
  }

  private Item createItem() {
    int number = roll.nextInt(1,10);

    switch (number) {
      case 1 -> {
        return new Food("Lambras", "An elvish flatbred", Consume.EDIBLE, 20);
      }
      case 2 -> {
        return new Food("Mushrooms", "Smells funky", Consume.POISONOUS, -25);
      }
      case 3 -> {
        return new Food("Water", "Feeling thirsty?", Consume.EDIBLE, 5);
      }
      case 4 -> {
        return new Ammo("Ammo", "1 clip with 10 bullets");
      }
      case 5 -> {
        return new Food("Potion", "Full heal", Consume.EDIBLE, 100);
      }
      case 6 -> {
        return new Food("Ham", "Honey roasted", Consume.EDIBLE, 30);
      }
      case 7 -> {
        return new Food("Berries", "Mostly sweet", Consume.EDIBLE, 10);
      }
      case 8 -> {
        return new Food("Cake", "This was not supposed to exsist", Consume.EDIBLE, 50);
      }
      case 9 -> {
        return new Food("Ration", "Military issue", Consume.EDIBLE, 15);
      }
      default -> {
        return null;
      }
    }
  }
}


