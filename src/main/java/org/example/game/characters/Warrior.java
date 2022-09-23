package org.example.game.characters;


interface HasHealth{
    int getHealth();
    default boolean isAlive(){
        return getHealth() > 0;
    }
}

interface CanReceiveDamage extends HasHealth {
    void receiveDamage(HasAttack damager);
    Warrior getNextWarrior();
    void setNextWarrior(Warrior nextWarrior);
    boolean hasNextWarrior();
}
public class Warrior implements HasHealth, HasAttack, CanReceiveDamage{
    private final int attack;
    protected int health;
    protected int initialHealth;



    protected Warrior nextWarrior;

    public Warrior(){
        attack = 5;
        health = 50;
        initialHealth = health;
    }

    protected Warrior(int health, int attack) {
        initialHealth = this.health = health;
        this.attack = attack;
    }

    @Override
    public boolean isAlive() {
        return (this.health > 0);
    }

    public int getAttack() {
        return attack;
    }



    @Override
    public int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = Math.min(initialHealth, health);
    }


    public void receiveDamage(HasAttack damager) {
        setHealth(getHealth() - damager.getAttack());
    }

    public void hit(CanReceiveDamage defender) {
        defender.receiveDamage(this);
    }

    public Warrior getNextWarrior() {
        return nextWarrior;
    }
    public boolean hasNextWarrior() {
        return (nextWarrior!=null);
    }

    public void setNextWarrior(Warrior nextWarrior) {
        this.nextWarrior = nextWarrior;
    }

}
