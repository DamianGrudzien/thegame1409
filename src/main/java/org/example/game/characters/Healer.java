package org.example.game.characters;

public class Healer extends Warrior implements HasHealing{

    private int healingPower = 2;
    private int healingStamina = 50;

    public Healer() {
        super(60, 0);
    }

    public void heal(Warrior woundedWarrior){
        if(woundedWarrior instanceof WarriorInArmy && healingStamina > 0) {
            woundedWarrior.setHealth(woundedWarrior.getHealth() + healingPower);
            healingStamina--;
        }
    }


    public void setHealingPower(int healingPower) {
        this.healingPower = healingPower;
    }

    @Override
    public int getHealingPower() {
        return healingPower;
    }

    @Override
    public void hit(CanReceiveDamage defender) {
        // Healer is not attacking
    }
}
