package org.example.game.characters;

public class HealingCommand implements Command{

    @Override
    public void executeCommand(Army.Node currentWarrior) {
        Army.Node warriorInFront =  currentWarrior.prev;
        if (warriorInFront.getWarrior() != null && currentWarrior.getWarrior() instanceof Healer healer) {
            healer.heal(warriorInFront);
        }
    }
}
