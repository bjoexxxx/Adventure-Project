package com.company;

public class Room {
  private String name;
  private String description;
  private Room south;
  private Room north;
  private Room east;
  private Room west;
  private Room position;

  public Room(String name, String description, Room north, Room east, Room south, Room west){
    this.name = name;
    this.description = description;
    this.north = north;
    this.east = east;
    this.south = south;
    this.west = west;
  }
  public void setName(String name){
    this.name = name;
  }
  public String getName(){
    return this.name;
  }
  public void setNorth(Room room){
    this.position=room;
  }
  public void setEast(Room room){
    this.position=room;
  }
  public void setSouth(Room room){
    this.position=room;
  }
  public void setWest(Room room){
    this.position=room;
  }
  public void goEast(){
    this.position = this.east;
  }
  public void goNorth(){
    this.position = this.north;
  }
  public void goSouth(){
    this.position = this.south;
  }
  public void goWest(){
    this.position = this.west;
  }
}
