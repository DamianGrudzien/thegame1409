package org.example.game.characters;

public class Vampire extends Warrior implements KnowsDamageDealt, HasVampirism{

    private int vampirism = 50;


    public Vampire() {
        super(40, 4);
    }


    @Override
    public void receiveDamage(HasAttack damager) {
        setHealth(getHealth() - damager.getAttack());
    }

    @Override
    public void hit(CanReceiveDamage defender) {
        int damageDealt = hitAndReportDamage(defender);
        final int percents = 100;
        setHealth(getHealth() +  (damageDealt * getVampirism()) / percents);
    }

    @Override
    public int getVampirism() {
        return vampirism;
    }


    public void setVampirism(int vampirism) {
        this.vampirism = vampirism;
    }
}
