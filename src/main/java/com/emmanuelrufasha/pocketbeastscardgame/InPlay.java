package com.emmanuelrufasha.pocketbeastscardgame;

import interfaces.ICard;
import interfaces.IGraveyard;
import interfaces.IInPlay;

import java.util.ArrayList;

public class InPlay implements IInPlay {
    
    private final ArrayList<ICard> cards;
    private IGraveyard graveyard;

    public InPlay() {
        this.cards = new ArrayList<>();
        this.graveyard = new Graveyard();
    }

    @Override
    public ArrayList<ICard> getCards() {
        //return cards;
        return new ArrayList<>(cards); // Return a copy of the cards to maintain encapsulation
    }
    
    @Override
    public ICard getCard(int index) {
        return cards.get(index);
    }
    
    @Override
    public void add(ICard card) {
        this.cards.add(card);
    }
    
    @Override
    public void remove(ICard card) {
        this.cards.remove(card);       
        graveyard.add(card);// Add the removed card to the graveyard
    }
    
    @Override
    public void removeAll(ArrayList<ICard> cards) {
        this.cards.removeAll(cards);    
        //System.out.println();
        graveyard.addAll(cards); // Add the removed cards to the graveyard
    }
    
    @Override
    public int count() {
        return this.cards.size();
    }
}

/*
------------------------ CHANGES MADE ------------------------

1. Modified the getCards method

- I have modiefied the getCards method to return a copy of the cards list to prevent direct modification 
  of the internal list from outside the class. This promotes encapsulation and protects the integrity of
  the in-play cards.
*/