package org.example.game.characters;

import org.example.game.Battle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BattleStraightFightTest {
    @Test
    @DisplayName("Test name")
    void testName() {
        var myArmy = new Army().addUnits(Lancer::new, 3);
        var enemyArmy = new Army().addUnits(Warrior::new, 3);

        assertTrue(Battle.straightFight(myArmy, enemyArmy));
    }
}
