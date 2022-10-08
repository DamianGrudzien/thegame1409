package org.example.game.characters;

import org.example.game.Battle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleStraightFightTest {
    @Test
    @DisplayName("Simple Test")
    void simpleTest() {
        var myArmy = new Army().addUnits(Warrior::new, 2).addUnits(Healer::new, 2);
        var enemyArmy = new Army().addUnits(Warrior::new, 3).addUnits(Healer::new, 2);

        assertFalse(Battle.straightFight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Second simple Test")
    void secondSimpleTest() {
        var myArmy = new Army().addUnits(Warrior::new, 2).addUnits(Knight::new, 1);
        var enemyArmy = new Army().addUnits(Knight::new, 1).addUnits(Healer::new, 1).addUnits(Knight::new, 1);

        assertTrue(Battle.straightFight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Only Healers in Army")
    void onlyHealersInArmy() {
        // Given
        var army1 = new Army(Healer::new, 2);
        var army2 = new Army(Healer::new, 2);

        // When
        var res = Battle.straightFight(army1, army2);

        // Then
        assertTrue(res);

    }

}
