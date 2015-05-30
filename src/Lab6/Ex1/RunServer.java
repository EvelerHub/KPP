package Lab6.Ex1;

import Lab6.Ex1.Data.ActiveUsers;
import Lab6.Ex1.Server.UPDServer;

import java.io.*;
import java.net.SocketException;

/**
 * Created by Eveler on 28.05.2015.
 */
public class RunServer {

    public static void main(String[] args) throws InterruptedException {
        ActiveUsers activeUsers = null;
        UPDServer updServer = null;
        String fileName = "Lab6Ex1.txt";
        try {
            activeUsers = (ActiveUsers) deSerealize(fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ops! some problems with file");
        }

        if (activeUsers == null){
            activeUsers = new ActiveUsers();
        }

        try {
            updServer = new UPDServer(1501,activeUsers);
            updServer.start(256);
        } catch (SocketException e) {
            System.out.println("Ops! SocketException");
        }
    }

    private static Object deSerealize(String fileName) throws IOException, ClassNotFoundException {
        Object object = null;
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
        object = objectInputStream.readObject();
        objectInputStream.close();

        return object;
    }

}
