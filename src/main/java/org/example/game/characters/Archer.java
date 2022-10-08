package org.example.game.characters;

import org.example.game.weapons.Weapon;
import org.example.game.weapons.WeaponI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Archer extends Warrior implements KnowsDamageDealt{
    private Logger log = LoggerFactory.getLogger(Archer.class);
    private final Weapon arrowAttack = WeaponI.newBow();

    private int arrows = 20;

    public Archer() {
        super(30, 2);
    }

    @Override
    public void hit(CanReceiveDamage defender) {
        int damageDealt = hitAndReportDamage(defender);

        log.debug("Archer melee attack: {}", damageDealt);
        log.debug("Archer health: {}", this.getHealth());
        log.debug("{} health after attack: {}", ((Warrior) defender).getClass().getSimpleName(), defender.getHealth());
    }

    public void hitWithBow(CanReceiveDamage defender){
        if (arrows > 0 && defender.isAlive()) {
            int beforeAttack = defender.getHealth();
            defender.receiveDamage(arrowAttack);
            int difference = beforeAttack - defender.getHealth();
            log.debug("Archer attack with arrow: {}", difference);
            arrows--;
        }


        log.debug("{} health: {}", ((WarriorInArmy) defender).getWarrior().getClass().getSimpleName(), defender.getHealth());
        log.debug("Arrows left: {}",arrows);
    }

    // For test purpose only
    int getArrows(){
        return arrows;
    }
}
