package com.emmanuelrufasha.pocketbeastscardgame;

import interfaces.ICard;
import interfaces.IDeck;
import interfaces.IGraveyard;
import interfaces.IHand;
import interfaces.IInPlay;
import interfaces.IPlayer;
import interfaces.IPlayerObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player implements IPlayer, IPlayerObserver{
    
    private final int MAX_MANA = 9;
    
    private final String name;
    
    private int manaAvailable;
    private int manaTicker;
    private int health;
    
    private final IDeck deck;
    private final IHand hand;
    private final IInPlay inPlay;
    private final IGraveyard graveyard;
    private final List<IPlayerObserver> observers; //List of IPlayerObserver to keep track of the observers:

    public Player(String name, IDeck deck) {
        this.name = name;
        this.manaAvailable = 0;
        this.manaTicker = 0;
        this.health = 15;
        this.deck = deck;
        this.hand = new Hand();
        this.inPlay = new InPlay();
        this.graveyard = new Graveyard();
        this.observers = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getManaAvailable() {
        return this.manaAvailable;
    }
    
    @Override
    public int getHealth() {
        return this.health;
    }
    
    @Override
    public IDeck getDeck() {
        return this.deck;
    }

    @Override    
    public IHand getHand() {
        return this.hand;
    }

        @Override
    public IInPlay getInPlay() {
        return this.inPlay;
    }

    @Override    
    public IGraveyard getGraveyard() {
        return this.graveyard;
    }
    
    @Override    
    public void newGame() {
        this.deck.shuffle();
        for (int i = 0; i < 4; i++) {
            Optional<ICard> optionalCard = this.deck.draw();
            if (optionalCard.isPresent()) {
                ICard drawnCard = optionalCard.get();
                this.hand.modifyCard(drawnCard, true); // Add the drawn card to the hand
            } else {
                // Handle the case when no card is drawn
                // Here, I'll simply print an error message:
                System.out.println("No card drawn from the deck.");
            }
        }
    }

    @Override
    public void addMana() {
        if (this.manaTicker < this.MAX_MANA) {
            this.manaTicker++;
        }
        this.manaAvailable = manaTicker;
    }
    
     @Override   
    public void useMana(int amount) {
        this.manaAvailable -= amount;
    }
    
      @Override
    public void drawCard() {
        Optional<ICard> optionalCard = this.deck.draw();
        if (optionalCard.isPresent()) {
            ICard drawnCard = optionalCard.get();
            this.hand.modifyCard(drawnCard, true); // Add the drawn card to the hand
        } else {
            // Handle the case when no card is drawn
            // I'll simply print an error message:
            System.out.println("No card drawn from the deck.");
        }
    }    

    @Override
    public Boolean damage(int amount) {
        this.health -= amount;
        //Notify Observers when a PLayer's health changes
        for(IPlayerObserver observer : this.observers) {
            observer.onHealthChange(this.name, this.health);
        }
        return this.health <= 0;
    }
       
    @Override
    public void applyPoisonEffect() {
        System.out.println(name + " is now poisoned!");
        // Reduce player's health by 1
        health -= 1;
        System.out.println(getName() + " is poisoned. Health reduced to " + health);   
    }    
    
    @Override
    public void onHealthChange(String playerName, int newHealth) {
        //Annouce the other player's health decrease/increase      
        if (newHealth > health) {
            System.out.println(playerName + "'s health has increased to " + newHealth);
        } else {
            System.out.println(playerName + "'s health has decreased to " + newHealth);
        }
    }
    
    @Override
    public void addObserver(IPlayerObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(IPlayerObserver observer) {
        this.observers.remove(observer);
    }

    // Helper method to get the list of observers
    public List<IPlayerObserver> getObservers() {
        return observers;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-9s HEALTH/%-5d MANA/%d\n", this.name, this.health, this.manaAvailable));

        for (int i=0; i<this.inPlay.count()+2; i++) {
            sb.append("+-------+ ");
        }
        sb.append("\n");
        
        for (int i=0; i<2; i++) {
            sb.append("|       | ");
        }
        for (int i=0; i<this.inPlay.count(); i++) {
            sb.append(String.format("|%7d| ", this.inPlay.getCard(i).getManaCost()));
        }
        sb.append("\n");
        
        sb.append("| DECK  | ");
        sb.append("| GRAVE | ");
        for (int i=0; i<this.inPlay.count(); i++) {
            sb.append(String.format("|  %-5s| ", this.inPlay.getCard(i).getId()));
        }
        sb.append("\n");
        
        sb.append(String.format("| %-6d| ", this.deck.count()));
        sb.append(String.format("| %-6d| ", this.graveyard.count()));
        for (int i=0; i<this.inPlay.count(); i++) {
            sb.append("|       | ");
        }
        sb.append("\n");
        
        for (int i=0; i<2; i++) {
            sb.append("|       | ");
        }
        for (int i=0; i<this.inPlay.count(); i++) {
            sb.append(String.format("|%-2d %4d| ", this.inPlay.getCard(i).getAttack(), this.inPlay.getCard(i).getHealth()));
        }
        sb.append("\n");
        
        for (int i=0; i<this.inPlay.count()+2; i++) {
            sb.append("+-------+ ");
        }
        sb.append("\n");
        sb.append(String.format("%d card(s) in hand.\n", this.hand.count()));
        sb.append("\n");
        
        sb.append(this.hand.toString());
        
        return sb.toString();
    }
}
