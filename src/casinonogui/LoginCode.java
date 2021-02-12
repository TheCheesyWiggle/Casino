
package casinonogui;

import java.util.Scanner;




public class LoginCode extends CasinoNoGUI{
    
    public static boolean login(){  
        Scanner input = new Scanner(System.in);
        //gets username and password
        System.out.println("Please enter username");
        username = input.next();
        System.out.println("Please enter password");
        password = input.next();
        
        System.out.println(username);
        System.out.println(password);
        int index = UsernameSearch();
        if(!(index == -1)){
            String realPassword = Accountlist.get(index).getPassword();

            if(password.equals(realPassword)){
                System.out.println("Welcome");
                return true;
            }
        }
            
        System.out.println("Incorrect Username or Password");
        
        return false;
    }
    
    public static void signup(){
        Scanner input = new Scanner(System.in);
        
        System.out.println("Please enter username");
        username = input.next();
        //makes sure no 2 the same usernames are used
        while(!(UsernameSearch()==-1)){
            System.out.println("Username taken please enter new username");
            username = input.next();
        }
        System.out.println("Please enter password");
        password = input.next();
        
        //adds funds to account
        System.out.println("How many £s do you want to desposit as your starting funds \nMinimum funds is £50 \nEnter amount :");
        double money =  input.nextDouble();
        while(money<50){
            System.out.println("Sorry minimum funds is £50 \nPlease enter new amount: ");
            money =  input.nextDouble();
        }
        //registers account
        Account myAccount = new Account(username,password,money);
        Accountlist.add(myAccount);
        fileHandling.writeFile(Accountlist);
    }
    
    public static int UsernameSearch(){
        
        if(!Accountlist.isEmpty()){
            for (int i = 0; i < Accountlist.size(); i++) {
                if(username.equals(Accountlist.get(i).getName())){
                    return i;
                }
            }
        }
        return -1;
    }
    
    //add:
        //try catches
}
