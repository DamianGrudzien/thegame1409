package org.example.game.characters;

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

    class ArcherCommand implements Command {
        CanReceiveDamage defender;

        public ArcherCommand(CanReceiveDamage defender) {
            this.defender = defender;
        }

        @Override
        public void executeCommand(WarriorInArmy warriorNode) {
            if(defender instanceof WarriorInArmy defenderInArmy){
                System.out.println((defenderInArmy.getWarrior()));
            }
        }
    }
}

