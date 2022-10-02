package org.example.game;

import org.example.game.characters.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BattleArmiesTest {

    private static Stream<Arguments> checkBattleBetweenArmiesSource() {
        return Stream.of(
                Arguments.of(new Army(Warrior::new, 1), new Army(Warrior::new, 2), false),
                Arguments.of(new Army(Warrior::new, 2), new Army(Warrior::new, 3), false),
                Arguments.of(new Army(Warrior::new, 5), new Army(Warrior::new, 7), false),
                Arguments.of(new Army(Warrior::new, 20), new Army(Warrior::new, 21), true),
                Arguments.of(new Army(Warrior::new, 10), new Army(Warrior::new, 11), true),
                Arguments.of(new Army(Warrior::new, 11), new Army(Warrior::new, 7), true),
                Arguments.of(new Army(Warrior::new, 5)
                                .addUnits(Defender::new, 4)
                                .addUnits(Defender::new, 5),
                        new Army(Warrior::new, 4), true),
                // 8. Battle
                Arguments.of(new Army(Defender::new, 5)
                                .addUnits(Warrior::new, 20)
                                .addUnits(Defender::new, 4),
                        new Army(Warrior::new, 21), true),
                // 9. Battle
                Arguments.of(new Army(Warrior::new, 10)
                                .addUnits(Defender::new, 5)
                                .addUnits(Defender::new, 10),
                        new Army(Warrior::new, 5), true),
                // 10. Battle
                Arguments.of(new Army(Defender::new, 2)
                                .addUnits(Warrior::new, 1)
                                .addUnits(Defender::new, 1),
                        new Army(Warrior::new, 5), false),
                // 11. Battle
                Arguments.of(new Army(Defender::new, 5)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Warrior::new, 7),
                        new Army(Warrior::new, 6)
                                .addUnits(Defender::new, 6)
                                .addUnits(Vampire::new, 6), false),
                // 12. Battle
                Arguments.of(new Army(Defender::new, 2)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4),
                        new Army(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 3), false),
                // 13. Battle
                Arguments.of(new Army(Defender::new, 11)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4),
                        new Army(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 13), true),
                // 14. Battle
                Arguments.of(new Army(Defender::new, 9)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 8),
                        new Army(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 13), true),
                // 15. Battle
                Arguments.of(new Army(Lancer::new, 5)
                                .addUnits(Vampire::new, 4)
                                .addUnits(Warrior::new, 5)
                                .addUnits(Warrior::new, 2),
                        new Army(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 5), false),
                // 16. Battle
                Arguments.of(new Army(Lancer::new, 7)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 2),
                        new Army(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4), true),
                // 17. Battle
                Arguments.of(new Army(Warrior::new, 2),
                        new Army(Lancer::new, 1)
                                .addUnits(Warrior::new, 1), false),
                // 19. Battle
                Arguments.of(new Army(Lancer::new, 5)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 2),
                        new Army(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4), false),

                Arguments.of(new Army(Lancer::new, 7)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 2),
                        new Army(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4), true),
                Arguments.of(new Army(Lancer::new, 4)
                                .addUnits(Defender::new, 2),
                        new Army(Lancer::new, 4)
                                .addUnits(Defender::new, 2), true)
        );
    }

    private static Stream<Arguments> checkBattleBetweenArmiesStraightFightSource() {
        return Stream.of(
                // 19.
                Arguments.of(new Army(Lancer::new,5)
                                .addUnits(Vampire::new,3)
                                .addUnits(Warrior::new,4)
                                .addUnits(Defender::new,2),
                        new Army(Warrior::new, 4)
                                .addUnits(Defender::new,4)
                                .addUnits(Vampire::new,6)
                                .addUnits(Lancer::new, 5)
                        , false),
                //20.
                Arguments.of(new Army(Lancer::new,7)
                                .addUnits(Vampire::new,3)
                                .addUnits(Warrior::new,4)
                                .addUnits(Defender::new,2),
                        new Army(Warrior::new, 4)
                                .addUnits(Defender::new,4)
                                .addUnits(Vampire::new,6)
                                .addUnits(Lancer::new, 4)
                        , true),
                //21.
                Arguments.of(new Army(Lancer::new,7)
                        .addUnits(Vampire::new,3)
                        .addUnits(Healer::new,1)
                        .addUnits(Warrior::new,4)
                        .addUnits(Healer::new,1)
                        .addUnits(Defender::new,2),
                        new Army(Warrior::new, 4)
                                .addUnits(Defender::new,4)
                                .addUnits(Healer::new,1)
                                .addUnits(Vampire::new,6)
                                .addUnits(Lancer::new, 4)
                        , false),
                //22.
                Arguments.of(new Army(Lancer::new,4)
                                .addUnits(Warrior::new,3)
                                .addUnits(Healer::new,1)
                                .addUnits(Warrior::new,4)
                                .addUnits(Healer::new,1)
                                .addUnits(Knight::new,2),
                        new Army(Warrior::new, 4)
                                .addUnits(Defender::new,4)
                                .addUnits(Healer::new,1)
                                .addUnits(Vampire::new,2)
                                .addUnits(Lancer::new, 4)
                        , true)
        );
    }

    @Test
    @DisplayName("Smoke test")
    void smokeTest() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();

        assertTrue(Battle.fight(chuck, bruce));
        assertFalse(Battle.fight(dave, carl));
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
        assertFalse(dave.isAlive());
        assertFalse(Battle.fight(carl, mark));
        assertFalse(carl.isAlive());

        var myArmy = new Army().addUnits(Knight::new, 3);


        var enemyArmy = new Army().addUnits(Warrior::new, 3);

        var army3 = new Army()
                .addUnits(Warrior::new, 20)
                .addUnits(Knight::new, 5);

        var army4 = new Army().addUnits(Warrior::new, 30);

        assertTrue(Battle.fight(myArmy, enemyArmy));
        assertFalse(Battle.fight(army3, army4));
    }


    @ParameterizedTest
    @MethodSource("checkBattleBetweenArmiesStraightFightSource")
    void checkBattleBetweenArmiesStraightFight(Army army1, Army army2, boolean expected) {
        // When
        var res = Battle.straightFight(army1, army2);

        // Then
        assertEquals(res, expected);
    }

    @ParameterizedTest
    @MethodSource("checkBattleBetweenArmiesSource")
    void checkBattleBetweenArmies(Army army1, Army army2, boolean expected) {
        // When
        var res = Battle.fight(army1, army2);

        // Then
        assertEquals(res, expected);
    }

    @Test
    @DisplayName("Battle 23.")
    void battle23() {
        Army myArmy=  new Army(Warlord::new,1)
                        .addUnits(Warrior::new,2)
                        .addUnits(Lancer::new,2)
                        .addUnits(Healer::new,2);

        Army enemyArmy = new Army(Warlord::new, 1)
                        .addUnits(Vampire::new,1)
                        .addUnits(Healer::new,2)
                        .addUnits(Knight::new, 2);

        myArmy.moveUnits();
        enemyArmy.moveUnits();

        assertTrue(Battle.fight(myArmy, enemyArmy));
    }

}