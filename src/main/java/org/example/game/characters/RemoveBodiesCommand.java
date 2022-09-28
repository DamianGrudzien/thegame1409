package org.example.game.characters;

public class RemoveBodiesCommand implements Command{

    Army.Node head;
    Army.Node cursor;

    public RemoveBodiesCommand(Army.Node head) {
        this.cursor = this.head = head;
    }

    @Override
    public void executeCommand(Army.Node currentWarrior) {
        Army.Node nextWarrior = currentWarrior.next;
        if(currentWarrior.getWarrior().isAlive()){
            cursor = currentWarrior;
        } else {
            cursor.next = nextWarrior == head ? head : nextWarrior;
        }
    }
}
