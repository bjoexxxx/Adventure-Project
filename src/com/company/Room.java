package com.company;

public class Room {
  private final String name;
  private final String description;
  private final String shortDescription;
  private boolean isVisted;
  private Room south;
  private Room north;
  private Room east;
  private Room west;

  public Room(String name, String description, String shortDescription){
    this.name = name;
    this.description = description;
    this.north = null;
    this.east = null;
    this.south = null;
    this.west = null;
    this.isVisted = false;
    this.shortDescription = shortDescription;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public boolean getIsVisited () {
    return this.isVisted;
  }

  public void setIsVisited(boolean visited) {
    this.isVisted = visited;
  }

  public String getName(){
    return this.name;
  }

  public String getDescription() {
    return description;
  }

  public void setNorth(Room room){
    this.north=room;
    if (room.getSouth() == null){
    room.setSouth(this);}
  }
  public void setEast(Room room){
    this.east=room;
    if (room.getWest() == null){
    room.setWest(this);}
  }
  public void setSouth(Room room){
    this.south=room;
      if (room.getNorth() == null){
    room.setNorth(this);}

  }
  public void setWest(Room room){
    this.west=room;
        if (room.getEast() == null){
    room.setEast(this);}
  }
  public Room getEast(){
    return this.east;
  }
  public Room getNorth(){
    return this.north;
  }
  public Room getSouth(){
    return this.south;
  }
  public Room getWest(){
    return this.west;
  }
}
