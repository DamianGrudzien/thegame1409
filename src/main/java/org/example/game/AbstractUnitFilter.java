package org.example.game;

import org.example.game.characters.Warrior;

public abstract class AbstractUnitFilter implements UnitFilter {
    protected UnitFilter nextUnitFilter;
    protected Warrior warrior;

    @Override
    public void checkUnit(Warrior warrior) {
    }
}
