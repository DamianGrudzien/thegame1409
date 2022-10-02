package org.example.game.characters;

import org.example.game.Battle;
import org.example.game.weapons.Weapon;
import org.example.game.weapons.WeaponI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BattleWithWeaponTest {
    @Test
    @DisplayName("Smoke Test")
    void smokeTest() {
        var ogre = new Warrior();
        var lancelot = new Knight();
        var richard = new Defender();
        var eric = new Vampire();
        var freelancer = new Lancer();
        var priest = new Healer();

        // you may consider using another interfaces for the predefined weapon
        // e.g. you could use enum or some factory
        var sword = WeaponI.newSword();
        var shield = WeaponI.newShield();
        var axe = WeaponI.newGreatAxe();
        var katana = WeaponI.newKatana();
        var wand = WeaponI.newMagicWand();
        // consider using a builder instead
        var superWeapon = Weapon.builder()
                                .health(50)
                                .attack(10)
                                .defense(5)
                                .vampirism(150)
                                .healPower(8)
                                .build();

        //(50, 10, 5, 150, 8);

        ogre.equipWeapon(sword);
        ogre.equipWeapon(shield);
        ogre.equipWeapon(superWeapon);
        lancelot.equipWeapon(superWeapon);
        richard.equipWeapon(shield);
        eric.equipWeapon(superWeapon);
        freelancer.equipWeapon(axe);
        freelancer.equipWeapon(katana);
        priest.equipWeapon(wand);
        priest.equipWeapon(shield);

        assert ogre.getHealth() == 125;
        assert lancelot.getAttack() == 17;
        assert richard.getDefense() == 4;
        assert eric.getVampirism() == 200;
        assert freelancer.getHealth() == 15;
        assert priest.getHealPower() == 5;

        assertFalse(Battle.fight(ogre, eric));
        assertFalse(Battle.fight(priest, richard));
        assertTrue(Battle.fight(lancelot, freelancer));

        var myArmy = new Army();
        myArmy.addUnits(Knight::new, 1);
        myArmy.addUnits(Lancer::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Vampire::new, 1);
        enemyArmy.addUnits(Healer::new, 1);

        myArmy.equipWarriorAtPosition(0, axe);
        myArmy.equipWarriorAtPosition(1, superWeapon);

        enemyArmy.equipWarriorAtPosition(0, katana);
        enemyArmy.equipWarriorAtPosition(1, wand);

        assertTrue(Battle.fight(myArmy, enemyArmy));


    }
}
