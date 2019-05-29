package com.github.integritygame.util;

import java.util.LinkedList;
import java.util.ListIterator;

public class TurnManager<e> {

    private e turnId;

    private LinkedList<e> playersId;

    private ListIterator<e> lis;

    /**
     * This will take a list of items to take turnes
     *
     * @param playersId All players ID
     */
    TurnManager(LinkedList<e> playersId) {
        this.playersId = playersId;
        lis = playersId.listIterator();
        turnId = lis.next();
    }

    /**
     * Will get the current object whose turn it is
     *
     * @return Current users ID
     */
    e getTurnId() {
        return turnId;
    }

    /**
     * Will set the next person in the queue to the person whose turn it is
     */
    void nextTurn() {
        if (!lis.hasNext()) {
            lis = playersId.listIterator();
        }
        turnId = lis.next();
    }

}
