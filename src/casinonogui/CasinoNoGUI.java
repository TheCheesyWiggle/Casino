
package casinonogui;

import java.util.ArrayList;
import java.util.Scanner;


public class CasinoNoGUI {
    //registered accounts
    protected static ArrayList<Account> Accountlist = new ArrayList<>();
    
    //username and password mad public so they can be used in the gui
    protected static String username;
    protected static String password;
    
    
    public static void main(String[] args) {
        Accountlist = fileHandling.readFile();
        entry();
    }
    
    //  login/signup
    public static void entry(){
        //login screen
        Scanner input = new Scanner(System.in);
        System.out.println("1)Login \n2)Sign up \n3)Quit");
        int ans = input.nextInt();
        switch(ans){
            case 1:
                if(LoginCode.login()==true){
                    menu();
                }
                else{
                    entry();
                }
                break;
            case 2:
                LoginCode.signup();
                menu();
                break;
            case 3:
                System.exit(0);
                break;
                
        }
    }
    
    //directs you to the right game
    public static void menu(){
        Scanner input = new Scanner(System.in);
        System.out.println("What do you want to play "+username+"? \n 1)Yahtzee \n 2)BlackJack \n 3)Roulette \n 4)Higher or lower \n 5)Add funds \n 6)Log out");
        int ans = input.nextInt();
        switch(ans){
            case 1:
                //yahtzee class
                Yahtzee.game();
                break;
            case 2:
                //blackjack class
                Blackjack.game();
                break;
            case 3:
                //roulette class
                Roulette.game();
                break;
            case 4:
                //HorL class
                HigherOrLower.game();
                break;
            case 5:
                if(LoginCode.login()==true){
                    Funds.menu();
                }
                menu();
                //send email verfication(also means add email to account object))
            case 6:
                entry();
                break;
        }
    }
    
    //get index of account
    public static double moneyLeft(){
        int index = LoginCode.UsernameSearch();
        return Accountlist.get(index).getMoney();
    }
    
    public static void bet(double bet){
        int index = LoginCode.UsernameSearch();
        
        System.out.println(moneyLeft());
        Accountlist.get(index).setMoney(moneyLeft()+bet);
        System.out.println(moneyLeft());
    }
    //add:
        //try catches
        //email verification 
}
