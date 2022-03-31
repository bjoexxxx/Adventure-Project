package Characters;

import Items.Item;
import Items.Weapon;
import com.company.Room;

import java.util.ArrayList;

public abstract class Character {
  protected Room currentRoom;
  protected ArrayList<Item> inventory;
  protected int health;
  protected Weapon rightHand;

  public Character(Room currentRoom,ArrayList<Item> inventory, int health) {
    this.inventory = inventory;
    this.health = health;
    this.rightHand = null;
    this.currentRoom = currentRoom;
  }


   public abstract void setCurrentRoom(Room currentRoom);

  public int getHealth() {
    return this.health;
  }

  public boolean ischarecterAlive() {
    return health <= 0;
  }

  public void tookDamage(int damageTaken) {
    this.health -= damageTaken;
  }

}
