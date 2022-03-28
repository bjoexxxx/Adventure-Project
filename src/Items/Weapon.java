package Items;

public abstract class Weapon extends Item {
  private int remainingUses;
  private int damage;
  public Weapon(String name, String description, int damage, int remainingUses){
super(name,description);
this.damage = damage;
this.remainingUses = remainingUses;
  }

  public void itemUsed(){
    this.remainingUses -= 1;
  }

  public int getRemainingUses(){
    return remainingUses;
  }

}
