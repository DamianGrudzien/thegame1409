package org.example.game.weapons;


import org.example.game.characters.HasAttack;

public interface WeaponI extends HasAttack {

    int getHealth();

    int getAttack();

    int getDefense();

    int getVampirism();

    int getHealPower();
    static Weapon newSword() {
        return Weapon.builder()
                     .health(5)
                     .attack(2)
                     .build();
    }

    static Weapon newShield() {
        return Weapon.builder()
                     .health(20)
                     .attack(-1)
                     .defense(2)
                     .build();
    }

    static Weapon newGreatAxe() {
        return Weapon.builder()
                     .health(-15)
                     .attack(-5)
                     .defense(-2)
                     .vampirism(10)
                     .build();
    }

    static Weapon newKatana() {
        return Weapon.builder()
                     .health(-20)
                     .attack(6)
                     .defense(-5)
                     .vampirism(50)
                     .build();
    }

    static Weapon newMagicWand() {
        return Weapon.builder()
                     .health(30)
                     .attack(3)
                     .healPower(3)
                     .build();
    }

    static Weapon newBow() {
        return Weapon.builder()
                     .attack(4)
                     .build();
    }
}

