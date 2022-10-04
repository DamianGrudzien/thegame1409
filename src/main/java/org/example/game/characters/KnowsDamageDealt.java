package org.example.game.characters;

public interface KnowsDamageDealt extends HasAttack{
    default int hitAndReportDamage(CanReceiveDamage defender){
        int healthBefore = defender.getHealth();
        HasAttack.super.hit(defender);
        int healthAfter = defender.getHealth();
        return healthBefore - healthAfter;
    }
}
