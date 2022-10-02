package org.example.game.characters;




public abstract class AbstractWarrior implements HasHealth, CanReceiveDamage, HasAttack{


    private int attack;
    protected int health;
    protected int initialHealth;


    protected AbstractWarrior(){
        attack = 5;
        health = 50;
        initialHealth = health;
    }

    protected AbstractWarrior(int health, int attack) {
        initialHealth = this.health = health;
        this.attack = attack;
    }

    @Override
    public boolean isAlive() {
        return (this.health > 0);
    }


    protected void setHealth(int health) {
        this.health = Math.min(initialHealth, health);
    }

    public void setAttack(int attack) {
        this.attack = Math.max(0,attack);
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
