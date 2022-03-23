package com.company;

  public class Food extends Item {
    private Consume consume;


    public Food(String name, String description,Consume consume) {
      super(name, description);
      this.consume = consume;
    }
    public Consume getConsume(){
      return this.consume;
    }
  }
