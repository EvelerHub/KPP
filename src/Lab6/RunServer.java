package Lab6;

import Lab6.Client.UDPClient;
import Lab6.Server.UPDServer;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Eveler on 28.05.2015.
 */
public class RunServer {
    public static void main(String[] args) throws InterruptedException {
        try {
            UPDServer updServer = new UPDServer(1501);
            updServer.start(256);
        } catch (SocketException e) {
            System.out.println("Ops! SocketException");
        }


    }
}
