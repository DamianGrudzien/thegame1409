package org.example.game.characters;

import org.example.game.weapons.WeaponI;

public class Healer extends Warrior implements HasHealing{

    private int healPower = 2;

    public int getInitialHealPower() {
        return initialHealPower;
    }

    private final int initialHealPower = healPower;
    private int healingStamina = 50;

    public Healer() {
        super(60, 0);
    }

    public void heal(Warrior woundedWarrior){
        if((woundedWarrior instanceof WarriorInArmy) && (healingStamina) > 0) {
            woundedWarrior.setHealth(woundedWarrior.getHealth() + healPower);
            healingStamina--;
        }
    }

    public void setHealPower(int healPower) {
        this.healPower = Math.max(0,healPower);
    }

    @Override
    public int getHealPower() {
        return healPower;
    }

    @Override
    public void hit(CanReceiveDamage defender) {
        // Healer is not attacking
    }

    @Override
    public void equipWeapon(WeaponI weaponI) {
        super.equipWeapon(weaponI);
        int sumOfHealingBonus = weapons.stream()
                                       .mapToInt(WeaponI::getHealPower)
                                       .sum();
        setHealPower(getInitialHealPower() + sumOfHealingBonus);
    }
}
