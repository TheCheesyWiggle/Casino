
package casinonogui;

import java.util.Random;
import java.util.Scanner;


public class Roulette {
    
    private static String[] mat = new String[37];
    private static int roll;
    
    //main method runs the game
    public static void game(){
        boolean again = true;
        setup();
        while(again ==true){
            Random rand = new Random(37);
            //passes roll through menu too methods that check the bet
            roll = rand.nextInt();
            menu();
        }
        
    }
    
    //sets up the roulette wheel
    public static void setup(){
        //sets up the roullette wheel assigning clolours to numbers
        mat[0] = "green";
        for(int i= 1;i<11;i++){
            int odd =i%2;
            switch(odd){
                case 0:
                    mat[i]= "black";
                    System.out.println(i+" black");
                    break;
                case 1:
                    mat[i]= "red";
                    System.out.println(i+" red");
                    break;
            }
        }
        for(int i= 11;i<19;i++){
            int odd =i%2;
            switch(odd){
                case 0:
                    mat[i]= "red";
                    System.out.println(i+" red");
                    break;
                case 1:
                    mat[i]= "black";
                    System.out.println(i+" black");
                    break;
            }
        }
        for(int i= 19;i<29;i++){
            int odd =i%2;
            switch(odd){
                case 0:
                    mat[i]= "black";
                    System.out.println(i+" black");
                    break;
                case 1:
                    mat[i]= "red";
                    System.out.println(i+" red");
                    break;
            }
        }
        for(int i= 29;i<37;i++){
            int odd =i%2;
            switch(odd){
                case 0:
                    mat[i]= "red";
                    System.out.println(i+" red");
                    break;
                case 1:
                    mat[i]= "black";
                    System.out.println(i+" black");
                    break;
            }
        }

    }
    
    //gets integer input
    public static int intInput(){
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
    
    //runs the menu
    public static void menu(){
        
        System.out.println("Please choose your option");
        System.out.println("1) Individual number bet"
            + "\n2) Dozens"
                + "\n3) Odd or Even"
                    + "\n4) Black or Red"
                        + "\n5) 1-18 or 19-36"
                            + "\n6) 1st column or 2nd column or 3rd column"
                                + "\n7) Quit");

        //error handling
        try{
            int choice = intInput();
            
            switch(choice){
            case 1:
                win(indivdual(),36);
                break;
            case 2:
                win(dozen(),3);
                break;
            case 3:
                win(oddOrEven(),2);
                break;
            case 4:
                win(colour(),2);
                break;
            case 5:
                win(half(),2);
                break;
            case 6:
                win(column(),2);
                break;
            case 7:
                CasinoNoGUI.menu();
                break;
            }
        }
        catch (Exception e) {
            System.out.println("Something went wrong. Please try again");
            menu();
        }
    }
    
    //checks if roll is equal to guess
    public static boolean indivdual(){
        System.out.println("Enter your number between 0-36");
        //error handling
        try{
            int choice = intInput();
            
            if(choice==roll){
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e) {
            System.out.println("Something went wrong. Please try again");
            indivdual();
        }
        return false;
    }
    
    //which dozen is it in
    public static boolean dozen(){
        System.out.println("1) 1st 2) 2nd 3)3rd 4) Back to menu");
        //error handling
        try{
            int choice = intInput();
            
            switch (choice){
                case 1:
                    if((roll>1)&&(roll<12)){
                        return true;
                    }
                case 2:
                    if((roll>12)&&(roll<24)){
                        return true;
                    }
                case 3:
                    if((roll>24)&&(roll<36)){
                        return true;
                    }
                case 4:
                    menu();
            }
        } 
        catch (Exception e) {
            System.out.println("Something went wrong. Please try again");
            dozen();
        }
        return false;
    }
    
    //checks if it odd or even
    public static boolean oddOrEven(){
        System.out.println("1) Odd 2) Even 3) Back to menu");
        //error handling
        try{
            int choice = intInput();
            
            switch(choice){
            //even
            case 1:
                if((roll%2) == 0){
                    return true;
                }
            //odd
            case 2:
                if((roll%2) == 1){
                    return true;
                }
            case 3:
                menu();
            }
        }
        catch (Exception e) {
            System.out.println("Something went wrong. Please try again");
            oddOrEven();
        }
        return false;
    }
    
    //checks colour
    public static boolean colour(){
        System.out.println("1) Red 2) Black 3) Back to menu");
        //error handling
        try{
            int choice = intInput();
            
            switch(choice){
            //red
            case 1:
                if(mat[roll].equals("red")){
                    return true;
                }
            //black
            case 2:
                if(mat[roll].equals("black")){
                    return true;
                }
            case 3:
                menu();
            }
        }
        catch (Exception e) {
            System.out.println("Something went wrong. Please try again");
            colour();
        }
        return false;
    }
    
    //checks which half
    public static boolean half(){
        System.out.println("1) 1-18 2) 19-36 3) Back to menu");
        //error handling
        try{
            int choice = intInput();
            
            switch(choice){
            case 1:
                if(roll<19){
                    return true;
                }
            case 2:
                if(roll>18){
                    return true;
                }
            case 3:
                menu();
        }
        }
        catch (Exception e) {
            System.out.println("Something went wrong. Please try again");
            half();
        }
        return false;
    }
    
    //which column its in
    public static boolean column(){
        System.out.println("1) 1st  2) 2nd 3) 3rd 4) Back to menu");
        //error handling
        try{
            int choice = intInput();
            
            switch (choice){
            case 1:
                //remainder 1 column 1
                if((roll%3)==1){
                    return true;
                }
            case 2:
                //remainder 2 column 2
                if((roll%3)==2){
                    return true;
                }
            case 3:
                //remainder 0 column 3
                if((roll%3)==0){
                    return true;
                }
            case 4:
                menu();
            }
        }
        catch (Exception e) {
            System.out.println("Something went wrong. Please try again");
            column();
        }
        return false;
    }
    
    //bet
    public static double bet(){
        Scanner input = new Scanner(System.in);

        //error handling
        try{
            //gets the user total
            System.out.println("How much would you like to bet £(0.00)");
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
                menu();
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
    
    //determnines win or loss
    //odds 1:1 = 2  2:1 = 3  35:1 = 36
    public static void win(boolean result,int odds){
        double bet=bet();
        if(result == true){
            System.out.println("You Won");
            bet = bet*odds;
            CasinoNoGUI.bet(bet);
        }
        CasinoNoGUI.bet(-bet);
    }
    
    //double check everything works
}
