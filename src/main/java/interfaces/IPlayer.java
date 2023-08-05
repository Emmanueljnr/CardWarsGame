package interfaces;

/**
 *
 * @author emmanuel
 */
public interface IPlayer {
    String getName();
    int getManaAvailable();
    int getHealth();
    IDeck getDeck();
    IHand getHand();
    IInPlay getInPlay();
    IGraveyard getGraveyard();
    void newGame();
    void addMana();
    void useMana(int amount);
    void drawCard();
    Boolean damage(int amount);
    void applyPoisonEffect();
    String toString();

}
