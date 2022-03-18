package com.company;

public class Item {
  private String name;
  private String description;
  public Item(String name, String description){
    this.name = name;
    this.description = description;
  }
  public void setName(String name){
    this.name = name;
  }
  public String getName(){
    return this.name;
  }

  @Override
  public String toString() {
    return name+": "+description+ "  ";
  }

  public void setDescription(String description){
    this.description = description;
  }
  public String getDescription(){
    return this.description;
  }
}
