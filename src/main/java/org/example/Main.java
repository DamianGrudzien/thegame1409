package org.example;

import org.example.game.Battle;
import org.example.game.characters.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Main {
    public static void main(String[] args) {

        Army simple = new Army(Lancer::new,1).addUnits(Archer::new,3);
        Army simple2 = new Army(Defender::new,4);
        boolean val = Battle.fight(simple, simple2);
    }
}

