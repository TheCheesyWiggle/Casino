
package casinonogui;


public class Account {
    private String name;
    private String password;
    private double money;

    public Account(String name, String password,double money) {
        this.name = name;
        this.password = password;
        this.money = money;
    }
    
    public String toString(){
        return name+","+password+","+money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public double getMoney() {
        return money;
    }
    
    
}
