package org.example.game.characters;

public class Archer extends Warrior{

    final int rangeAttack = 2;

    public Archer() {
        super(30, 4);
    }

    public int getRangeAttack() {
        return rangeAttack;
    }

    @Override
    public void hit(CanReceiveDamage defender) {
        super.hit(defender);
    }
}
