package com.company;

public class Door {

  private boolean isOpen;

  Door(boolean isOpen) {
    this.isOpen = isOpen;

  }

  public boolean isOpen() {
    return isOpen;
  }

  public void setOpen(boolean open) {
    isOpen = open;
  }
}
