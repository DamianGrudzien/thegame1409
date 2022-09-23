package org.example.game.characters;

import java.util.*;
import java.util.function.Supplier;

public class Army {


    private static class Node {
        Warrior warrior;
        Node next;

        public Node(Warrior warrior) {
            this.warrior = warrior;
            this.next = this;
        }
    }

    private final Node head = new Node(null);
    private Node tail = head;

    boolean isEmpty(){
        return tail == head;
    }

    private Warrior peek(){
        return head.next.warrior;
    }

    private void removeFromHead(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        if(tail == head.next){
            tail = head;
        }
        head.next = head.next.next;
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

        for (int i = 0; i < quantity; i++) {
            var warrior = factory.get();
            if (!isEmpty()) {
                tail.warrior.setNextWarrior(warrior);
            }
            addToTail(warrior);
        }
        return this;

    }

    public Iterator<Warrior> firstAlive(){
        return new FirstAliveIterator();
    }

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
            return peek();
        }
    }



}
