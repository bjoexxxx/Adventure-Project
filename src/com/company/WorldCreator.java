package com.company;

public class WorldCreator {
  public Room playerPosition;

  public WorldCreator() {
    this.playerPosition = null;
  }

  public void createRooms() {
    Room room1 = new Room("Staging Grounds", "you find yourself at the entrance of a dungeon. " +
        "This is where heroes pick up their arms and venture forth", "Staging area at the start of the dungeon.");
    Room room2 = new Room("Forsaken Chapel", "A room of holy congregation long abandoned", "Forsaken chapel");
    Room room3 = new Room("Inner Sanctum", "Where priests keep their secrets", "Inner sanctum ");
    Room room4 = new Room("Dank Cave", "A dark narrow cavern", "A dank cave");
    Room room5 = new Room("Boss Room", "A great ornate hall, with a massive figure towering over you", "Great ornate hall");
    Room room6 = new Room("Room Of Forbidden Idols", "Odd trinkets adorn this room, you have never seen their like before", " Room of forbidden Idols");
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
    playerPosition = room1;

    // skriv rummets navn, den retning den skal sidde, lav new door, false means closed
    room8.setDoorEast(new Door(false));
    room8.setDoorNorth(new Door(false));
    room1.setDoorSouth(new Door(false));



    // Make items
    room1.setLootTable(createLoot("sword"));
    room1.setLootTable(createLoot("lambas"));
    room3.setLootTable(createLoot("flute"));


  }

  public Room getPlayerPosition() {
    return this.playerPosition;
  }

  public Item createLoot(String name) {

    switch (name) {
      case "sword" -> {
        return new Item("Sword", "Its very pointy");
      }
      case "lambas" -> {
        return new Item("Lambras", "An elvish flatbred");
      }
      case "flute" -> {
        return new Item("Boneflute", "Can make a eerie high pitch tone");
      }
      default -> {
        return null;
      }

    }
  }
}


