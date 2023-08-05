package com.emmanuelrufasha.pocketbeastscardgame;

import interfaces.IAttackStrategy;
import interfaces.ICard;
import interfaces.IPlayer;

/**
 *
 * @author emmanuel
 */
public class PoisonStrategy implements IAttackStrategy {

//    @Override
//    public void performAttack(IPlayer currentPlayer, IPlayer opponentPlayer, ICard attackingCard) {
//        // Reduce the opponent's health by 1 when the poisoned player plays a card
//        opponentPlayer.damage(1);
//    }

    @Override
    public void performAttack(IPlayer currentPlayer, IPlayer opponentPlayer, ICard attackingCard) {
        // Reduce the opponent's health by 1 when the poisoned player plays a card
        if (attackingCard instanceof PoisonCard) {
            opponentPlayer.damage(1);
        }
    }
    
    @Override
    public void performAttack(IPlayer currentPlayer, IPlayer opponentPlayer, ICard attackingCard, ICard targetCard) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
}
