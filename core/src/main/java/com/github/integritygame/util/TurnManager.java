package com.github.integritygame.util;

import java.util.LinkedList;
import java.util.ListIterator;

public class TurnManager<e> {

    public e turnId;

    public LinkedList<e> playersId;

    public ListIterator<e> lis;

    public TurnManager(LinkedList<e> playersId){
        this.playersId = playersId;
        lis = playersId.listIterator();
        turnId = lis.next();
    }

    public e getTurnId(){
        return turnId;
    }

    public void nextTurn(){
        if(!lis.hasNext()){
            lis = playersId.listIterator();
        }
        turnId = lis.next();
    }

}
