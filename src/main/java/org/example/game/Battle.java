package org.example.game;

import org.example.game.characters.Army;
import org.example.game.characters.Healer;
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

        while (it1.hasNext() && it2.hasNext()) {
            Warrior warriorArmy1 = it1.next();
            Warrior warriorArmy2 = it2.next();
            if(!(warriorArmy1 instanceof Healer) && !(warriorArmy2 instanceof Healer)) {
                fight(warriorArmy1, warriorArmy2);
            }
        }


        army1.removeDeadWarriors();
        army2.removeDeadWarriors();

        it1 = army1.iterator();
        it2 = army2.iterator();

        if(!it1.hasNext()){
            return false;
        }
        else if(!it2.hasNext()){
            return true;
        }
        else {
            fight(army1, army2);
        }

        return army1.firstAlive().hasNext();
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
