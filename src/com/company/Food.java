package com.company;

  public class Food extends Item {
    private Consume consume;
    private int nutrition;


    public Food(String name, String description,Consume consume, int nutrition) {
      super(name, description);
      this.consume = consume;
      this.nutrition = nutrition;
    }
    public Consume getConsume(){
      return this.consume;
    }

    public int getNutrition (){
      return this.nutrition;
    }
  }
