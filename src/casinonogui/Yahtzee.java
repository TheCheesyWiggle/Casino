/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinonogui;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Finn
 */
public class Yahtzee {
    
    private static int[] dice = new int[5];
    private static int total = 0;
    private static int AItotal =0;
    
    public static void game(){
        
        String again = "yes";
        while(again.contentEquals("yes")){
            double bet = bet();
            for(int i =0;i<13;i++){
                playerTurn();
                opponent();
            }
            if(total>AItotal){
                System.out.println("Well done you won your score was: "+total+"\nAlex James Northams score was :"+AItotal);
                win(true,bet);
            }
            else{
                System.out.println("Commiserations you lost your score was: "+total+"\nAlex James Northams score was :"+AItotal);
                win(false,bet);
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
    
    public static void playerTurn() {
        int turn = 1;
        for(int i =0; i<5 ; i++){
            dice[i] = diceRoll();
        }
        System.out.println(format());
        while (turn <3){
            keepDice();
            System.out.println(format());
            turn++;
        }
        ScoreSheet();
    }
    
    public static int diceRoll(){
        Random rand = new Random();
        return rand.nextInt(5)+1;
    }
    
    public static void keepDice(){
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter the letter of the dice you want to change splitting them with a comma or type none");
        String numbers = input.next().toLowerCase();
        
        String[] die = numbers.split(",");
        
        for(int i =0;i< die.length;i++){
            //converts letters to numbers so it can tell the other array which iones to re roll
            switch(die[i]){
                case "a":
                    die[i]="1";
                    break;
                case "b":
                    die[i]="2";
                    break;
                case "c":
                    die[i]="3";
                    break;
                case "d":
                    die[i]="4";
                    break;
                case "e":
                    die[i]="5";
                    break;
                case "none":
                    die[i]="NA";
                    break;
            }
            if(!die[i].equals("NA")){
                dice[Integer.parseInt(die[i])-1] = diceRoll();
            }
        }
  
    }
    
    public static String format(){
        return "dice A:"+dice[0]+" dice B:"+dice[1]+" dice C:"+dice[2]+" dice D:"+dice[3]+" dice E:"+dice[4];
    }
    
    public static void ScoreSheet(){
        Scanner input = new Scanner(System.in);
        
        // 1     2    3      4     5     6       7       8       9
        //ones twos threes fours fives sixes 3ofakind 4ofakind chance
        int[] varPoints = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
        //   1             2             3         4         5
        //fullhouse  low straight  high straight  yahtzee  bonus
        boolean[] setPoints = new boolean[5];
        
        //scoresheet display
        System.out.println("Please choose your section");
        System.out.println("1)Ones:   "+varPoints[0]+"      7)3 of a kind: "+varPoints[6]+""
                + "\n2)Twos:   "+varPoints[1]+"      8)4 of a kind: "+varPoints[7]+""
                        + "\n3)Threes: "+varPoints[2]+"      9)Fullhouse: "+setPoints[0]+""
                                + "\n4)Fours:  "+varPoints[3]+"      10)Low Straight: "+setPoints[1]+""
                                        + "\n5)Fives:  "+varPoints[4]+"      11)High Straight: "+setPoints[2]+""
                                                + "\n6)Sixes:  "+varPoints[5]+"      12)Yahtzee: "+setPoints[3]+""
                                                        + "\n  Bonus:  "+setPoints[4]+"  13)Chance: "+varPoints[8]);
        System.out.println("Total: "+total);
        System.out.println("14) Quit");
        //gets input and calculates score for the section
        try{
            int turnTotal = 0;
            int section = input.nextInt();
            switch(section){
                case 1:
                    if(varPoints[0]==-1){
                        turnTotal=numScore(section);
                        varPoints[0]=turnTotal;
                        total = total+turnTotal;
                    }
                    else{
                        System.out.println("Already selected this in a previous turn");
                        ScoreSheet();
                    }
                    break;
                case 2:
                    if(varPoints[1]==-1){
                        turnTotal=numScore(section);
                        varPoints[1]=turnTotal;
                        total = total+turnTotal;
                    }
                    else{
                        System.out.println("Already selected this in a previous turn");
                        ScoreSheet();
                    }
                    break;
                case 3:
                    if(varPoints[2]==-1){
                        turnTotal=numScore(section);
                        varPoints[2]=turnTotal;
                        total = total+turnTotal;
                    }
                    else{
                        System.out.println("Already selected this in a previous turn");
                        ScoreSheet();
                    }
                    break;
                case 4:
                    if(varPoints[3]==-1){
                        turnTotal=numScore(section);
                        varPoints[3]=turnTotal;
                        total = total+turnTotal;
                    }
                    else{
                        System.out.println("Already selected this in a previous turn");
                        ScoreSheet();
                    }
                    break;
                case 5:
                    if(varPoints[4]==-1){
                        turnTotal=numScore(section);
                        varPoints[4]=turnTotal;
                        total = total+turnTotal;
                    }
                    else{
                        System.out.println("Already selected this in a previous turn");
                        ScoreSheet();
                    }
                    break;
                case 6:
                    if(varPoints[5]==-1){
                        turnTotal=numScore(section);
                        varPoints[5]=turnTotal;
                        total = total+turnTotal;
                    }
                    else{
                        System.out.println("Already selected this in a previous turn");
                        ScoreSheet();
                    }
                    break;
                case 7:
                    if(varPoints[6]==-1){
                        turnTotal=ofKind(3);
                        varPoints[6]=turnTotal;
                        total = total+turnTotal;
                    }
                    else{
                        System.out.println("Already selected this in a previous turn");
                        ScoreSheet();
                    }
                    break;
                case 8:
                    if(varPoints[7]==-1){
                        turnTotal=ofKind(4);
                        varPoints[7]=turnTotal;
                        total = total+turnTotal;
                    }
                    else{
                        System.out.println("Already selected this in a previous turn");
                        ScoreSheet();
                    }
                    break;
                case 9:
                    if(!(setPoints[0]== false)||(setPoints[0]== true)){
                        setPoints[0]=fullhouse();
                        if(setPoints[0]==true){
                            total = total+25;
                        }
                    }
                    else{
                        System.out.println("Already selected this in a previous turn");
                        ScoreSheet();
                    }
                    break;
                case 10:
                    if(!(setPoints[1]== false)||(setPoints[1]== true)){
                        setPoints[1]=straight("l");
                        if(setPoints[1]==true){
                            total = total+30;
                        }
                    }
                    else{
                        System.out.println("Already selected this in a previous turn");
                        ScoreSheet();
                    }
                    break;
                case 11:
                    if(!(setPoints[2]== false)||(setPoints[2]== true)){
                        setPoints[2]=straight("h");
                        if(setPoints[2]==true){
                            total = total+40;
                        }
                    }
                    else{
                        System.out.println("Already selected this in a previous turn");
                        ScoreSheet();
                    }
                    break;
                case 12:
                    if(!(setPoints[3]== false)||(setPoints[3]== true)){
                        setPoints[3]=yahtzee();
                        if(setPoints[3]==true){
                            total = total+50;
                        }
                    }
                    else{
                        System.out.println("Already selected this in a previous turn");
                        ScoreSheet();
                    }
                    break;
                case 13:
                    if(varPoints[8]==-1){
                        turnTotal=add();
                        varPoints[8]=turnTotal;
                        total = total+turnTotal;
                    }
                    else{
                        System.out.println("Already selected this in a previous turn");
                        ScoreSheet();
                    }
                    break;
                case 14:
                    CasinoNoGUI.menu();
                    break;
            }
            if((varPoints[0]+varPoints[1]+varPoints[2]+varPoints[3]+varPoints[4]+varPoints[5])>=65){
                    total= total+35;
                    setPoints[4]=true;
                }
        }
        catch (Exception e) {
            System.out.println("Something went wrong. Please try again");
            ScoreSheet();
        }
    }
    
    //adds value of all the dice together
    public static int add(){
        return dice[0]+dice[1]+dice[2]+dice[3]+dice[4];
    }
    
    //calculates the score for the 6 first addition based sections
    public static int numScore(int num){
        int sum = 0;
        for(int i =0; i<5 ; i++){
            if(dice[i]==num){
                sum = sum+num;
            }
        }
        return sum;
    }
    
    //calculates yahtzee
    public static boolean yahtzee(){
        if(ofKind(5)>0){
            return true;
        }
        return false;
    }
    
    //calculates 3 and 4 of a kind
    public static int ofKind(int kind){
        int count = 0;
        //cycles through all numbers on a dice
        for(int i =0;i<6;i++){
            //cycles through the dice
            for(int j =0; j<5 ; j++){
                //checks if dice is equal to a number between 1-6, number remains constant until cycled through dice
                if(dice[j]==i){
                    //counts how many dice are equal to the number specified above
                    count++;
                    //checks if the amount of numbers is high enough to allow the score
                    if(count<=kind){
                        //added to total in add()
                        return add();
                    }
                }
            }
        }
        return 0;
    }
    
    //clculates fullhouse
    public static boolean fullhouse(){
        int count = 0;
        int spareDie[]={0,0};
        //cycles through all numbers on a dice
        for(int i =0;i<6;i++){
            //cycles through the dice
            for(int j =0; j<5 ; j++){
                //checks if dice is equal to a number between 1-6, number remains constant until cycled through dice
                if(dice[j]==i){
                    //counts how many dice are equal to the number specified above
                    count++;
                    //checks if the last two numbers are the same
                    if(count==3){
                        if(spareDie[0]==spareDie[1]){
                            return true;
                        }
                    }
                }
                else{
                    //check wether the 1st spare die has been written too
                    if(spareDie[0]==0){
                        //overwrites second spare die regardless fullhouse => 3 the same and 2 the same
                        spareDie[1]=dice[j];
                    }
                    spareDie[0]=dice[j];
                }
            }
        }
        return false;
    }
    
    //calculates both straights
    public static boolean straight(String type){
        //sorts
        Arrays.sort(dice); 
        //check which type of straight it is
        //check the striaght but calculating the difference bettween two positions in th aarray
        switch(type){
            case "l":
                if((dice[4]-dice[1])==3){
                    return true;
                }
                break;
            case "h":
                if((dice[5]-dice[1])==4){
                    return true;
                }
                break;
        } 
        return true;
    }
    
    //virtual oppponent
    public static void opponent(){
        for(int i =0; i<5 ; i++){
            dice[i] = diceRoll();
        }

        AIscoreSheet(1);

    }
    
    //AI score sheet
    public static void AIscoreSheet(int section){
        // 1     2    3      4     5     6       7       8       9
        //ones twos threes fours fives sixes 3ofakind 4ofakind chance
        int[] varPoints = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
        //   1             2             3         4         5
        //fullhouse  low straight  high straight  yahtzee  bonus
        boolean[] setPoints = new boolean[5];
            switch(section){
            case 1:
                if(!(setPoints[3]== false)||(setPoints[3]== true)){
                    AItotal = AItotal+50;
                    setPoints[3]= yahtzee();
                }
                else{
                    AIscoreSheet(section+1);
                }
                break;
            case 2:
                if(!(setPoints[2]== false)||(setPoints[2]== true)){
                    AItotal = AItotal+40;
                    setPoints[2]= straight("h");
                }
                else{
                    AIscoreSheet(section+1);
                }
                break;
            case 3:
                if(!(setPoints[1]== false)||(setPoints[1]== true)){
                    AItotal = AItotal+30;
                    setPoints[1]= straight("l");
                }
                else{
                    AIscoreSheet(section+1);
                }
                break;
            case 4:
                if(!(setPoints[0]== false)||(setPoints[0]== true)){
                    AItotal = AItotal+25;
                    setPoints[0]= fullhouse();
                }
                else{
                    AIscoreSheet(section+1);
                }
                break;
            case 5:
                if(varPoints[7]==-1){
                    AItotal=AItotal+ofKind(4);
                    varPoints[7]=AItotal;
                }
                else{
                    AIscoreSheet(section+1);
                }
                break;
            case 6:
                if(varPoints[6]==-1){
                    AItotal=AItotal+ofKind(3);
                    varPoints[6]=AItotal;
                }
                else{
                    AIscoreSheet(section+1);
                }
                break;
            case 7:
                if(varPoints[5]==-1){
                    AItotal=AItotal+numScore(6);
                    varPoints[5]=AItotal;
                }
                else{
                    AIscoreSheet(section+1);
                }
                break;
            case 8:
                if(varPoints[4]==-1){
                    AItotal=AItotal+numScore(5);
                    varPoints[4]=AItotal;
                }
                else{
                    AIscoreSheet(section+1);
                }
                break;
            case 9:
                if(varPoints[3]==-1){
                    AItotal=AItotal+numScore(4);
                    varPoints[3]=AItotal;
                }
                else{
                    AIscoreSheet(section+1);
                }
                break;
            case 10:
                if(varPoints[2]==-1){
                    AItotal=AItotal+numScore(3);
                    varPoints[2]=AItotal;
                }
                else{
                    AIscoreSheet(section+1);
                }
                break;
            case 11:
                if(varPoints[1]==-1){
                    AItotal=AItotal+numScore(2);
                    varPoints[1]=AItotal;
                }
                else{
                    AIscoreSheet(section+1);
                }
                break;
            case 12:
                if(varPoints[0]==-1){
                    AItotal=AItotal+numScore(1);
                    varPoints[0]=AItotal;;
                }
                else{
                    AIscoreSheet(section+1);
                }
                break;
            case 13:
                if(varPoints[8]==-1){
                    AItotal=AItotal+add();
                    varPoints[8]=AItotal;;
                }
                break;
            }
            if((varPoints[0]+varPoints[1]+varPoints[2]+varPoints[3]+varPoints[4]+varPoints[5])>=65){
                total= total+35;
                setPoints[4]=true;
            }
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
                game();
            }

            return bet;
        }
        catch (Exception e) {
            System.out.println("Something went wrong. Please try again");
            bet();
        }
        return 0;
    }
    
    //determnines win or loss
    public static void win(boolean result,double bet){
        if(result == true){
            System.out.println("You Won");
            bet = bet*2;
            CasinoNoGUI.bet(bet);
        }
        CasinoNoGUI.bet(-bet);
    }

        //double check efficiency
}
