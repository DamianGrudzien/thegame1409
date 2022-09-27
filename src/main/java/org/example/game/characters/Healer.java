package org.example.game.characters;

public class Healer extends Warrior implements HasHealing{

    private static final int HEALING_POINT = 2;

    public Healer() {
        super(60, 0);
    }

    public void heal(Warrior woundedWarrior){
        woundedWarrior.setHealth(woundedWarrior.getHealth() + HEALING_POINT);
    }

    @Override
    public void hit(CanReceiveDamage defender) {
        // Healer is not attacking
    }
}
