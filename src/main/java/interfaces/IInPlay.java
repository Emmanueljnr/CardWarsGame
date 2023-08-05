/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author emmanuel
 */
public interface IInPlay {
    ArrayList<ICard> getCards();
    ICard getCard(int index);
    void add(ICard card);
    void remove(ICard card);
    void removeAll(ArrayList<ICard> cards);
    int count();
}
