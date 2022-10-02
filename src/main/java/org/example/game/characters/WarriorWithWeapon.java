package org.example.game.characters;

import org.example.game.weapons.WeaponI;

public class WarriorWithWeapon extends Warrior implements HasHealingPower, HasDefense, HasVampirism {
    private Warrior warrior;
    private WeaponI weaponI;

    public WarriorWithWeapon(Warrior warrior, WeaponI weaponI) {
        this.warrior = warrior;
        this.weaponI = weaponI;
    }

    @Override
    public int getDefense() {
        return Math.max(0, getBaseDefense() + getDefenseBonus());
    }


    @Override
    public int getHealingPower() {
        return Math.max(0, getBaseHealingPower() + getHealingBonus());
    }

    private int getHealingBonus() {
        if (warrior instanceof WarriorWithWeapon warriorWithWeapon) {
            return warriorWithWeapon.getHealingBonus() + weaponI.getHealPower();
        }
        return weaponI.getHealPower();
    }

    private int getBaseHealingPower() {
        if (warrior instanceof WarriorWithWeapon warriorWithWeapon) {
            return warriorWithWeapon.getBaseHealingPower();
        }
        if(warrior instanceof HasHealingPower healer){
            return healer.getHealingPower();
        }
        return 0;
    }

    @Override
    public int getVampirism() {
        return Math.max(0, getBaseVampirism() + getVampirismBonus());
    }

    private int getVampirismBonus() {
        if (warrior instanceof WarriorWithWeapon warriorWithWeapon) {
            return warriorWithWeapon.getVampirismBonus() + weaponI.getVampirism();
        }
        return weaponI.getVampirism();
    }

    private int getBaseVampirism() {
        if (warrior instanceof WarriorWithWeapon warriorWithWeapon) {
            return warriorWithWeapon.getBaseVampirism();
        }
        if(warrior instanceof HasVampirism vampire){
            return vampire.getVampirism();
        }
        return 0;
    }


    private int getAttackBonus() {
        if (warrior instanceof WarriorWithWeapon warriorWithWeapon) {
            return warriorWithWeapon.getAttackBonus() + weaponI.getAttack();
        }
        return weaponI.getAttack();
    }

    private int getBaseAttack() {
        if (warrior instanceof WarriorWithWeapon warriorWithWeapon) {
            return warriorWithWeapon.getBaseAttack();
        }
        return warrior.getAttack();
    }

    @Override
    public int getAttack() {
        return Math.max(0, getBaseAttack() + getAttackBonus());
    }

    @Override
    public int getHealth() {
        return Math.max(0, getBaseHealth() + getHealthBonus());
    }

    private int getHealthBonus() {
        if (warrior instanceof WarriorWithWeapon warriorWithWeapon) {
            return warriorWithWeapon.getHealthBonus() + weaponI.getHealth();
        }
        return weaponI.getHealth();
    }

    private int getBaseHealth() {
        if (warrior instanceof WarriorWithWeapon warriorWithWeapon) {
            return warriorWithWeapon.getBaseHealth();
        }
        return warrior.getHealth();
    }

    private int getDefenseBonus() {
        if (warrior instanceof WarriorWithWeapon warriorWithWeapon) {
            return warriorWithWeapon.getDefenseBonus() + weaponI.getDefense();
        }
        return weaponI.getDefense();
    }

    private int getBaseDefense() {
        if (warrior instanceof WarriorWithWeapon warriorWithWeapon) {
            return warriorWithWeapon.getBaseHealth();
        }
        if (warrior instanceof HasDefense defender) {
            return defender.getDefense();
        }
        return 0;
    }
}