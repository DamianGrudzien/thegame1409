package org.example.game.characters;

//interface HasWarriorBehind {
//    Warrior getNextWarrior();
//    void setNextWarrior(Warrior nextWarrior);
//    boolean hasWarriorBehind();
//}

public class Warrior implements HasHealth, CanReceiveDamage, HasAttack{
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

    @Override
    public void hit(CanReceiveDamage defender) {
        defender.receiveDamage(this);
    }

//    public Warrior getNextWarrior() {
//        return nextWarrior;
//    }
//    public boolean hasWarriorBehind() {
//        return (nextWarrior!=null);
//    }
//
//    public void setNextWarrior(Warrior nextWarrior) {
//        this.nextWarrior = nextWarrior;
//    }

}
