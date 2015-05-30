package Lab6.Ex2;

/**
 * Created by Jul on 26.04.2015.
 */
public class ShowBalanceOperation extends CardOperation{
    private String serNum = null;

    public ShowBalanceOperation(String serNum) {
        this.serNum = serNum;
    }

    public ShowBalanceOperation() {
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }
}
