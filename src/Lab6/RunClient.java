package Lab6;

import Lab6.Client.UDPClient;
import Lab6.Server.UPDServer;

import java.io.IOException;
import java.net.SocketException;

/**
 * Created by Eveler on 29.05.2015.
 */
public class RunClient {
    public static void main(String[] args) {
        try {
            UDPClient udpClient = new UDPClient("127.0.0.1", 1501);
            udpClient.start(256);
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Ops! ClassNotFoundException | IOException e");
        }
    }


}
