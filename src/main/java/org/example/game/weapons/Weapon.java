package org.example.game.weapons;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public
class Weapon implements WeaponI {

    private int health;
    private int attack;
    private int defense;
    private int vampirism;
    private int healPower;
}