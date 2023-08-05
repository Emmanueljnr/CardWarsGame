/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emmanuel
 */
public interface IGraveyard {
    void add(ICard card);
    void addAll(List<ICard> cardList);
    int count();
    ArrayList<ICard> getCards();   
}
