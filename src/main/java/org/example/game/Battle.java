package org.example.game;

import org.example.game.characters.Army;
import org.example.game.characters.Warrior;

public class Battle {

    private Battle() {
    }

    public static boolean fight(Warrior warrior1, Warrior warrior2) {

        do {
            warrior1.hit(warrior2);
            if(!warrior2.isAlive()){
                break;
            }
            warrior2.hit(warrior1);

        } while (warrior1.isAlive() && warrior2.isAlive());

        return warrior1.isAlive();
    }


    public static boolean fight(Army army1, Army army2) {
//        Warrior attacker = army1.getWarrior();
//        Warrior defender = army2.getWarrior();
//
//        while (true) {
//            boolean resultOfFight = fight(attacker, defender);
//            if (resultOfFight) {
//                army2.armyLooseWarrior();
//                if (army2.hasUnits()) {
//                    defender = army2.getWarrior();
//                } else {
//                    break;
//                }
//            } else {
//                army1.armyLooseWarrior();
//                if (army1.hasUnits()) {
//                    attacker = army1.getWarrior();
//                }
//                else {
//                    break;
//                }
//            }
//        }

        var it1 = army1.firstAlive();
        var it2 = army2.firstAlive();

        while(it1.hasNext() && it2.hasNext()){
            fight(it1.next(), it2.next());
        }

        return it1.hasNext();
    }

    private static void checkArmies(Army army1, Army army2) {
//        if (army1 == null || army2 == null) {
//            throw new IllegalArgumentException("Army can not be null");
//        }
//        if (!army1.hasUnits()) {
//            throw new IllegalArgumentException("Arm1 has no units!");
//        } else if (!army2.hasUnits()) {
//            throw new IllegalArgumentException("Arm2 has no units!");
//        }


    }
}
