package com.emmanuelrufasha.pocketbeastscardgame;

import interfaces.ICard;
import interfaces.IHand;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hand implements IHand {
    
    private final ArrayList<ICard> cards;

    //Constructor for creating a new Hand object
    public Hand() {
        this.cards = new ArrayList<>(); // Initialize the cards as an empty ArrayList
    }

    // Get the cards in the hand
    @Override
    public ArrayList<ICard> getCards() {
        //return this.cards;
        return new ArrayList<>(cards); // Return a copy of the cards to maintain encapsulation
    }
    
    // Add or remove a card to/from the hand based on the parameter
    @Override
    public void modifyCard(ICard card, boolean add) {
        if (add) {
            this.cards.add(card); // Add the card to the hand if the 'add' parameter is true
            this.sort(); //Sort all the cards in hand
        } else {
            this.cards.remove(card); // Remove the card from the hand if the 'add' parameter is false
        }
        this.sort();  // Sort the cards in the hand
    }

    
    // Remove all cards from the hand
    @Override
    public void removeAll(ArrayList<ICard> cards) {
        this.cards.removeAll(cards);
    }
    
    // Get the count of cards in the hand
    @Override
    public int count() {
        return this.cards.size();
    }
    
    // Sort the cards in the hand
@Override
public void sort() {
    Collections.sort(cards, new Comparator<ICard>() {
        @Override
        public int compare(ICard card1, ICard card2) {
            return Integer.compare(card1.getManaCost(), card2.getManaCost());
        }
    });
}

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        // Display the top border for each card
        for (int i=0; i<this.count(); i++) {
            sb.append("+-------+ ");
        }
        sb.append("\n");
        
        // Display the mana cost of each card
        for (ICard card : cards) {
            sb.append(String.format("|%7d| ", card.getManaCost()));
        }
        sb.append("\n");
        
        // Display the card ID of each card
        for (ICard card : cards) {
            sb.append(String.format("|  %-5s| ", card.getId()));
        }
        sb.append("\n");
        
        // Display the middle border for each card
        for (int i=0; i<this.count(); i++) {
            sb.append("|       | ");
        }
        sb.append("\n");
        
        // Display the attack and health of each card
        for (ICard card : cards) {
            sb.append(String.format("|%-2d %4d| ", card.getAttack(), card.getHealth()));
        }
        sb.append("\n");
        
        // Display the bottom border for each card
        for (int i=0; i<this.count(); i++) {
            sb.append("+-------+ ");
        }
        sb.append("\n");
        
        return sb.toString();
    }
}

/*
---------------------- CHANGES MADE --------------------

1. getCard method now returns a copy of the cards in order to maintain encapsulation. 

2. Removed the 'add' & 'remove' methods

   - I removed them both due to code duplication, as they had similar code for sorting the 
     cards after the modification. By consolidating the code into a single modifyCard method, 
     we remove the duplication and improve code maintainability. I also removed them both for
     the sake of flexibility.The modifyCard method takes a boolean parameter add, which allows 
     us to control whether a card should be added or removed from the hand. This provides flexibility 
     and allows for potential future enhancements, such as adding additional logic or checks when 
     modifying cards in the hand. Plus, handling the addition and removal of cards from the hand
     in the same method keeps the code simplle and readable.

*/