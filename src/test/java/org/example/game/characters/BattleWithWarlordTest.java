package org.example.game.characters;

import org.example.game.Battle;
import org.example.game.weapons.WeaponI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BattleWithWarlordTest {


    private static Stream<Arguments> checkBattleBetweenArmiesWithWarlordSource() {
        return Stream.of(
                Arguments.of(new Army(Warlord::new, 1)
                                .addUnits(Warrior::new, 2)
                                .addUnits(Lancer::new, 2)
                                .addUnits(Healer::new, 2),
                        new Army(Warlord::new, 1)
                                .addUnits(Vampire::new, 1)
                                .addUnits(Healer::new, 2)
                                .addUnits(Knight::new, 2)
                        , true),
                Arguments.of(new Army(Warrior::new, 2)
                                .addUnits(Lancer::new, 2)
                                .addUnits(Defender::new, 1)
                                .addUnits(Warlord::new, 3),
                        new Army(Warlord::new, 2)
                                .addUnits(Vampire::new, 1)
                                .addUnits(Healer::new, 5)
                                .addUnits(Knight::new, 2)
                        , false)
        );
    }

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


        assertEquals(Lancer.class, myArmy.unitAtPosition(0)
                                         .getClass());
        assertEquals(Healer.class, myArmy.unitAtPosition(1)
                                         .getClass());

        assertEquals(Warlord.class, myArmy.unitAtPosition(-1)
                                          .getClass());

        assertEquals(Vampire.class, enemyArmy.unitAtPosition(0)
                                             .getClass());
        assertEquals(Warlord.class, enemyArmy.unitAtPosition(-1)
                                             .getClass());
        assertEquals(Knight.class, enemyArmy.unitAtPosition(-2)
                                            .getClass());


        assert enemyArmy.size() == 6;

        assertTrue(Battle.fight(myArmy, enemyArmy));
    }

    @ParameterizedTest
    @MethodSource("checkBattleBetweenArmiesWithWarlordSource")
    void checkBattleBetweenArmiesWithWarlord(Army army1, Army army2, boolean expected) {
        army1.moveUnits();
        army2.moveUnits();

        assertEquals(expected, Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Battle 25.")
    void battle25() {
        Army myArmy = new Army(Warrior::new, 2)
                .addUnits(Lancer::new, 3)
                .addUnits(Defender::new, 1)
                .addUnits(Warlord::new, 4);
        Army enemyArmy = new Army(Warlord::new, 1)
                .addUnits(Vampire::new, 1)
                .addUnits(Rookie::new, 1)
                .addUnits(Knight::new, 1);

        myArmy.moveUnits();
        enemyArmy.moveUnits();

        myArmy.equipWarriorAtPosition(0,WeaponI.newSword());
        enemyArmy.equipWarriorAtPosition(0,WeaponI.newShield());

        assertTrue(Battle.straightFight(myArmy,enemyArmy));
    }


    @Test
    @DisplayName("Battle 26.")
    void battle26() {
         Army myArmy = new Army(Warrior::new, 2)
                .addUnits(Lancer::new, 3)
                .addUnits(Defender::new, 1)
                .addUnits(Warlord::new, 1);
         Army enemyArmy = new Army(Warlord::new, 5)
                        .addUnits(Vampire::new, 1)
                        .addUnits(Rookie::new, 1)
                        .addUnits(Knight::new, 1);

         myArmy.moveUnits();
         enemyArmy.moveUnits();

         myArmy.equipWarriorAtPosition(0,WeaponI.newSword());
         enemyArmy.equipWarriorAtPosition(0,WeaponI.newShield());

        // Wrong assertion in original code
         assertTrue(Battle.straightFight(myArmy,enemyArmy));

    }
}
