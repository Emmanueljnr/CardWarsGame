package interfaces;

/**
 *
 * @author emmanuel
 */
public interface IPlayerObserver {
    void onHealthChange(String playerName, int newHealth);
    void addObserver(IPlayerObserver observer);
    void removeObserver(IPlayerObserver observer);
}


/*
 Any class that wants to be notified when a player's health changes can implement the 
 IPlayerObserver interface and receive the onHealthChange callback.
*/