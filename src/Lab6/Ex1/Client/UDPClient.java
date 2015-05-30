package Lab6.Ex1.Client;

import Lab6.Ex1.Data.ActiveUsers;
import Lab6.Ex1.Data.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;

/**
 * Created by Eveler on 28.05.2015.
 */
public class UDPClient {
    private ActiveUsers activeUsers;
    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;
    private InetAddress serverAddress;
    private int serverPort = -1;
    private boolean isAlive;

    public UDPClient(String address, int port) throws SocketException, UnknownHostException {
        activeUsers = new ActiveUsers();
        serverPort = port;
        serverAddress = InetAddress.getByName(address);
        datagramSocket = new DatagramSocket();
        datagramSocket.setSoTimeout(1000);
        isAlive = false;
    }

    public void start(int bufferSize) throws ClassNotFoundException, IOException {
        byte[] buffer = new byte[bufferSize];
        try {
            datagramPacket = new DatagramPacket(buffer, buffer.length, serverAddress, serverPort);
            datagramSocket.send(datagramPacket);
            System.out.println("Sending request");
            while (true) {
                datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                if (datagramPacket.getLength() == 0) break;
                ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(datagramPacket.getData(), 0, datagramPacket.getLength()));
                User usr = (User) in.readObject();
                activeUsers.add(usr);
                clear(buffer);
            }
        } catch (SocketTimeoutException e) {
            System.out.println("Server is unreachable: " + e);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        } finally {
            datagramSocket.close();
        } System.out.println("Registered users: " + activeUsers.size());
        System.out.println(activeUsers);
    }


    private byte[] clear(byte[] buffer) {
        return new byte[buffer.length];
    }


}
