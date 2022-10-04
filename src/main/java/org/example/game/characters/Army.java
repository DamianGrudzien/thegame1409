package org.example.game.characters;

import org.example.game.weapons.WeaponI;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class Army {
    private final Node head = new Node(null);

    private Warlord warlord;
    private int size;
    private Node tail = head;

    public Army() {

    }

    public Army(Supplier<Warrior> factory, int quantity) {
        addUnits(factory, quantity);
    }

    private class Node
            extends Warrior
            implements WarriorInArmy {


        Warrior warrior;
        Node next;
        Node prev;

        public Node(Warrior warrior) {
            this.warrior = warrior;
            this.next = this;
            this.prev = this;
        }

        @Override
        public Warrior getNextBehind() {
            return (next != head) ? next : null;
        }

        @Override
        public Warrior getPrevFront() {
            return prev != head ? prev : head;
        }

        @Override
        public int getAttack() {
            return warrior.getAttack();
        }

        @Override
        public int getHealth() {
            return warrior.getHealth();
        }

        @Override
        protected void setHealth(int health) {
            warrior.setHealth(health);
        }

        @Override
        public void receiveDamage(HasAttack damager) {
            warrior.receiveDamage(damager);
        }

        @Override
        public void hit(CanReceiveDamage defender) {
            warrior.hit(defender);
            next.takeOrder(new HealingCommand());
            if(!(warrior instanceof Archer)) {
                next.takeOrder(new ArcherCommand(defender));
            }
        }

        @Override
        public void takeOrder(Command command) {
            command.executeCommand(this);
            if (next != head) {
                next.takeOrder(command);
            }
        }

        @Override
        public boolean isAlive() {
            return warrior.isAlive();
        }

        public Warrior getWarrior() {
            return warrior;
        }
    }

    public void moveUnits() {
        if (warlord !=null && warlord.isAlive()) {
            Iterator<Warrior> newArmyIterator = warlord.moveUnits(iterator());
            clear();
            while(newArmyIterator.hasNext()){
                addUnits(newArmyIterator::next,1);
            }
        }
    }

    private void clear() {
        warlord = null;
        head.next = head;
        tail = head;
        size = 0;
    }

    public void equipWarriorAtPosition(int position, WeaponI weaponI) {
        var it = iterator();
        int counter = 0;
        while (it.hasNext()) {
            if (position == counter++) {
                it.next()
                  .equipWeapon(weaponI);
                break;
            }
            it.next();
        }
    }

    public void removeDeadWarriors() {
        var it = iterator();
        while (it.hasNext()) {
            if (!it.next()
                   .isAlive()) {
                it.remove();
            }
        }
    }

    public Army addUnits(Supplier<Warrior> factory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            Warrior warrior = factory.get();
            if (warrior instanceof Warlord newWarlord){
                if (warlord == null){
                    warlord = newWarlord;
                    addToTail(warrior);
                    size++;
                } else{
                    break;
                }
            } else {
                addToTail(warrior);
                size++;
            }

        }
        return this;
    }

    private void addToTail(Warrior warrior) {
        Node newNodeTail = new Node(warrior);
        newNodeTail.next = head;
        head.prev = newNodeTail;
        newNodeTail.prev = tail;
        tail.next = newNodeTail;
        tail = newNodeTail;

    }

    public Warrior unitAtPosition(int position) {
        Iterator<Warrior> iterator;
        int cursor = 0;

        if(position < 0){
            cursor = -1;
            iterator = backwardIterator();
            while (iterator.hasNext()){
                if(position == cursor){
                    return iterator.next();
                }
                cursor--;
                iterator.next();
            }
        } else {
            iterator = iterator();
            while (iterator.hasNext()){
                if(position == cursor++){
                    return iterator.next();
                }
                iterator.next();
            }
        }
        return null;
    }

    public int size(){
        return this.size;
    }

    private Warrior peek() {
        return head.next;
    }

    private void removeFromHead() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (tail == head.next) {
            tail = head;
            head.prev = tail;
        }
        head.next = head.next.next;
        head.next.prev = head;
        size--;
        this.moveUnits();
    }

    boolean isEmpty() {
        return tail == head;
    }

    public Iterator<Warrior> firstAlive() {
        return new FirstAliveIterator();
    }

    public Iterator<Warrior> iterator() {
        return new SimpleIterator();
    }
    public Iterator<Warrior> backwardIterator() {
        return new BackwardIterator();
    }
    private class FirstAliveIterator implements Iterator<Warrior> {



        @Override
        public boolean hasNext() {

            while (!isEmpty() && !peek().isAlive()) {
                removeFromHead();
            }
            return !isEmpty();
        }

        @Override
        public Warrior next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            var res = peek();
            return res == head ? null : res;
        }
    }
    private class SimpleIterator implements Iterator<Warrior> {

        Node cursor = head;

        Node prev = null;

        @Override
        public boolean hasNext() {
            return cursor.next != head;
        }

        @Override
        public Warrior next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            prev = cursor;
            cursor = cursor.next;
            return cursor.getWarrior();
        }
        @Override
        public void remove() {
            if (prev == null) {
                throw new UnsupportedOperationException("remove");
            }
            prev.next = cursor.next;
            cursor = prev;
            prev = null;
        }

    }
    private class BackwardIterator implements Iterator<Warrior> {

        Node cursor = head;

        @Override
        public boolean hasNext() {
            return cursor.prev != head;
        }

        @Override
        public Warrior next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            cursor = cursor.prev;
            return cursor.getWarrior();
        }

    }

}
