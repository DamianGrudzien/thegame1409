package org.example.game.characters;

import org.example.game.weapons.WeaponI;

import java.util.*;
import java.util.function.Supplier;

public class Army {
    boolean containsWarlord;

//    @Override
//    public void moveUnits() {
//        if(this.hasWarlord()){
//
//        }
//    }

    public void equipWarriorAtPosition(int position, WeaponI weaponI){
        var it = iterator();
        int counter = 0;
        while(it.hasNext()){
            if(position == counter++){
                it.next().equipWeapon(weaponI);
                break;
            }
            it.next();
        }
    }

//    private boolean hasWarlord(){
//        return containsWarlord;
//    }

    protected class Node
            extends Warrior
            implements WarriorInArmy, HealerInArmy{

        Warrior getWarrior() {
            return warrior;
        }
        Warrior warrior;

        Node next;

        public Node(Warrior warrior) {
            this.warrior = warrior;
            this.next = this;
        }


        @Override
        public Warrior getNextBehind() {
            return (next != head) ? next : null;
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
        }

        @Override
        public void healUnit(Warrior wounded){
            if (warrior instanceof Healer healer) {
                healer.heal(wounded);
            }
            if(next != head) {
                next.healUnit(warrior);
            }
        }

        public void takeOrder(Command command){

            command.executeCommand(this);
            if(next != head){
                next.takeOrder(command);
            }
        }

        @Override
        public boolean isAlive() {
            return warrior.isAlive();
        }

    }
    public void removeDeadWarriors(){
        var it = iterator();
            while(it.hasNext()) {
                if(!it.next().isAlive()){
                    it.remove();
                }
            }
    }


    private final Node head = new Node(null);
    private Node tail = head;

    boolean isEmpty(){
        return tail == head;
    }

    private Warrior peek(){
        return head.next;
    }

    private void removeFromHead(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        if(tail == head.next){
            tail = head;
        }
        head.next = head.next.next;
//        this.moveUnits();
    }

    private void addToTail(Warrior warrior){
        Node newNodeTail = new Node(warrior);
        newNodeTail.next = head;
        tail.next = newNodeTail;
        tail = newNodeTail;
    }


    public Army() {
    }

    public Army(Supplier<Warrior> factory, int quantity) {
        addUnits(factory, quantity);
    }

    public Army addUnits(Supplier<Warrior> factory, int quantity){
//        Warrior warriorFromFactory = factory.get();
//        if(warriorFromFactory instanceof Warlord warlord){
//            var it = iterator();
//            while(it.hasNext()) {
//                if(it.next().getClass() == warlord.getClass()){
//                    return this;
//                }
//            }
//            addToTail(warriorFromFactory);
//            return this;
//        }

        for (int i = 0; i < quantity; i++) {
            addToTail(factory.get());
        }
        return this;

    }

    public Iterator<Warrior> firstAlive(){
        return new FirstAliveIterator();
    }

    public Iterator<Warrior> iterator(){return new SimpleIterator();}

    private class FirstAliveIterator implements Iterator<Warrior>{

        @Override
        public boolean hasNext() {

            while(!isEmpty() && !peek().isAlive()){
                removeFromHead();
            }
            return !isEmpty();
        }

        @Override
        public Warrior next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            var res = peek();
            return res == head ? null : res;
        }

    }
    private class SimpleIterator implements Iterator<Warrior> {
        Node cursor = head;
        Node prev = null;

        public Warrior getRemovedWarrior() {
            return removedWarrior;
        }

        Warrior removedWarrior = null;

        @Override
        public boolean hasNext() {
            return cursor.next != head;
        }

        @Override
        public Warrior next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            removedWarrior = null;
            prev = cursor;
            cursor = cursor.next;
            return cursor.getWarrior();
        }

        @Override
        public void remove() {
            if(prev == null){
                throw new UnsupportedOperationException("remove");
            }
            removedWarrior = cursor.getWarrior();
            prev.next = cursor.next;
            cursor = prev;
            prev = null;
        }
    }



}
