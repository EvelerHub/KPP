package Lab6.Ex2;

import java.io.Serializable;

/**
 * Created by Jul on 26.04.2015.
 */
public class MetroCard{
    private String serNum;
    private User usr;
    private String colledge;
    private double balance;

    public MetroCard() {
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    public void setColledge(String colledge) {
        this.colledge = colledge;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getSerNum() {
        return serNum;
    }

    public User getUsr() {
        return usr;
    }

    public String getColledge() {
        return colledge;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "№: " + serNum + "\nUser: " + usr +
                "\nColledge: " + colledge +
                "\nBalance: " + balance;
    }

}
