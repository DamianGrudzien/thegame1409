package org.example.game.characters;

import org.example.game.FactorDecreaseAttack;

public class Lancer extends Warrior{

    public Lancer() {
        super(50, 6);
    }

    @Override
    public void hit(CanReceiveDamage defender) {
        int healthBefore = defender.getHealth();
        defender.receiveDamage(this);

        if(defender.hasNextWarrior()) {
            double decreaseAttack = 0.5;
            int healthAfter = defender.getHealth();
            int diffHealth = healthBefore - healthAfter;

            Warrior nextDefender = defender.getNextWarrior();
            nextDefender.receiveDamage(() -> (int) (diffHealth * decreaseAttack));
        }
    }
}
