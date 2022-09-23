package org.example.game;

import org.example.game.characters.HasAttack;
import org.example.game.characters.Warrior;

public class FactorDecreaseAttack implements HasAttack {

    private Warrior lancer;

    private final double decreaseHit;

    public FactorDecreaseAttack(Warrior lancer, double decreaseHit) {
        this.lancer = lancer;
        this.decreaseHit = decreaseHit;
    }

    public int getAttack() {
        return (int) (lancer.getAttack() * decreaseHit);
    }
}
