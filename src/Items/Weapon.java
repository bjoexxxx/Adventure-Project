package Items;

public abstract class Weapon extends Item {
  private int remainingUses;
  private int damage;
  public Weapon(String name, String description, int damage, int remainingUses){
super(name,description);
this.damage = damage;
this.remainingUses = remainingUses;
  }

  public void itemUsed(Weapon equippedWeapon){
    equippedWeapon.remainingUses -= 1;
  }

  public void setRemainingUses(int remainingUses) {
    this.remainingUses = remainingUses;
  }

  public int getRemainingUses(){
    return remainingUses;
  }

  public int getDamage() {
    return damage;
  }
}
