
package casinonogui;

import java.util.ArrayList;


public class HigherOrLower {
    
    // deck
    private static ArrayList<Card> Deck = new ArrayList<>(); 
    
   public static void game(){
       
   }
   
   
   public static void setup(){
        String[] suit = {"Hearts","Diamonds","Clubs","Spades"};
        for(int i= 0;i<4;i++){
            for(int j= 0;j<14;j++){
                Card newCard = new Card(j,suit[i]);
                Deck.add(newCard);
                System.out.println(newCard);
            }
        }
    }
}
