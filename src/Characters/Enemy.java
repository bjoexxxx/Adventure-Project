package Characters;

import Items.Item;
import Items.Weapon;
import com.company.Room;

import java.util.ArrayList;

public class Enemy extends Character {
  private String name;

  public Enemy(Room currentRoom, ArrayList<Item> inventory, int health, String name, Weapon rightHand) {
    super(currentRoom, inventory, health);
    this.name = name;
    this.rightHand = rightHand;
  }

  public String toString() {
    return this.name;
  }

  public void isAttacked(Weapon playerWeapon, Player youThePlayer) {
    tookDamage(playerWeapon);
    Weapon monsterWeapon = rightHand;

    if (ischarecterAlive()) {
      currentRoom.removeMonster(this);
      currentRoom.setLootTable(monsterWeapon);
      currentRoom.addMonsterInventoryToLoottable(inventory);
    } else {
      youThePlayer.tookDamage(monsterWeapon);
    }
  }

  public void attackTarget(Enemy nameOfTarget) {
  }

  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }

  public String getName() {
    return name;
  }
}
