package com.company;

public class Room {
  private String name;
  private String description;
  private Room south;
  private Room north;
  private Room east;
  private Room west;
  private Room playerPosition;

  public Room(String name, String description){
    this.name = name;
    this.description = description;
    this.north = null;
    this.east = null;
    this.south = null;
    this.west = null;
  }
  public void setName(String name){
    this.name = name;
  }
  public String getName(){
    return this.name;
  }
  public void setNorth(Room room){
    this.north=room;
  }
  public void setEast(Room room){
    this.east=room;
  }
  public void setSouth(Room room){
    this.south=room;
  }
  public void setWest(Room room){
    this.west=room;
  }
  public Room goEast(){
    return this.east;
  }
  public Room goNorth(){
    return this.north;
  }
  public Room goSouth(){
    return this.south;
  }
  public Room goWest(){
    return this.west;
  }
}
