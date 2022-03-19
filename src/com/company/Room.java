package com.company;

import java.util.ArrayList;

public class Room {
  private ArrayList<Item> lootTable;
  private final String name;
  private final String description;
  private final String shortDescription;
  private boolean isVisted;
  private boolean triedNorth;
  private boolean triedEast;
  private boolean triedSouth;
  private boolean triedWest;
  private Room south;
  private Room north;
  private Room east;
  private Room west;
  private Door door;


  public Room(String name, String description, String shortDescription) {
    this.name = name;
    this.description = description;
    this.north = null;
    this.east = null;
    this.south = null;
    this.west = null;
    this.isVisted = false;
    this.triedNorth = false;
    this.triedEast = false;
    this.triedSouth = false;
    this.triedWest = false;
    this.shortDescription = shortDescription;
    this.door = null;
    this.lootTable = new ArrayList<>();

  }

  public Door getDoor() {
    return door;
  }

  public void setDoor(Door door) {
    this.door = door;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public boolean getIsVisited() {
    return this.isVisted;
  }

  public void setIsVisited(boolean visited) {
    this.isVisted = visited;
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

  public void setLootTable(Item loot){
    this.lootTable.add(loot);
  }

  public ArrayList<Item> getLootTable(){
    return this.lootTable;
  }

  public void removeItem(Item loot){
    this.lootTable.remove(loot);
  }
}
