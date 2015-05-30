package Lab6.Ex2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;
    private boolean work = true;
    private MetroCardBank bnk = null;
    private Socket s = null;
    public ClientHandler(MetroCardBank bnk, Socket s) {
        this.bnk = bnk;
        this.s = s;
        this.work = true;
        try {
            this.is = new ObjectInputStream(s.getInputStream());
            this.os = new ObjectOutputStream(s.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
    @Override
    public void run() {
        synchronized (bnk) {
            System.out.println("Client Handler Started for: " + s);
            while (work) {
                Object obj;
                try {
                    obj = is.readObject();
                    processOperation(obj);
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                } catch (ClassNotFoundException e) {
                    System.out.println("Error: " + e);
                }
            }
            try {
                System.out.println("Client Handler Stopped for: " + s);
                s.close();
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }
    private void processOperation(Object obj) throws
            IOException, ClassNotFoundException {
        if (obj instanceof StopOperation) {

            finish();
        } else if (obj instanceof AddMetroCardOperation) {
            addCard(obj);
        } else if (obj instanceof AddMoneyOperation) {
            addMoney(obj);
        } else if (obj instanceof PayMoneyOperation) {
            payMoney(obj);
        } else if (obj instanceof RemoveCardOperation) {
            removeCard(obj);
        } else if (obj instanceof ShowBalanceOperation) {
            showBalance(obj);
        } else {
            error();
        }
    }
    private void finish() throws IOException {
        work = false;
        os.writeObject("Finish Work " + s);
        os.flush();
    }
    private void addCard(Object obj)
            throws IOException, ClassNotFoundException {
        bnk.addCard(((AddMetroCardOperation) obj).getCrd());
        os.writeObject("Card Added");
        os.flush();
    }

    private void addMoney(Object obj)
            throws IOException, ClassNotFoundException {
        AddMoneyOperation op = (AddMoneyOperation) obj;
        boolean res = bnk.addMoney(op.getSerNum(), op.getMoney());
        if (res) {
            os.writeObject("Balance Added");
            os.flush();
        } else {
            os.writeObject("Cannot Balance Added");
            os.flush();
        }
    }

    private void payMoney(Object obj)
            throws IOException, ClassNotFoundException {
        PayMoneyOperation op = (PayMoneyOperation) obj;
        boolean res = bnk.getMoney(op.getSerNum(), op.getMoney());
        if (res) {
            os.writeObject("Money Payed");
            os.flush();
        } else {
            os.writeObject("Cannot Pay Money");
            os.flush();
        }
    }

    private void removeCard(Object obj)
            throws IOException, ClassNotFoundException {
        RemoveCardOperation op = (RemoveCardOperation) obj;
        boolean res = bnk.removeCard(op.getSerNum());
        if (res) {
            os.writeObject("Metro Card Succesfully Remove: " + op.getSerNum());
            os.flush();
        } else {
            os.writeObject("Cannot Remove Card" + op.getSerNum());
            os.flush();
        }
    }

    private void showBalance(Object obj)
            throws IOException, ClassNotFoundException {
        ShowBalanceOperation op = (ShowBalanceOperation) obj;
        int ind = bnk.findMetroCard(op.getSerNum());
        if (ind >= 0) {
            os.writeObject("Card : " + op.getSerNum() + " balance: "
                    + bnk.getStore().get(ind).getBalance());
            os.flush();
        } else {
            os.writeObject("Cannot Show Balance for Card: " + op.getSerNum());
        }
    }

    private void error() throws IOException {
        os.writeObject("Bad Operation");
        os.flush();
    }

}
