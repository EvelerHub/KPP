package Lab6;

import Lab6.Server.UPDServer;

import java.net.SocketException;

/**
 * Created by Eveler on 28.05.2015.
 */
public class Main {
    public static void main(String[] args) {
        try {
            UPDServer updServer = new UPDServer(1501);
            updServer.start(256);
        } catch (SocketException e) {
            System.out.println("Ops! SocketException");
        }
    }
}
