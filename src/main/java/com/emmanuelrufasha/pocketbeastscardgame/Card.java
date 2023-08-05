package com.emmanuelrufasha.pocketbeastscardgame;

import interfaces.IAttackStrategy;
import interfaces.ICard;
import interfaces.IPlayer;


public class Card implements ICard, Comparable<Card> {

    //strtegy pattern fields
    private IAttackStrategy attackStrategy;
    
    private final String id;
    private final String name;
    private final int manaCost;
    private final int attack;
    private int health;   
    
    //My main/first constructor, initialises all the properties of the card
    public Card(String id, String name, int manaCost, int attack, int health) {
        this.id = id;
        this.name = name;
        this.manaCost = manaCost;
        this.attack = attack;
        this.health = health;
    }
   
    //My second constructor simply calls the first constuctor, passing relevent properties of the card being copied
    public Card(Card card) {
        this(card.id, card.name, card.manaCost, card.attack, card.health);
    }  
    
    // setter method to allow setting the attack strategy for a card
    @Override
    public void setAttackStrategy(IAttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }
    
    // performAttack method to delegate the attack behavior to the strategy
    @Override
    public void performAttack(IPlayer currentPlayer, IPlayer opponentPlayer) {
        if (attackStrategy != null) {
            attackStrategy.performAttack(currentPlayer, opponentPlayer, this);
        }
    }
    
    /*
     Performs an attack using the Attack Strategy.
     If an attack strategy is set, it delegates the attack behavior to the AttackStrategy strategy's  
     performAttack method.
      - @param currentPlayer  : The player who owns the attacking card.
      - @param opponentPlayer : The player who owns the defending card.
    */
    public void performAttack(Player currentPlayer, Player opponentPlayer) {
        if (attackStrategy != null) {
            attackStrategy.performAttack(currentPlayer, opponentPlayer, this);
        }
    }
        
    @Override
    public String getId() {
        return this.id;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public int getManaCost() {
        return this.manaCost;
    }
    
    @Override
    public int getAttack() {
        return this.attack;
    }
    
    @Override
    public int getHealth() {
        return this.health;
    }
    
    @Override
    public void damage(int amount) {
        this.health -= amount;
    }
    
    @Override 
    public String toString() {
    return "Card Name: " + this.name + " (" + this.id + ")\n" +
           "Mana Cost: " + this.manaCost + "\n" +
           "Attack   : " + this.attack + "\n" +
           "Health   : " + this.health;
    } 
    
    
    @Override
    public int compareTo(Card o) {
        return Integer.compare(this.getManaCost(), o.getManaCost());
    }
}


/*

Changes Made to this Class
--------------------------

1. Modified the 2nd Constructor

- Code repetition in both constructors, as they are both 
  initialising the exact same properties, as a result, 
  I modified the second constructor. By doing so...I have 
  eliminated code repetition in the constructors, 
  while still allowing the Card class to be initialized.


2. Modified the toString method

- I wanst a fan of the formatting for the toString method, 
  when playing a game, its important that the information of a card is easy
  to read. As such, this is the modificaion i have made


*/