package interfaces;

import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author emmanuel
 */
public interface IDeck {
    int count();
    Optional<ICard> draw();
    void shuffle();
    ArrayList<ICard> getCards();
}
