package com.emmanuelrufasha.pocketbeastscardgame;

import interfaces.ICard;
import interfaces.IDeck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class Deck implements IDeck{
    
    private final ArrayList<ICard> cards;
    
    //Constructor adds cards to the Deck
    public Deck(ArrayList<ICard> cards) {
        this.cards = cards;
    }
    
    //Ability to get a card by ID
    public ICard getCardById(String cardId) {
        for (ICard card : cards) {
            if (card.getId().equals(cardId)) {
                return card;
            }
        }
        return null; // Card with the given ID notfound
    }
    
    //the returns the size of the deck, based on how many cards are within it
    @Override
    public int count() {
        return this.cards.size();
    }
    
    @Override
    public Optional<ICard> draw() {
        // Check if the deck is empty
        if (this.cards.isEmpty()) {
            return Optional.empty(); // Return an empty Optional if the deck is empty
        }

        // Draw the top card from the deck
        ICard card = this.cards.get(0);
        this.cards.remove(0); // Remove the card from the deck
        return Optional.of(card); // Return the card wrapped in an Optional
    }
    
    @Override
    public void shuffle() {
        Collections.shuffle(this.cards);
    }
    
    //returns a new ArrayList containing the cards.
    @Override
    public ArrayList<ICard> getCards() {
        // Return a copy of the cards to maintain encapsulation
        return new ArrayList<>(this.cards);
    }
    
}

/*
--------------------- CHANGES MADE ------------------------------

1. Added a getCards method
--------------------------

 - By returning a copy of the cards list instead of the original reference, 
   I'm preventing external code from modifying the deck's cards unintentionally.


2. Modified the draw method
----------------------------

- The draw() method in the Deck class has been modified to return an Optional<Card> 
 object instead of a Card object. This change has been made to provide better handling 
 for scenarios where the deck is empty. Previously, if the draw() method was called on an empty deck, 
 it would throw an IndexOutOfBoundsException. By returning an Optional<Card>, 
 the method now indicates the possibility of an empty deck and avoids potential errors.

 When calling the draw() method, you can check if a card was successfully drawn by using the 
 isPresent() method of the Optional<Card> object. If a card was drawn, you can retrieve it 
 using the get() method. If the deck is empty, you can handle this case accordingly without 
 encountering an exception.

This will allow the codebase to become more robust and explicit about the possibility of an 
empty deck, allowing for better error handling and overall code reliability.

 Example usage:
 Optional<Card> optionalCard = deck.draw();
 if (optionalCard.isPresent()) {
     Card card = optionalCard.get();
     // Perform actions with the drawn card
 } else {
     // Handle the case of an empty deck
     // No card was drawn
 }
*/



/*
Reference Notes for later :
----------------------------

public Card draw() {
    if (this.cards.isEmpty()) {
        return null;
    }
    
    Card card = this.cards.get(0);
    this.cards.remove(0);
    return card;
}

Implementation in main method or wherever...:

Card card = deck.draw();
if (card != null) {
    System.out.println(card);
} else {
    System.out.println("No cards left in the deck.");
}

*/