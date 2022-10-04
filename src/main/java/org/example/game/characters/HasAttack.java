package org.example.game.characters;

interface HasAttack {
    int getAttack();

    default void hit(CanReceiveDamage defender) {
        defender.receiveDamage(this);
    }
}
