package org.example.game.characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface WarriorInArmy {
    Warrior getNextBehind();

    Warrior getPrevFront();

    void takeOrder(Command command);

    Warrior getWarrior();
}

interface Command {
    void executeCommand(WarriorInArmy warriorNode);

}

class HealingCommand implements Command {

    @Override
    public void executeCommand(WarriorInArmy currentWarrior) {
        Warrior warriorInFront = currentWarrior.getPrevFront();
        if (((WarriorInArmy) warriorInFront).getWarrior() != null && currentWarrior.getWarrior() instanceof Healer healer) {
            healer.heal(warriorInFront);
        }
    }
}

class ArcherCommand implements Command {

    static Logger log = LoggerFactory.getLogger(ArcherCommand.class);
    CanReceiveDamage defender;

    public ArcherCommand(CanReceiveDamage defender) {
        this.defender = defender;
    }

    @Override
    public void executeCommand(WarriorInArmy warriorNode) {
        if ((defender instanceof WarriorInArmy defenderInArmy)
                && (warriorNode.getWarrior() instanceof Archer archer)) {

            archer.hitWithBow(defender);
            log.debug("{}", defender.getClass().getSimpleName());
            if (defenderInArmy.getNextBehind() != null) {
                defender = defenderInArmy.getNextBehind();
            }
        }
    }
}

