package com.company;

public class Room {
  private String name;
  private Room south;
  private Room north;
  private Room east;
  private Room west;

  public Room(String name, Room south, Room north, Room east, Room west){
    this.name = name;
    this.east = east;
    this.south = south;
    this.north = north;
    this.west = west;
  }
  public void setName(String name){
    this.name = name;
  }
  public String getName(){
    return this.name;
  }
  public void goEast(){

  }
}
