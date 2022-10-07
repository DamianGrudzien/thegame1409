package org.example.game.characters;

import org.example.game.weapons.WeaponI;

public class Vampire extends Warrior implements KnowsDamageDealt{

    protected int vampirism = 50;

    public int getInitialVampirism() {
        return initialVampirism;
    }

    protected int initialVampirism = vampirism;


    public Vampire() {
        super(40, 4);
    }


    @Override
    public void receiveDamage(HasAttack damager) {
        setHealth(getHealth() - damager.getAttack());
    }

    @Override
    public void hit(CanReceiveDamage defender) {
        int damageDealt = hitAndReportDamage(defender);
        final int percents = 100;
        setHealth(getHealth() +  (damageDealt * getVampirism()) / percents);
    }

    @Override
    public void equipWeapon(WeaponI weaponI) {
        super.equipWeapon(weaponI);
        int getBonusVamipirism = weapons.stream()
                                       .mapToInt(WeaponI::getVampirism)
                                       .sum();
        setVampirism(getInitialVampirism() + getBonusVamipirism);
    }

    int getVampirism() {
        return vampirism;
    }


    public void setVampirism(int vampirism) {
        this.vampirism = Math.max(0,vampirism);
    }
}
