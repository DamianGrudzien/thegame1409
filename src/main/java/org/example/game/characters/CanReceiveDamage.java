package org.example.game.characters;

interface CanReceiveDamage extends HasHealth {
    void receiveDamage(HasAttack damager);
}
