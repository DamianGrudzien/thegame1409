package org.example.game.characters;

public class HealingCommand implements Command{

    @Override
    public void executeCommand(Army.Node currentWarrior) {
        Army.Node warriorBehind =  currentWarrior.next;
        if (warriorBehind.getWarrior() != null && warriorBehind.getWarrior() instanceof Healer healer) {
            healer.heal(currentWarrior.getWarrior());
        }
    }
}
