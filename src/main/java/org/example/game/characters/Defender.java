package org.example.game.characters;

import org.example.game.weapons.WeaponI;

public class Defender extends Warrior implements HasDefense{
    protected int defense = 2;
    protected int initialDefense = defense;

    public Defender(){
        super(60,3);
    }

    protected Defender(int health, int attack, int defense){
        super(health, attack);
        this.defense = defense;
    }

    public int getDefense(){
        return defense;
    }

    public void setDefense(int defense){
        this.defense = Math.max(0,defense);
    }
    public int getInitialDefense() {
        return initialDefense;
    }

    @Override
    public void receiveDamage(HasAttack damager){
        int reducedDamage = Math.max(0, damager.getAttack() - getDefense());
        super.receiveDamage(() -> reducedDamage);
    }

    @Override
    public void equipWeapon(WeaponI weaponI) {
        super.equipWeapon(weaponI);
        int sumOfDefenseBonus = weapons.stream()
                                      .mapToInt(WeaponI::getDefense)
                                      .sum();
        setDefense(getInitialDefense() + sumOfDefenseBonus);
    }
}
