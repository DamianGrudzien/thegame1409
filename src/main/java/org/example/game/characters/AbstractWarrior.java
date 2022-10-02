package org.example.game.characters;




public abstract class AbstractWarrior implements HasHealth, CanReceiveDamage, HasAttack{

    protected abstract void setHealth(int health);

    protected abstract void setAttack(int attack);

}
