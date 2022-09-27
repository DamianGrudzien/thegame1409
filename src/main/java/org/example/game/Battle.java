package org.example.game;

import org.example.game.characters.Army;
import org.example.game.characters.Warrior;

import java.util.Iterator;

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

    public static boolean straightFight(Army army1, Army army2){
        Iterator<Warrior> it1 = army1.iterator();
        Iterator<Warrior> it2 = army2.iterator();

        do {
            while (it1.hasNext() && it2.hasNext()) {
                fight(it1.next(), it2.next());
            }

            army1.removeDeadWarriors();
            army2.removeDeadWarriors();

        } while (it1.hasNext() && it2.hasNext());

        if(!army1.firstAlive().hasNext()){
            return false;
        }

        fight(army1, army2);
        return it1.hasNext();
    }



    public static boolean fight(Army army1, Army army2) {
        var it1 = army1.firstAlive();
        var it2 = army2.firstAlive();

        while(it1.hasNext() && it2.hasNext()){
            fight(it1.next(), it2.next());
        }

        return it1.hasNext();
    }
}
