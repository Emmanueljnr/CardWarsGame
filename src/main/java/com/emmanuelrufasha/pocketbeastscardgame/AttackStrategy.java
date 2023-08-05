package com.emmanuelrufasha.pocketbeastscardgame;

import interfaces.IAttackStrategy;
import interfaces.ICard;
import interfaces.IPlayer;

/**
 * @author emmanuel rufasha : W9091718
 */
public class AttackStrategy implements IAttackStrategy { 
    @Override
    //Attack a Player directly
    public void performAttack(IPlayer currentPlayer, IPlayer opponentPlayer, ICard attackingCard) {
         
        if (attackingCard.getId().equals("PC")) { // Assuming "PC" is the ID of the Poison Card
            // Attacking card is the Poison Card
            IAttackStrategy poisonStrategy = new PoisonStrategy();
            poisonStrategy.performAttack(currentPlayer, opponentPlayer, attackingCard);
            System.out.println(opponentPlayer.getName() + " is now poisoned!"); // Announcement of poisoning effect
        } else {
            // Attacking card is not the Poison Card, perform regular attack
            if (opponentPlayer.damage(attackingCard.getAttack())) {
                System.out.println(opponentPlayer.getName() + " has been defeated.");
            } else {
                System.out.println(opponentPlayer.getName() + " is now at " + opponentPlayer.getHealth() + " health.");
            }
        }
    }
    
    //Attack a Card
    @Override
    public void performAttack(IPlayer currentPlayer, IPlayer opponentPlayer, ICard attackingCard, ICard targetCard) {
        attackingCard.damage(targetCard.getAttack());
        targetCard.damage(attackingCard.getAttack());

        // Check if attackingCard's health is <= 0
        if (attackingCard.getHealth() <= 0) {
            // Remove attacking Player's card from inPlay and add it to the Graveyard
            currentPlayer.getInPlay().remove(attackingCard); 
            currentPlayer.getGraveyard().add(attackingCard);
        }

        // Check if targetCard's health is <= 0
        if (targetCard.getHealth() <= 0) {
            // Remove other PLayer's card from inPlay and add it to the Graveyard
            opponentPlayer.getInPlay().remove(targetCard); 
            opponentPlayer.getGraveyard().add(targetCard);
        }
    }
} 
    
//    @Override
//    public void performAttack(Player currentPlayer, Player opponentPlayer, Card attackingCard) {
//        // My logic for a direct attack
//        // Deal damage to the opponent player's beast
//    } 


/*
 AttackStrategy implements the IAttackStrategy interface. In the performAttack method, 
 I'm implementing the logic for a direct attack where the attacking card deals damage 
 directly to the opponent player's beast.

 - I decided to use method overloading in order to give the Attack Strategy the ability
   to perform two different functions, the first being to attack a player directly
   and the second being to attack a card. the first method takes in 3 parameters
   while the second takes in 4.

-  In the logic for my second performAttack method, the attackingCard and targetCard 
   damage each other based on their respective attack values. If the health 
   of either card becomes zero or less, it is removed from the player's 
   in-play cards list and added to their graveyard.
*/