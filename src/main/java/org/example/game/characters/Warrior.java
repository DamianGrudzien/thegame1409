package org.example.game.characters;


import org.example.game.weapons.WeaponI;

import java.util.ArrayList;
import java.util.List;

public class Warrior extends AbstractWarrior implements HasWeapon{

    List<WeaponI> weapons = new ArrayList<>();

    protected int attack;


    protected int initialAttack;
    protected int health;
    protected final int initialHealth;
    protected int maxHealth;

    public Warrior(){
        initialAttack = attack = 5;
        maxHealth = initialHealth = health = 50;
    }

    public Warrior(int health, int attack) {
        this.initialHealth = this.maxHealth = this.health = health;
        this.initialAttack = this.attack = attack;
    }

//    private WarriorWithWeapon warriorWithWeapon;


    @Override
    public void setAttack(int attack) {
        this.attack = Math.max(0,attack);
    }

//    public void equipWeapon(WeaponI weaponI) {
//       warriorWithWeapon = new WarriorWithWeapon(this, weaponI);
//       (this).setAttack(warriorWithWeapon.getAttack());
//       (this).setInitialHealth(warriorWithWeapon.getHealth());
//        switch (this.getClass().getSimpleName()) {
//            case "Defender" -> ((Defender) this).setDefense(warriorWithWeapon.getDefense());
//            case "Healer" -> ((Healer) this).setHealPower(warriorWithWeapon.getHealPower());
//            case "Vampire" -> ((Vampire) this).setVampirism(warriorWithWeapon.getVampirism());
//            default -> {}
//        }
//    }

    public int getInitialHealth() {
        return initialHealth;
    }

    public void equipWeapon(WeaponI weaponI){
        weapons.add(weaponI);
        int getBonusAttack = weapons.stream()
                                    .mapToInt(WeaponI::getAttack)
                                    .sum();
        int getBonusHealth = weapons.stream()
                                    .mapToInt(WeaponI::getHealth)
                                    .sum();
        int increasedHealth = getInitialHealth() + getBonusHealth;
        setMaxHealth(increasedHealth);
        setHealth(increasedHealth);
        setAttack(initialAttack + getBonusAttack);
    }

    private void setMaxHealth(int health) {
        maxHealth = Math.max(0,health);
    }

    @Override
    public boolean isAlive() {
        return (this.health > 0);
    }

    @Override
    protected void setHealth(int health) {
        this.health = Math.min(maxHealth, health);
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void receiveDamage(HasAttack damager) {
        setHealth(getHealth() - damager.getAttack());
    }

    @Override
    public void hit(CanReceiveDamage defender) {
        defender.receiveDamage(this);
    }
}
