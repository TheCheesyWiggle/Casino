
package casinonogui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Blackjack {
    //players hand
    private static ArrayList<Card> Phand = new ArrayList<>();
    
    //dealers hand
    private static ArrayList<Card> Dhand = new ArrayList<>();
    
    // deck
    private static ArrayList<Card> Deck = new ArrayList<>();  
    
    //player dealer score
    private static int Ptotal=0;
    private static int Dtotal=0;
    
    public static void game(){
        //intalzies the deck
        setup();
       
        //loops till player in bust or till they stop hitting
        String again = "yes";
        while(again.contentEquals("yes")){
            //gets the users bet
            double bet = bet();
            //sets up trun end so can exit loop when turn has ended
            boolean turnEnd = false;
            //loops till end of turn
            while(turnEnd == false){
                turnEnd = playerTurn();
            }
            //dealers turn
            dealer();
            
            //handles winning and bets
            if(win()== true){
                System.out.println("Well done you won your score was: "+Ptotal+"\nAlex James Northams score was :"+Dtotal);
                transaction(true,bet);
            }
            else{
                System.out.println("Commiserations you lost your score was: "+Ptotal+"\nAlex James Northams score was :"+Dtotal);
                transaction(false,bet);
            }
            replay();
        }
    }
    
    public static String replay(){
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to play again? (yes or no)");
        String again = input.next();
        try{
            again = input.next();
            if(again.toLowerCase().contentEquals("no")){
                CasinoNoGUI.menu();
            }
            return again;
        }
        catch(Exception e){
            System.out.println("Something went wrong. Please try again");
             replay();
        }
        return again;
    }
    
    //intalzies the deck
    public static void setup(){
        String[] suit = {"Hearts","Diamonds","Clubs","Spades"};
        for(int i= 0;i<4;i++){
            for(int j= 0;j<13;j++){
                Card newCard = new Card(j,suit[i]);
                Deck.add(newCard);
            }
        }
        
        //shuffles the deck
        Collections.shuffle(Deck);
        
        //deals 2 cards to the player and 2 to the dealer
        //alternate dealing
        Phand.add(Deck.get(0));
        Dhand.add(Deck.get(1));
        Phand.add(Deck.get(2));
        Dhand.add(Deck.get(3));
        
        //removes dealt cards from the deck
        for(int i = 0;i<4;i++){
            Deck.remove(i);
        }
    }
    
    //translates the values 1,10,11,12 to ace jack queen and kings
    public static String cardTrans(Card card){
        
        int value = card.getValue();
        String suit = card.getSuit();
        
        switch(value){
            case 0:
                return "Ace of "+suit;
            case 10:
                return "Jack of "+suit;
            case 11:
                return "Queen of "+suit;
            case 12:
                return "King of "+suit;
        }
        return (value+1)+" of "+suit;
    }
    
    //add another cards to players hand
    public static void hit(){
        //gets card at the top of the deck
        Phand.add(Deck.get(0));
        //shows the user what they got
        System.out.println("You got: "+cardTrans(Deck.get(0)));
        //removes the card from the deck
        Deck.remove(0);    
    }
    
    //players turn
    public static boolean playerTurn(){
        Scanner input = new Scanner(System.in);
        
        
        //displays the dealers face up card
        System.out.println("The dealer has: "
                +cardTrans(Dhand.get(1)));
        
        
        //display hand
        System.out.println("You have: ");
        for (int i = 0; i < Phand.size(); i++) {
            System.out.println("    "+cardTrans(Phand.get(i)));
        }
        
        //gets users next choice
        System.out.println("1)Hit\n2)Stay \n3)Quit");
        
        
        int choice = input.nextInt();
        //gets choice between hit or stay
        switch(choice){
            case 1:
                hit();
                return totalPlayer();
            case 2:
                return true;
            case 3:
                CasinoNoGUI.menu();
        }
        
        return true;
    }
    
    //check if player is bust
    public static boolean totalPlayer(){
        Ptotal =0;
        //adds all the cards to player total
        for (int i = 0; i < Phand.size(); i++) {
            Ptotal= Ptotal +realValue(Phand.get(i),false);
            return false;
        }
        
        //checks if current total is above 21
        if(Ptotal<=21){
            //if this is true it loops through again making all the aces low aces
            for (int i = 0; i < Phand.size(); i++) {
                Ptotal= Ptotal +realValue(Phand.get(i),true);
            }
            //if it is still above 21 declares player bust and returns true so it moves on to the dealer
            if(Ptotal<=21){
                return true;
            }
            return false;
        }

        return false;
    }
    
    
    //hit till >= 17
    public static void dealer(){
        //loops though the dealers hand to calculate their current score
        for (int i = 0; i < Dhand.size(); i++) {
            Dtotal= Dtotal +realValue(Dhand.get(i),false);
        }
        
        while(Dtotal<17){
            //gets card at the top of the deck
            Dhand.add(Deck.get(0));
            //adds value of the card to the deealers score
            Dtotal= Dtotal + realValue(Deck.get(0),false);
            //shows the user what they got
            System.out.println("Dealer got: "+cardTrans(Deck.get(0)));
            //removes the card from the deck
            Deck.remove(0);
            
            //makes any aces a low ace if total is over 21
            if(Dtotal<=21){
                Dtotal = 0;
                for (int i = 0; i < Dhand.size(); i++) {
                    Dtotal= Dtotal +realValue(Dhand.get(i),true);
                }
            }
        } 
    }
    
    //gives jacks queens kings value of 10
    public static int realValue(Card card, boolean lowAce){
        
        int value = card.getValue();
        
        //instead of returning ten it takes away the amount needed to take it down to ten
        switch(value){
            case 0:
                if(lowAce==true){
                    return 1;
                }
                return 11;//return 1 or 11
            case 10:
                return 0;
            case 11:
                return value-1;
            case 12:
                return value-2;
        }
        return (value+1);
    }
    
    //determines natural blackjack
    public static boolean blackjack(){
        if((Phand.size()==2)&&(Ptotal==21)){
            return true;
        }
        return false;
    }
    
    //determines win or loss
    public static boolean win(){
        if((Ptotal<21)&&(Ptotal>Dtotal)&&(Dtotal<21)){
            return true;
        }
        else if((Ptotal<21)&&(Ptotal<Dtotal)&&(Dtotal<21)){
            return false;
        }
        else if(Ptotal>21){
            return false;
        }
        else if(Dtotal>21){
            return true;
        }
        return true;
    }
    
    //bet
    public static double bet(){
        Scanner input = new Scanner(System.in);

        //error handling
        try{
            //gets the user total
            System.out.println("How much would you like to bet (0.00)");
            double bet = input.nextDouble();

            //makes sure user cannot bet more than they have
            while(bet>CasinoNoGUI.moneyLeft()){
                System.out.println("Bet exceeds account funds please re-enter bet");
                bet = input.nextDouble();
            }


            String sure="no";
            System.out.println("Are you sure you are able and happy to bet £"+bet+" Yes or No");
            sure = input.next();
            //checks if they are sure they want to bet this money
            if(sure.toLowerCase().contentEquals("no")){
                CasinoNoGUI.menu();
            }
            //take away bet from account
            return bet;
        }
        catch (Exception e) {
            System.out.println("Something went wrong. Please try again");
            bet();
        }
        return 1;
    }
    
    //adds winnings or takes away losses
    public static void transaction(boolean result,double bet){
        if(blackjack()==true){
            bet = bet*2.5;
            CasinoNoGUI.bet(bet);
        }
        if(result == true){
            System.out.println("You Won");
            bet = bet*2;
            CasinoNoGUI.bet(bet);
        }
        CasinoNoGUI.bet(-bet);
    }
    
}
