package com.emmanuelrufasha.pocketbeastscardgame;

import interfaces.ICard;
import interfaces.IDeck;
import interfaces.IDeckFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeckFactory implements IDeckFactory {
    
    @Override
    public IDeck createNewDeck() {
        // Create a new ArrayList to store ICard objects representing the cards in the deck
        ArrayList<ICard> cards = new ArrayList<>();
        
        // Populating the card ArrayList with cards
        cards.addAll(Arrays.asList(
            new Card("BR", "Barn Rat", 1, 1, 1),
            new Card("SP", "Scampering Pup", 2, 2, 1),
            new Card("HB", "Hardshell Beetle", 2, 1, 2),
            new Card("VHC", "Vicious House Cat", 3, 3, 2),
            new Card("GD", "Guard Dog", 3, 2, 3),
            new Card("ARH", "All Round Hound", 3, 3, 3),
            new Card("MO", "Moor Owl", 4, 4, 2),
            new Card("HT", "Highland Tiger", 5, 4, 4)
        ));      
        
      
        // Set the attack strategy for the Poison Card using its ID
        ICard poisonCard = new PoisonCard("PC", "Poison Card", 2, 0, 1);
        poisonCard.setAttackStrategy(new PoisonStrategy());
        cards.add(poisonCard);
      
      return new Deck(cards);
    }
}


/*

 - Add an effect that a card has after its played. e.g damage opponents health pool or [REMOVE CARD FROM THEIR HAND]
 - cards with types, & types give the cards extra benefits etc.
*/