package interfaces;

/**
 *
 * @author emmanuel
 */
public interface IDeckFactory {
    IDeck createNewDeck();
}

// Using an interface for the DeckFactory allows you to define a contract that specifies the behavior for creating decks. This can be useful if you anticipate different implementations of deck creation in the future. For example, you might have multiple classes that implement IDeckFactory to create different types of decks, such as a random deck factory, a pre-defined deck factory, or a custom deck factory.

//By programming to the IDeckFactory interface, rather than a specific implementation class, you promote loose coupling and flexibility in your code. It becomes easier to swap different deck factory implementations without affecting the client code that depends on the IDeckFactory interface.