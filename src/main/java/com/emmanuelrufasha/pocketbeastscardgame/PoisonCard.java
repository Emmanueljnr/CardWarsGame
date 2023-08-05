package com.emmanuelrufasha.pocketbeastscardgame;

import interfaces.IAttackStrategy;

/**
 * @author emmanuel
 */
public class PoisonCard extends Card {
    
    private IAttackStrategy attackStrategy;
    private boolean isPoisoned; //for checking if a player is poisoned or not
    
    //constructor
    public PoisonCard(String id, String name, int manaCost, int attack, int health) {
        super(id, name, manaCost, attack, health);
        this.isPoisoned = true;
        this.attackStrategy = new PoisonStrategy();
    }
    
 
    @Override
    public void setAttackStrategy(IAttackStrategy attackStrategy) {
        // Poison Card doesn't need an attack strategy
        //throw new UnsupportedOperationException("Poison Card doesn't have an attack strategy.");
        this.attackStrategy = attackStrategy;
    }

    @Override
    public void performAttack(Player currentPlayer, Player opponentPlayer) {
        super.performAttack(currentPlayer, opponentPlayer); // Invoke the superclass's performAttack() method
        opponentPlayer.damage(1); // Apply the poison effect by reducing the opponent's health by 1
    }
    
}

/*
 - The setAttackStrategy() method in the PoisonCard class is overridden to throw an UnsupportedOperationException 
   because the PoisonCard doesn't need an attack strategy. This is because the attack behavior of the PoisonCard 
   is hardcoded within the performAttack() method itself.
*/