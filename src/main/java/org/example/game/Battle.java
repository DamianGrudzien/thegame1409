package org.example.game;

import org.example.game.characters.Army;
import org.example.game.characters.Warrior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Battle {

    private static final Logger log = LoggerFactory.getLogger(Battle.class);

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

        log.debug("First Warrior is alive?: {}",warrior1.isAlive());
        return warrior1.isAlive();
    }

    public static boolean straightFight(Army army1, Army army2){
        while(true){
            var it1 = army1.iterator();
            var it2 = army2.iterator();

            if (!it1.hasNext()) {
                return false;
            }
            if (!it2.hasNext()) {
                return true;
            }

            while (it1.hasNext() && it2.hasNext()) {
                fight(it1.next(), it2.next());
            }

            log.debug("Removing Dead warriors in armies");
            army1.removeDeadWarriors();
            army2.removeDeadWarriors();
        }
    }



    public static boolean fight(Army army1, Army army2) {
        var it1 = army1.firstAlive();
        var it2 = army2.firstAlive();

        while(it1.hasNext() && it2.hasNext()){
            fight(it1.next(), it2.next());
        }
        log.debug("First army win?: {}", it1.hasNext());
        return it1.hasNext();
    }
}
