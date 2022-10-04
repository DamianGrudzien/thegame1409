package org.example.game.characters;

public class Defender extends Warrior implements HasDefense{
    protected int defense = 2;

    public Defender(){
        super(60,3);
    }

    protected Defender(int health, int attack, int defense){
        super(health, attack);
        this.defense = defense;
    }

    public int getDefense(){
        return defense;
    }
    public void setDefense(int defense){
        this.defense = defense;
    }

    @Override
    public void receiveDamage(HasAttack damager){
        int reducedDamage = Math.max(0, damager.getAttack() - getDefense());
        super.receiveDamage(() -> reducedDamage);
    }
}
