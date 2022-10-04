package org.example.game.characters;

public class Healer extends Warrior implements HasHealing{

    private int healPower = 2;
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
        this.healPower = healPower;
    }

    @Override
    public int getHealPower() {
        return healPower;
    }

    @Override
    public void hit(CanReceiveDamage defender) {
        // Healer is not attacking
    }
}
