package org.example.game.characters;

public class Vampire extends Warrior implements KnowsDamageDealt{
    private static final int VAMPIRISM = 50;


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
        setHealth(getHealth() +  (damageDealt * VAMPIRISM) / percents);
    }
}
