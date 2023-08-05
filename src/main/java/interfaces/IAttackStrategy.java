package interfaces;

/**
 * @author emmanuel rufasha : W9091718
 */
public interface IAttackStrategy {
    void performAttack(IPlayer currentPlayer, IPlayer opponentPlayer, ICard attackingCard); //For Attacking Player Directly
    void performAttack(IPlayer currentPlayer, IPlayer opponentPlayer, ICard attackingCard, ICard targetCard); //For Attacking a Card
}


/*
IAttackStrategy. defines the method performAttack(Player currentPlayer, 
Player opponentPlayer, Card attackingCard) that takes the current player, 
opponent player, and the attacking card as parameters...
*/