package org.example.game.characters;

public class Knight extends Warrior {
    private static final int ATTACK = 7;

    @Override
    public int getAttack() {
        return ATTACK;
    }
}
