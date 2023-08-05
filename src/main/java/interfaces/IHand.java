package interfaces;

import java.util.ArrayList;

/**
 *
 * @author emmanuel
 */
public interface IHand {
    ArrayList<ICard> getCards();
    void modifyCard(ICard card, boolean add);
    void removeAll(ArrayList<ICard> cards);
    int count();
    void sort();
    String toString();
}
