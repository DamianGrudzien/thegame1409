package org.example.game.characters;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Warlord extends Defender {

    public Warlord() {
        super(100, 4, 2);
    }


    Iterator<Warrior> moveUnits(Iterator<Warrior> iterator) {
        List<Lancer> lancers = new LinkedList<>();
        List<Healer> healers = new LinkedList<>();
        List<Warrior> warriors = new LinkedList<>();

        LinkedList<Warrior> newWarriorsLine = new LinkedList<>();

        while (iterator.hasNext()) {
            Warrior nextWarrior = iterator.next();
            if (nextWarrior instanceof Lancer lancer) {
                lancers.add(lancer);
            } else if(nextWarrior instanceof Healer healer){
                healers.add(healer);
            } else if(!(nextWarrior instanceof Warlord)){
                warriors.add(nextWarrior);
            }
        }
        newWarriorsLine.addAll(lancers);
        newWarriorsLine.addAll(Math.min(newWarriorsLine.size(),1), healers);
        newWarriorsLine.addAll(warriors);
        newWarriorsLine.add(this);

        return newWarriorsLine.iterator();


        // Different Approach with Streams
//        List<Warrior> list = new ArrayList<>();
//        while (iterator.hasNext()) {
//            list.add(iterator.next());
//        }
//
//        var lancers = list.stream()
//                          .filter(Lancer.class::isInstance)
//                          .toList();
//        var healers = list.stream()
//                          .filter(Healer.class::isInstance)
//                          .toList();
//        var restWarriors = list.stream()
//                               .filter(not(Lancer.class::isInstance))
//                               .filter(not(Healer.class::isInstance))
//                               .filter(not(Warlord.class::isInstance))
//                               .toList();
//        List<Warrior> out = new ArrayList<>();
//        out.addAll(lancers);
//        out.addAll(restWarriors);
//        out.addAll(Math.min(out.size(),1),healers);
//        out.add(this);
//        return out.iterator();

    }
}
