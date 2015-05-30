package Lab6.Ex1;

import Lab6.Ex1.Client.UDPClient;

import java.io.IOException;

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
