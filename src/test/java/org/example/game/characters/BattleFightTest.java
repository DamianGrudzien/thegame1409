package org.example.game.characters;

import org.example.game.Battle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BattleFightTest {

    @Test
    @DisplayName("Smoke Test")
    void smokeTest() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();
        var bob = new Defender();
        var mike = new Knight();
        var rog = new Warrior();
        var lancelot = new Defender();
        var rocky = new Rookie();
        var tiger = new Defender();
        var eric = new Vampire();
        var adam = new Vampire();
        var richard = new Defender();
        var ogre = new Warrior();
        var freelancer = new Lancer();
        var vampire = new Vampire();

        Assertions.assertTrue(Battle.fight(chuck, bruce));
        assertFalse(Battle.fight(dave, carl));
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
        assertFalse(Battle.fight(carl, mark));
        assertFalse(carl.isAlive());
        assertFalse(Battle.fight(bob, mike));
        assertTrue(Battle.fight(lancelot, rog));
        assertTrue(Battle.fight(lancelot, rocky));
        assertEquals(60,tiger.getHealth());
        assertFalse(Battle.fight(eric, richard));
        assertTrue(Battle.fight(ogre, adam));
        assertTrue(Battle.fight(freelancer, vampire));
        assertTrue(freelancer.isAlive());

        var myArmy = new Army();
        myArmy.addUnits(Defender::new, 2);
        myArmy.addUnits(Vampire::new, 2);
        myArmy.addUnits(Lancer::new, 4);
        myArmy.addUnits(Warrior::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warrior::new, 2);
        enemyArmy.addUnits(Lancer::new, 2);
        enemyArmy.addUnits(Defender::new, 2);
        enemyArmy.addUnits(Vampire::new, 3);

        var army3 = new Army();
        army3.addUnits(Warrior::new, 1);
        army3.addUnits(Lancer::new, 1);
        army3.addUnits(Defender::new, 1);

        var army4 = new Army();
        army4.addUnits(Vampire::new, 3);
        army4.addUnits(Warrior::new, 1);
        army4.addUnits(Lancer::new, 2);

        var army5 = new Army();
        army5.addUnits(Warrior::new, 10);

        var army6 = new Army();
        army6.addUnits(Warrior::new, 6);
        army6.addUnits(Lancer::new, 5);


        assertTrue(Battle.fight(myArmy, enemyArmy));
        assertFalse(Battle.fight(army3, army4));
        assertFalse(Battle.straightFight(army5, army6));
    }

    @Test
    @DisplayName("Defender Against two warriors")
    void defenderAgainstTwoWarriors() {
        var myArmy = new Army();
        myArmy.addUnits(Defender::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warrior::new, 2);

        assertFalse(Battle.fight(myArmy, enemyArmy));

    }

    @Test
    @DisplayName("Lancer Against two warriors")
    void LancerAgainstTwoWarriors() {
        var jack = new Warrior();
        var john = new Knight();
        var myArmy = new Army();
        myArmy.addUnits(Lancer::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(() -> jack, 1).addUnits(() -> john, 1);

        assertFalse(Battle.fight(myArmy, enemyArmy));
        assertEquals(11, john.getHealth());

    }


    @Test
    @DisplayName("1. Fight")
    void test01() {
        var carl = new Warrior();
        var jim = new Knight();

        var res = Battle.fight(carl, jim);

        assertFalse(res, "Knight should have won");
    }

    @Test
    @DisplayName("2. Fight")
    void test02() {
        var ramon = new Knight();
        var slevin = new Warrior();

        var res = Battle.fight(ramon, slevin);

        assertTrue(res, "Knight should have won");

    }

    @Test
    @DisplayName("3. Fight")
    void test03() {
        var bob = new Warrior();
        var mars = new Warrior();

        Battle.fight(bob, mars);
        var res = bob.isAlive();

        assertTrue(res, "Bob should be alive");

    }

    @Test
    @DisplayName("4. Fight")
    void test04() {
        var zeus = new Knight();
        var godKiller = new Warrior();

        Battle.fight(zeus, godKiller);
        var res = zeus.isAlive();

        assertTrue(res, "Zeus should be alive");

    }

    @Test
    @DisplayName("5. Fight")
    void test05() {
        var husband = new Warrior();
        var wife = new Warrior();

        Battle.fight(husband, wife);
        var res = wife.isAlive();

        assertFalse(res, "Wife should be dead");

    }

    @Test
    @DisplayName("6. Fight")
    void test06() {
        var dragon = new Warrior();
        var knight = new Knight();

        Battle.fight(dragon, knight);
        var res = knight.isAlive();

        assertTrue(res, "Knight should be alive");

    }

    @Test
    @DisplayName("7. Fight")
    void test07() {
        var unit_1 = new Warrior();
        var unit_2 = new Knight();
        var unit_3 = new Warrior();

        Battle.fight(unit_1, unit_2);
        var res = Battle.fight(unit_2, unit_3);

        assertFalse(res, "Unit 3 should have won");
    }

    @ParameterizedTest
    @MethodSource()
    void checkFightResults(Warrior warrior1, Warrior warrior2, boolean expected) {
        var res = Battle.fight(warrior1, warrior2);

        assertEquals(res, expected);
    }

    private static Stream<Arguments> checkFightResults() {
        return Stream.of(
                Arguments.of(new Warrior(), new Warrior(), true),
                Arguments.of(new Knight(), new Warrior(), true),
                Arguments.of(new Knight(), new Knight(), true),
                Arguments.of(new Warrior(), new Knight(), false)
        );
    }

    @ParameterizedTest
    @MethodSource("checkBattleBetweenArmiesSource")
    void checkBattleBetweenArmies(Army army1, Army army2, boolean expected) {
        // When
        var res = Battle.fight(army1, army2);

        // Then
        assertEquals(res, expected);
    }

    private static Stream<Arguments> checkBattleBetweenArmiesSource() {
        return Stream.of(
                Arguments.of(new Army(Warrior::new, 5).addUnits(Defender::new, 4)
                                                                          .addUnits(Defender::new, 5),
                                        new Army(Warrior::new, 4), true),
                Arguments.of(new Army(Defender::new, 5).addUnits(Warrior::new,20)
                                                                           .addUnits(Defender::new,4),
                        new Army(Warrior::new, 21), true),
                Arguments.of(new Army(Warrior::new, 10).addUnits(Defender::new,5)
                                                       .addUnits(Defender::new,10),
                        new Army(Warrior::new, 5), true),
                Arguments.of(new Army(Defender::new, 2).addUnits(Warrior::new,1)
                                                       .addUnits(Defender::new,1),
                        new Army(Warrior::new, 5), false)
        );
    }

}
