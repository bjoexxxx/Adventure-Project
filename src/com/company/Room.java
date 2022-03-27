package com.company;

import Items.Item;

import java.util.ArrayList;

public class Room {
  private ArrayList<Item> lootTable;
  private final String name;
  private final String description;
  private final String shortDescription;
  private boolean roomIsVisted;
  private boolean triedNorth;
  private boolean triedEast;
  private boolean triedSouth;
  private boolean triedWest;
  private Room south;
  private Room north;
  private Room east;
  private Room west;
  private Door doorNorth;
  private Door doorSouth;
  private Door doorEast;
  private Door doorWest;


  public Room(String name, String description, String shortDescription) {
    this.name = name;
    this.description = description;
    this.north = null;
    this.east = null;
    this.south = null;
    this.west = null;
    this.roomIsVisted = false;
    this.triedNorth = false;
    this.triedEast = false;
    this.triedSouth = false;
    this.triedWest = false;
    this.shortDescription = shortDescription;
    this.doorSouth = null;
    this.doorNorth = null;
    this.doorWest = null;
    this.doorEast = null;
    this.lootTable = new ArrayList<>();

  }

  public Door getDoorEast() {
    return doorEast;
  }

  public Door getDoorWest() {
    return doorWest;
  }

  public Door getDoorNorth() {
    return doorNorth;
  }

  public Door getDoorSouth() {
    return doorSouth;
  }

  public void setDoorNorth(Door door) {
    this.doorNorth = door;
    if (getNorth().doorSouth == null) {
      getNorth().setDoorSouth(door);
    }
  }

  public void setDoorSouth(Door door) {
    this.doorSouth = door;
    if (getSouth().doorNorth == null) {
      getSouth().setDoorNorth(door);
    }
  }

  public void setDoorEast(Door door) {
    this.doorEast = door;
    if (getEast().doorWest == null) {
      getEast().setDoorWest(door);
    }
  }

  public void setDoorWest(Door door) {
    this.doorWest = door;
    if (getWest().doorEast == null) {
      getWest().setDoorEast(door);
    }
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public boolean getRoomIsVisited() {
    return this.roomIsVisted;
  }

  public void setRoomIsVisted(boolean visited) {
    this.roomIsVisted = visited;
  }

  public String getDescription() {
    return description;
  }

  public void setNorth(Room room) {
    this.north = room;
    if (room.getSouth() == null) {
      room.setSouth(this);
    }
  }

  public void setEast(Room room) {
    this.east = room;
    if (room.getWest() == null) {
      room.setWest(this);
    }
  }

  public void setSouth(Room room) {
    this.south = room;
    if (room.getNorth() == null) {
      room.setNorth(this);
    }

  }

  public void setWest(Room room) {
    this.west = room;
    if (room.getEast() == null) {
      room.setEast(this);
    }
  }

  public Room getEast() {
    return this.east;
  }

  public Room getNorth() {
    return this.north;
  }

  public Room getSouth() {
    return this.south;
  }

  public Room getWest() {
    return this.west;
  }

  public boolean getTriedNorth() {
    return triedNorth;
  }

  public boolean getTriedEast() {
    return triedEast;
  }

  public boolean getTriedSouth() {
    return triedSouth;
  }

  public boolean getTriedWest() {
    return triedWest;
  }

  public void setTriedNorth(boolean triedNorth) {
    this.triedNorth = triedNorth;
  }

  public void setTriedEast(boolean triedEast) {
    this.triedEast = triedEast;
  }

  public void setTriedSouth(boolean triedSouth) {
    this.triedSouth = triedSouth;
  }

  public void setTriedWest(boolean triedWest) {
    this.triedWest = triedWest;
  }

  public void setLootTable(Item loot) {
    this.lootTable.add(loot);
  }

  public ArrayList<Item> getLootTable() {
    return this.lootTable;
  }

  public void removeItem(Item loot) {
    this.lootTable.remove(loot);
  }

  public boolean triedRooms() {
    return this.getTriedNorth() && this.getTriedEast() && this.getTriedSouth() && this.getTriedWest();
  }

  public boolean checkIfDoorsAreLocked(Room room) {
    if (room == this.getNorth() && this.getDoorNorth() != null) {
      return this.getDoorNorth().isOpen();
    } else if (room == this.getSouth() && this.getDoorSouth() != null) {
      return this.getDoorSouth().isOpen();
    } else if (room == this.getEast() && this.getDoorEast() != null) {
      return this.getDoorEast().isOpen();
    } else if (room == this.getWest() && this.getDoorWest() != null) {
      return this.getDoorWest().isOpen();
    } else {
      return true;
    }
  }

  public Item searchItemsInRoom(String itemName) {
    for (int i = 0; i < lootTable.size(); i++) {
      Item temp = lootTable.get(i);
      if (temp.getName().equals(itemName)) {
        return temp;
      }
    }
    return null;
  }

  public ArrayList<Direction> getAllAvailbleDirections() {

    ArrayList<Direction> availbleDirections = new ArrayList<>();
    if (north != null) {
      availbleDirections.add(Direction.NORTH);
    }
    if (east != null) {
      availbleDirections.add(Direction.EAST);
    }
    if (west != null) {
      availbleDirections.add(Direction.WEST);
    }
    if (south != null) {
      availbleDirections.add(Direction.SOUTH);
    }
    return availbleDirections;
  }

  public Direction getRoom(String direction) {

    if (direction.equals("North") || direction.equals("N")) {
      setTriedNorth(true);
      return Direction.NORTH;
    } else if (direction.equals("South") || direction.equals("S")) {
      setTriedSouth(true);
      return Direction.SOUTH;
    } else if (direction.equals("East") || direction.equals("E")) {
      setTriedEast(true);
      return Direction.EAST;
    } else if(direction.equals("West") || direction.equals("W")) {
      setTriedWest(true);
      return Direction.WEST;
    }
    return null;
  }
}

