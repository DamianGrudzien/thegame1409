package org.example.game;

import org.example.game.characters.Warrior;

public interface Damage {
    Warrior getDamageDealer();
    int getValue();
}
