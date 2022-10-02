package org.example.game.characters;


import org.example.game.weapons.WeaponI;

public class Warrior extends AbstractWarrior implements HasWeapon{
    WarriorWithWeapon warriorWithWeapon;
    public Warrior() {
        super();
    }

    public Warrior(int health, int attack) {
        super(health, attack);
    }

    private void setInitialHealth(int health) {
         initialHealth = this.health = health;
    }

    public void equipWeapon(WeaponI weaponI) {
       warriorWithWeapon = new WarriorWithWeapon(this, weaponI);
       setAttack(warriorWithWeapon.getAttack());
       setInitialHealth(warriorWithWeapon.getHealth());
        switch (this.getClass().getSimpleName()) {
            case "Defender" -> ((Defender) this).setDefense(warriorWithWeapon.getDefense());
            case "Healer" -> ((Healer) this).setHealingPower(warriorWithWeapon.getHealingPower());
            case "Vampire" -> ((Vampire) this).setVampirism(warriorWithWeapon.getVampirism());
            default -> {}
        }
    }
}
