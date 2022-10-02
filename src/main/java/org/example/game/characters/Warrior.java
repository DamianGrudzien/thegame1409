package org.example.game.characters;


import org.example.game.weapons.WeaponI;

public class Warrior extends AbstractWarrior implements HasWeapon{

    protected int attack;
    protected int health;
    protected int initialHealth;

    public Warrior(){
        attack = 5;
        health = 50;
        initialHealth = health;
    }

    public Warrior(int health, int attack) {
        initialHealth = this.health = health;
        this.attack = attack;
    }

    private WarriorWithWeapon warriorWithWeapon;


    private void setInitialHealth(int health) {
         initialHealth = this.health = health;
    }

    @Override
    public void setAttack(int attack) {
        this.attack = Math.max(0,attack);
    }

    public void equipWeapon(WeaponI weaponI) {
       warriorWithWeapon = new WarriorWithWeapon(this, weaponI);
       (this).setAttack(warriorWithWeapon.getAttack());
       (this).setInitialHealth(warriorWithWeapon.getHealth());
        switch (this.getClass().getSimpleName()) {
            case "Defender" -> ((Defender) this).setDefense(warriorWithWeapon.getDefense());
            case "Healer" -> ((Healer) this).setHealPower(warriorWithWeapon.getHealPower());
            case "Vampire" -> ((Vampire) this).setVampirism(warriorWithWeapon.getVampirism());
            default -> {}
        }
    }

    @Override
    public boolean isAlive() {
        return (this.health > 0);
    }

    @Override
    protected void setHealth(int health) {
        this.health = Math.min(initialHealth, health);
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
