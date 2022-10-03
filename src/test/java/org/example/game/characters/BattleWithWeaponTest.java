package org.example.game.characters;

import org.example.game.Battle;
import org.example.game.weapons.Weapon;
import org.example.game.weapons.WeaponI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleWithWeaponTest {
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

    @Test
    @DisplayName("First fight with Weapon")
    void firstFightWithWeapon() {
        var unit1 = new Warrior();
        var unit2 = new Vampire();
        var weapon1 = Weapon.builder().health(10).attack(5).vampirism(40).build();
        var weapon2 = WeaponI.newSword();

        unit1.equipWeapon(weapon1);
        unit2.equipWeapon(weapon2);

        assertTrue(Battle.fight(unit1,unit2));
    }

    @Test
    @DisplayName("Second fight with Weapon")
    void secondFightWithWeapon() {
        var unit1 = new Defender();
        var unit2 = new Lancer();
        var weapon1 = WeaponI.newShield();
        var weapon2 = WeaponI.newGreatAxe();

        unit1.equipWeapon(weapon1);
        unit2.equipWeapon(weapon2);

        assertTrue(Battle.fight(unit1,unit2));
    }

    @Test
    @DisplayName("Third fight with Weapon")
    void thirdFightWithWeapon() {
        var unit1 = new Healer();
        var unit2 = new Knight();

        var weapon1 = WeaponI.newMagicWand();
        var weapon2 = WeaponI.newKatana();

        unit1.equipWeapon(weapon1);
        unit2.equipWeapon(weapon2);

        assertFalse(Battle.fight(unit1,unit2));
    }

    @Test
    @DisplayName("Fourth fight with Weapon")
    void fourthFightWithWeapon() {
        var unit1 = new Defender();
        var unit2 = new Vampire();

        var weapon1 = WeaponI.newShield();
        var weapon2 = WeaponI.newMagicWand();
        var weapon3 = WeaponI.newShield();
        var weapon4 = WeaponI.newKatana();

        unit1.equipWeapon(weapon1);
        unit1.equipWeapon(weapon2);
        unit2.equipWeapon(weapon3);
        unit2.equipWeapon(weapon4);

        assertFalse(Battle.fight(unit1,unit2));
    }

    @Test
    @DisplayName("Fifth fight with Weapon")
    void fifthFightWithWeapon() {
        // Given
        var weapon1 = WeaponI.newMagicWand();
        var weapon2 = WeaponI.newGreatAxe();

        Army myArmy = new Army(Knight::new,1).addUnits(Lancer::new,1);
        Army enemyArmy = new Army(Vampire::new,1).addUnits(Healer::new,1);

        // When
        myArmy.equipWarriorAtPosition(0,weapon1);
        myArmy.equipWarriorAtPosition(1,weapon2);
        enemyArmy.equipWarriorAtPosition(0,weapon1);
        enemyArmy.equipWarriorAtPosition(1,weapon2);

        //Then
        assertTrue(Battle.fight(myArmy,enemyArmy));
    }

    @Test
    @DisplayName("Sixth fight with Weapon")
    void sixthFightWithWeapon() {
        // Given
        var weapon1 = WeaponI.newSword();
        var weapon2 = WeaponI.newGreatAxe();

        Army myArmy = new Army(Defender::new,1).addUnits(Warrior::new,1);
        Army enemyArmy = new Army(Knight::new,1).addUnits(Healer::new,1);

        // When
        myArmy.equipWarriorAtPosition(0,weapon2);
        myArmy.equipWarriorAtPosition(1,weapon2);
        enemyArmy.equipWarriorAtPosition(0,weapon1);
        enemyArmy.equipWarriorAtPosition(1,weapon1);

        //Then
        // Wrong assertion in original code
        assertFalse(Battle.fight(myArmy,enemyArmy));
    }
    @Test
    @DisplayName("Seventh fight with Weapon")
    void seventhFightWithWeapon() {
        // Given
        var weapon1 = WeaponI.newKatana();
        var weapon2 = WeaponI.newShield();

        Army myArmy = new Army(Defender::new,2);
        Army enemyArmy = new Army(Knight::new,1).addUnits(Vampire::new,1);

        // When
        myArmy.equipWarriorAtPosition(0,weapon1);
        myArmy.equipWarriorAtPosition(1,weapon1);
        enemyArmy.equipWarriorAtPosition(0,weapon1);
        enemyArmy.equipWarriorAtPosition(1,weapon1);

        //Then
        assertFalse(Battle.fight(myArmy,enemyArmy));
    }

    @Test
    @DisplayName("Eighth fight with Weapon")
    void eighthFightWithWeapon() {
        // Given
        var weapon1 = Weapon.builder().health(-20).attack(6).defense(1).vampirism(40).healPower(-2).build();
        var weapon2 = Weapon.builder().health(20).attack(-2).defense(2).vampirism(-55).healPower(3).build();

        Army myArmy = new Army(Knight::new,3);
        Army enemyArmy = new Army(Warrior::new,1)
                                .addUnits(Defender::new,2);

        // When
        myArmy.equipWarriorAtPosition(0,weapon1);
        myArmy.equipWarriorAtPosition(1,weapon1);
        myArmy.equipWarriorAtPosition(2,weapon2);
        enemyArmy.equipWarriorAtPosition(0,weapon1);
        enemyArmy.equipWarriorAtPosition(1,weapon2);
        enemyArmy.equipWarriorAtPosition(2,weapon2);

        //Then
        assertTrue(Battle.fight(myArmy,enemyArmy));
    }
    @Test
    @DisplayName("Ninth fight with Weapon")
    void ninthFightWithWeapon() {
        // Given
        var weapon1 = Weapon.builder().health(-20).attack(1).defense(1).vampirism(40).healPower(-2).build();
        var weapon2 = Weapon.builder().health(20).attack(2).defense(2).vampirism(-55).healPower(3).build();

        Army myArmy = new Army(Vampire::new,3);
        Army enemyArmy = new Army(Warrior::new,1)
                .addUnits(Defender::new,2);

        // When
        myArmy.equipWarriorAtPosition(0,weapon1);
        myArmy.equipWarriorAtPosition(1,weapon1);
        myArmy.equipWarriorAtPosition(2,weapon2);
        enemyArmy.equipWarriorAtPosition(0,weapon1);
        enemyArmy.equipWarriorAtPosition(1,weapon2);
        enemyArmy.equipWarriorAtPosition(2,weapon2);

        //Then
        assertFalse(Battle.straightFight(myArmy,enemyArmy));
    }

    @Test
    @DisplayName("Tenth fight with Weapon")
    void tenthFightWithWeapon() {
        // Given
        var weapon1 = WeaponI.newKatana();
        var weapon2 = WeaponI.newShield();

        Army myArmy = new Army(Vampire::new,2).addUnits(Rookie::new,2);
        Army enemyArmy = new Army(Warrior::new,1).addUnits(Defender::new,2);

        // When
        myArmy.equipWarriorAtPosition(0,weapon1);
        myArmy.equipWarriorAtPosition(1,weapon1);
        myArmy.equipWarriorAtPosition(2,weapon2);
        enemyArmy.equipWarriorAtPosition(0,weapon1);
        enemyArmy.equipWarriorAtPosition(1,weapon2);
        enemyArmy.equipWarriorAtPosition(2,weapon2);

        //Then
        assertTrue(Battle.straightFight(myArmy,enemyArmy));
    }

    @Test
    @DisplayName("Eleventh fight with Weapon")
    void eleventhFightWithWeapon() {
        // Given
        var weapon1 = WeaponI.newSword();
        var weapon2 = WeaponI.newGreatAxe();

        Army myArmy = new Army(Vampire::new,3);
        Army enemyArmy = new Army(Warrior::new,1).addUnits(Defender::new,1);

        // When
        myArmy.equipWarriorAtPosition(0,weapon2);
        myArmy.equipWarriorAtPosition(1,weapon2);
        myArmy.equipWarriorAtPosition(2,weapon2);
        enemyArmy.equipWarriorAtPosition(0,weapon1);
        enemyArmy.equipWarriorAtPosition(1,weapon1);

        //Then
        assertFalse(Battle.straightFight(myArmy,enemyArmy));
    }

    @Test
    @DisplayName("Twelfth fight with Weapon")
    void twelfthFightWithWeapon() {
        // Given
        var weapon1 = WeaponI.newKatana();
        var weapon2 = WeaponI.newMagicWand();

        Army myArmy = new Army(Rookie::new,3);
        Army enemyArmy = new Army(Defender::new,1).addUnits(Healer::new,1);

        // When
        myArmy.equipWarriorAtPosition(0,weapon1);
        myArmy.equipWarriorAtPosition(1,weapon1);
        myArmy.equipWarriorAtPosition(2,weapon1);
        enemyArmy.equipWarriorAtPosition(0,weapon2);
        enemyArmy.equipWarriorAtPosition(1,weapon2);

        //Then
        assertFalse(Battle.straightFight(myArmy,enemyArmy));
    }

}
