
package casinonogui;

import java.io.*;
import java.util.*;


public class fileHandling {
    
    public static String folderDirectory = System.getProperty("user.dir") + "\\Accountlist.txt";
    
    public static void writeFile(ArrayList<Account> Accountlist) {

        try {
            FileWriter writeToFile = new FileWriter(folderDirectory, false);
            PrintWriter printToFile = new PrintWriter(writeToFile);
            for (int i = 0; i < Accountlist.size(); i++) {
                printToFile.println(Accountlist.get(i).toString());
            }
            printToFile.close();
            writeToFile.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public static ArrayList<Account> readFile() {

        ArrayList<Account> Accountlist = new ArrayList<>();
        String lineFromFile;
         
        try {
            BufferedReader read = new BufferedReader(new FileReader(folderDirectory));
            while ((lineFromFile = read.readLine()) != null) {
                String[] AccountDetails = lineFromFile.split(",");
                //String name, String password, double money
                Account myAccount = new Account(AccountDetails[0],AccountDetails[1],Double.parseDouble(AccountDetails[2]));
                Accountlist.add(myAccount);
            }
            read.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return Accountlist;
    }
}
