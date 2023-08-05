package com.emmanuelrufasha.pocketbeastscardgame;

import interfaces.ICard;
import interfaces.IGraveyard;
import java.util.ArrayList;
import java.util.List;

public class Graveyard implements IGraveyard {
    
    private final ArrayList<ICard> cards;

    //Construct a new Graveyard object
    public Graveyard() {
        this.cards = new ArrayList<>();
    }
    
    //allows other classes to add a single card to the graveyard.
    @Override
    public void add(ICard card) {
        this.cards.add(card);
    }
    
    //Allows other classes to add a list of cards to the graveyard at once 
    @Override
    public void addAll(List<ICard> cardList) {
        cards.addAll(cardList);
    }
        
    //Return the number of cards in the graveyard.
    @Override
    public int count() {
        return this.cards.size();
    }    
        
    //Return the list of cards in the graveyard.
    @Override
    public ArrayList<ICard> getCards() {
        return new ArrayList<>(this.cards);
    }
        
}


/*
-------------------- CHANGES MADE ---------------------

1. Added a getCards method

  - getCards() method is the getter method that allows external classes to access the cards in 
    the graveyard. It returns a copy of the cards list to ensure that the original list remains 
    encapsulated and cannot be directly modified from outside the Graveyard class.

2. Removed the add method

  - I removed the 'add' method from the Graveyard class and instead put it in the InPlay class under 
    the name 'addCardToGraveyard', the reason being that it makes more sense to handle adding a card 
    to the Graveyard in the 'InPlay'. Seperation of concerns : prefer to have the Graveyard class 
    manage the cards in the graveyard, and InPlay class can handle the transition of cards from the 
    play to the graveyard

*/


