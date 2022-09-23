package org.example.game.characters;

public class Vampire extends Warrior{
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
        int healthBefore = defender.getHealth();
        super.hit(defender);
        int healthAfter = defender.getHealth();
        int healthDiff = healthBefore - healthAfter;


        final int percents = 100;
        setHealth(getHealth() +  (healthDiff * VAMPIRISM) / percents);
    }

}
