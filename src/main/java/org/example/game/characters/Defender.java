package org.example.game.characters;

import org.example.game.Damage;
import org.example.game.SimpleDamage;

public class Defender extends Warrior {
    private static final int DEFENSE = 2;

    public Defender(){
        super(60,3);
    }

    protected int getDefense(){
        return DEFENSE;
    }

//    @Override
//    public void receiveDamage(int attack) {
//        setHealth(getHealth() - Math.max(0,(attack - getDefense())));
//    }

    @Override
//    public void receiveDamage(HasAttack damager) {
//        setHealth(getHealth() - Math.max(0,(damager.getAttack() - getDefense())));
//    }

    public void receiveDamage(HasAttack damager){
        int reducedDamage = Math.max(0, damager.getAttack() - getDefense());
        super.receiveDamage(() -> reducedDamage);
    }


//    protected void receiveDamage(Damage damage){
//        int reducedDamage = Math.max(0, damage.getValue() - getDefense());
//        super.receiveDamage(new SimpleDamage(reducedDamage, damage.getDamageDealer()));
//    }

}
