package org.example.game;

import org.example.game.characters.Warrior;

import java.util.Objects;

public class SimpleDamage implements Damage{
    private int value;
    Warrior damageDealer;

    public SimpleDamage(int value, Warrior damageDealer) {
        this.value = value;
        this.damageDealer = Objects.requireNonNull(damageDealer);
    }

    @Override
    public Warrior getDamageDealer() {
        return damageDealer;
    }

    @Override
    public int getValue() {
        return 0;
    }
}
