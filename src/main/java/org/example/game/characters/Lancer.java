package org.example.game.characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lancer extends Warrior implements KnowsDamageDealt {

    private static final int PENETRATION = 50;
    Logger log = LoggerFactory.getLogger(Lancer.class);
    private static final int RANGE_OF_LANCE = 2;

    public Lancer() {
        super(50, 6);
    }

    @Override
    public void hit(CanReceiveDamage defender) {
        int damageDealt = hitAndReportDamage(defender);
        if (defender instanceof WarriorInArmy unitInArmy) {
            for (int i = 1; i < RANGE_OF_LANCE; i++) {
                hitWarriorBehind(damageDealt, unitInArmy);

                if (unitInArmy.getNextBehind() != null) {
                    unitInArmy = (WarriorInArmy) unitInArmy.getNextBehind();
                } else {
                    break;
                }
            }
        }
    }

    private void hitWarriorBehind(int damageDealt, WarriorInArmy unitInArmy) {
        var theSecondWarrior = unitInArmy.getNextBehind();
        if (theSecondWarrior != null) {
            int damageToSecond = ((damageDealt * PENETRATION) / 100);
            theSecondWarrior.receiveDamage(() -> damageToSecond);
        }
    }
}
