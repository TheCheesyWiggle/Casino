
package casinonogui;

import java.util.Scanner;


public class Funds {
    
    public static void menu(){
        Scanner input = new Scanner(System.in);
        //error handling
        try{
            System.out.println(" 1)Add funds \n 2)Withdraw funds \n 3)View funds \n 4)Quit to main menu \n 5)Add funds \n 6)Log out");
            int ans = input.nextInt();
            switch(ans){
                case 1:
                    //add funds to the account
                    add();
                    break;
                case 2:
                    //withdraw fund from the account
                    withdraw();
                    break;
                case 3:
                    //view funds in account
                    view();
                    break;
                case 4:
                    CasinoNoGUI.menu();
            }
        }
        catch(Exception e){
            System.out.println("Something went wrong. Please try again");
            menu();
        }
    }
    
    //inputs with duoble data type
    private static double doubleInput(){
        Scanner input = new Scanner(System.in);
        return input.nextDouble();
    }
    
    //adds funds
    private static void add() {
        try{
            System.out.println("How much would you like to add to your account £(0.00)");
            double add = doubleInput();
            CasinoNoGUI.bet(add);
        }
        catch(Exception e){
            System.out.println("Something went wrong. Please try again");
            add();
        }
    }

    //withdraws funds
    private static void withdraw() {
        try{
            System.out.println("How much would you like to withdraw from your account £(0.00)");
            double funds = doubleInput();
            CasinoNoGUI.bet(-funds);
            }
        catch(Exception e){
            System.out.println("Something went wrong. Please try again");
            withdraw();
        }
    }
    
    //views funds
    private static void view() {
        System.out.println(CasinoNoGUI.moneyLeft());
        menu();
    }
}
