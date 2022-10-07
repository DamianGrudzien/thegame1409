package org.example.game.characters;

import org.example.game.Battle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @DisplayName("First fight when myArmy win")
    void firstFightWhenMyArmyWin() {
        // Given
        var myArmy = new Army(Archer::new,3)
                .addUnits(Healer::new,2)
                .addUnits(Knight::new, 2)
                .addUnits(Warlord::new,3);


        var enemyArmy = new Army(Lancer::new,1)
                .addUnits(Healer::new,2)
                .addUnits(Knight::new, 2)
                .addUnits(Warlord::new,2);

        // When
        myArmy.moveUnits();
        enemyArmy.moveUnits();
        var result = Battle.fight(myArmy,enemyArmy);

        //Then
        assertTrue(result);
    }

    @Test
    @DisplayName("Archer not attacking third enemy when straight fight")
    void archerNotAttackingThirdEnemyWhenStraightFight() {
        // Given
        var army1 = new Army(Warrior::new,1).addUnits(Lancer::new,2);
        var army2 = new Army(Archer::new,2);

        // When
        Battle.straightFight(army1,army2);

        //Then
        assertEquals(50,army1.unitAtPosition(2).getHealth());
    }

    @Test
    @DisplayName("Archer attack third enemy whenSimpleBattle")
    void archerAttackThirdEnemyWhenSimpleBattle() {
        // Given
        var army1 = new Army(Warrior::new,1).addUnits(Lancer::new,3);
        var army2 = new Army(Warrior::new,1).addUnits(Archer::new,2);

        // When
        Battle.fight(army1,army2);

        //Then
        assertEquals(46,army1.unitAtPosition(1).getHealth());
    }

    @Test
    @DisplayName("Archer not attacking after losing all arrows")
    void archerNotAttackingAfterLosingAllArrows() {
        // Given
        var army1 = new Army(Warrior::new,6).addUnits(Archer::new,2);
        var army2 = new Army(Warrior::new,6);

        // When
        Battle.fight(army1, army2);
        var res = ((Archer) army1.unitAtPosition(4)).getArrows();
        var res1 = ((Archer) army1.unitAtPosition(5)).getArrows();

        //Then
        assertEquals(0, res);
        assertEquals(0, res1);
    }

}
