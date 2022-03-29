package Characters;

import Items.Item;
import Items.Weapon;
import com.company.Room;

import java.util.ArrayList;

public class Enemy extends Character{
  private String name;
  public Enemy(Room currentRoom, ArrayList<Item> inventory, int health, String name, Weapon rightHand){
    super(currentRoom,inventory,health);
    this.name = name;
    this.rightHand = rightHand;
  }
  public String toString(){
    return this.name;
  }
  public void isAttacked(int damage){
    this.health -= damage;
  }
  public void attackTarget(Enemy nameOfTarget) {
  }
  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }
  public Room getCurrentRoom(){
    return currentRoom;
  }

  public String getName() {
    return name;
  }
}
