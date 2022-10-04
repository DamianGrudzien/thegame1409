package org.example.game.characters;

public interface HasAttack {
    int getAttack();

    default void hit(CanReceiveDamage defender) {
        defender.receiveDamage(this);
    }
}
