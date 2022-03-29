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
  public Character(Room currentRoom, ArrayList<Item> inventory, int health){
    this.currentRoom = currentRoom;
    this.inventory = inventory;
    this.health = health;
    this.rightHand = null;
  }
  public abstract void attackTarget(Enemy nameOfTarget);

  public abstract void setCurrentRoom(Room currentRoom);

  public int getHealth(){
    return this.health;
  }
}