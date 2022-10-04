package org.example.game.characters;

import org.example.game.Battle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BattleWithArcherTest {

    @Test
    @DisplayName("Smoke Test")
    void smokeTest() {
        var chuck = new Warrior();
        var bruce = new Archer();
        var carl = new Knight();
        var dave = new Archer();
        var mark = new Archer();
        var bob = new Rookie();

        assertTrue(Battle.fight(chuck, bruce));
        assertTrue(Battle.fight(carl, dave));
        assertTrue(Battle.fight(mark,bob));

    }
}
