package com.company;

public class Door {

  private String key;
  private boolean isOpen;

  Door(boolean isOpen, String key) {
    this.isOpen = isOpen;
    this.key = key;

  }

  public boolean isOpen() {
    return isOpen;
  }

  public void setOpen(boolean open) {
    isOpen = open;
  }

  public String getKey (){
    return this.key;
  }


}
