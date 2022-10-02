package org.example.game.characters;

import org.example.game.Battle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BattleWithWarlordTest {
    @Test
    @DisplayName("Smoke Test")
    void smokeTest() {

        var ronald = new Warlord();
        var heimdall = new Knight();

        assertFalse(Battle.fight(heimdall, ronald));

        var myArmy = new Army();
        myArmy.addUnits(Warlord::new, 1);
        myArmy.addUnits(Warrior::new, 2);
        myArmy.addUnits(Lancer::new, 2);
        myArmy.addUnits(Healer::new, 2);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warlord::new, 3);
        enemyArmy.addUnits(Vampire::new, 1);
        enemyArmy.addUnits(Healer::new, 2);
        enemyArmy.addUnits(Knight::new, 2);

        myArmy.moveUnits();
        enemyArmy.moveUnits();


        assertEquals(Lancer.class, myArmy.unitAtPosition(0).getClass());
        assertEquals(Healer.class, myArmy.unitAtPosition(1).getClass());

        assertEquals(Warlord.class, myArmy.unitAtPosition(-1).getClass());

        assertEquals(Vampire.class, enemyArmy.unitAtPosition(0).getClass());
        assertEquals(Warlord.class, enemyArmy.unitAtPosition(-1).getClass());
        assertEquals(Knight.class, enemyArmy.unitAtPosition(-2).getClass());


        assert enemyArmy.size() == 6;

        assertTrue(Battle.fight(myArmy, enemyArmy));
    }
}
