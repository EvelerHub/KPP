package Lab6.Ex2;

public class AddMoneyOperation extends CardOperation {
    private String serNum = null;
    private double money = 0.0;

    public AddMoneyOperation(String serNum, double money) {
        this.serNum = serNum;
        this.money = money;
    }

    public AddMoneyOperation() {
        this("null", 0.0);
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }
}
