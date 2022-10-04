package org.example.game.characters;

public class Lancer extends Warrior implements KnowsDamageDealt{

    private static final int PENETRATION = 50;
    public Lancer() {
        super(50, 6);
    }

    @Override
    public void hit(CanReceiveDamage defender) {
        int damageDealt = hitAndReportDamage(defender);
        if(defender instanceof WarriorInArmy unitInArmy) {
            var theSecond = unitInArmy.getNextBehind();
            if (theSecond != null) {
                int damageToSecond = ((damageDealt * PENETRATION) / 100);
                theSecond.receiveDamage(() -> damageToSecond);
            }
        }
    }

    // Without Decorator implementation
//    @Override
//    public void hit(CanReceiveDamage defender) {
//        int healthBefore = defender.getHealth();
//        defender.receiveDamage(this);
//
//        if(defender.hasWarriorBehind()) {
//            double decreaseAttack = 0.5;
//            int healthAfter = defender.getHealth();
//            int diffHealth = healthBefore - healthAfter;
//
//            Warrior nextDefender = defender.getNextWarrior();
//
//            nextDefender.receiveDamage(() -> (int) (diffHealth * decreaseAttack));
//        }
//    }
}
