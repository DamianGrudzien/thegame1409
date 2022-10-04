package org.example.game.characters;

import java.util.Iterator;
import java.util.LinkedList;

public class Warlord extends Defender{

    // Get units from army then remove all units, then fill it with add units
    public Warlord() {
        super(100,4,2);
    }


    Iterator<Warrior> rearrangeArmy(Iterator<Warrior> iterator) {


        LinkedList<Lancer> lancers = new LinkedList<>();
        LinkedList<Healer> healers = new LinkedList<>();
        LinkedList<Warrior> warriors = new LinkedList<>();
        Warlord warlordPrimary = null;

        LinkedList<Warrior> newWarriors = new LinkedList<>();

        while (iterator.hasNext()) {
            Warrior nextWarrior = iterator.next();
            if (nextWarrior instanceof Lancer lancer) {
                lancers.add(lancer);
            } else if(nextWarrior instanceof Healer healer){
                healers.add(healer);
            } else if(nextWarrior instanceof Warlord warlord){
                warlordPrimary = warlord;
            } else {
                warriors.add(nextWarrior);
            }
        }
        if (!lancers.isEmpty()) {
            newWarriors.addAll(lancers);
        }
        newWarriors.addAll(warriors);
        if (!newWarriors.isEmpty()) {
            newWarriors.addAll(1, healers);
        } else {
            newWarriors.addAll(healers);
        }
        newWarriors.add(warlordPrimary);

        return newWarriors.iterator();
    }
}
