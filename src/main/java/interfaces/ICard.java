package interfaces;

/**
 *
 * @author emmanuel rufasha : W9091718
 */
public interface ICard {
    String getId();
    String getName();
    int getManaCost();
    int getAttack();
    int getHealth();
    void damage(int amount);
    void setAttackStrategy(IAttackStrategy attackStrategy); //used by specific cards that require different attack logic
    void performAttack(IPlayer currentPlayer, IPlayer opponentPlayer);
    String toString();
}
