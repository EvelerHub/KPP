
 package Lab6.Ex2;

 import java.io.IOException;
 import java.io.InterruptedIOException;
 import java.io.ObjectInputStream;
 import java.io.ObjectOutputStream;
 import java.net.InetSocketAddress;
 import java.net.Socket;

 public class Client {
    private int port = -1;
    private String server = null;
    private Socket socket = null;
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;

    public Client(String server, int port) {
        this.port = port;
        this.server = server;
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(server, port), 1000);
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
        } catch (InterruptedIOException e) {
            System.out.println("Error: " + e);
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public void finish() {
        try {
            os.writeObject(new StopOperation());
            os.flush();
            System.out.println(is.readObject());
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void applyOperation(CardOperation op) {
        try {
            os.writeObject(op);
            os.flush();

            System.out.println(is.readObject());
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
        }
    }


     public static void main(String[] args) {
         Client cl = new Client("localhost", 7891);
         AddMetroCardOperation op = new AddMetroCardOperation();
         op.getCrd().setUsr(new User("Petr", "Petrov", "M", "25.12.1968"));
         op.getCrd().setSerNum("00001");
         op.getCrd().setColledge("KhNU");
         op.getCrd().setBalance(25);
         cl.applyOperation(op);
         cl.finish();
         //
         cl = new Client("localhost", 7891);
         cl.applyOperation(new AddMoneyOperation("00001", 100));
         cl.applyOperation(new ShowBalanceOperation("00001"));
         cl.finish();
     }
 }
