package org.example.game.characters;

import org.example.game.weapons.WeaponI;

public class Healer extends Warrior {

    private static final int INITIAL_HEAL_POWER = 2;
    private int healPower;
    private int healingStamina = 50;

    public Healer() {
        super(60, 0);
        healPower = INITIAL_HEAL_POWER;
    }

    private static void healerHitHimself(Healer defHealer) {
        defHealer.receiveDamage(defHealer::getHealth);
    }

    public void heal(Warrior woundedWarrior) {
        if ((woundedWarrior instanceof WarriorInArmy) && (healingStamina) > 0) {
            woundedWarrior.setHealth(woundedWarrior.getHealth() + healPower);
            healingStamina--;
        }
    }

    public int getHealPower() {
        return healPower;
    }

    public void setHealPower(int healPower) {
        this.healPower = Math.max(0, healPower);
    }

    @Override
    public void hit(CanReceiveDamage defender) {
        // Healer is not attacking

        //Healer kill other Healer from different army or in single combat

        if (defender instanceof WarriorInArmy warriorInArmy && warriorInArmy.getWarrior() instanceof Healer healerInArmy) {
            healerHitHimself(healerInArmy);
        } else {
            if (defender instanceof Healer defHealer) {
                healerHitHimself(defHealer);
            }
        }
    }

    @Override
    public void equipWeapon(WeaponI weaponI) {
        super.equipWeapon(weaponI);
        int sumOfHealingBonus = weapons.stream()
                                       .mapToInt(WeaponI::getHealPower)
                                       .sum();
        setHealPower(getInitialHealPower() + sumOfHealingBonus);
    }

    private int getInitialHealPower() {
        return INITIAL_HEAL_POWER;
    }
}
