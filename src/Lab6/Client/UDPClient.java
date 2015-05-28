package Lab6.Client;

import Lab6.Data.ActiveUsers;
import Lab6.Data.User;

import java.io.*;
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
    private byte[] buffer;

    public UDPClient(String address, int port) throws SocketException, UnknownHostException {
        activeUsers = new ActiveUsers();
        serverPort = port;
        serverAddress = InetAddress.getByName(address);
        datagramSocket = new DatagramSocket();
        datagramSocket.setSoTimeout(1000);
        isAlive = false;
        buffer = null;
    }

    public void start(int bufferSize) throws ClassNotFoundException, IOException {
        buffer = new byte[bufferSize];

        class Sender implements Runnable {
            byte[] buffer;

            public Sender(byte[] buffer) {
                this.buffer = buffer;
            }

            @Override
            public void run() {
                while (isAlive) {
                    try {
                        datagramPacket = new DatagramPacket(buffer, buffer.length);

                        datagramSocket.receive(datagramPacket);

                        if (datagramPacket.getLength() == 0) break;
                        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(datagramPacket.getData(), 0, datagramPacket.getLength()));
                        User user = (User)in.readObject();
                        activeUsers.add(user);
                        //buffer = clear(buffer);

                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        datagramPacket = new DatagramPacket(buffer, buffer.length, serverAddress, serverPort);
        datagramSocket.send(datagramPacket);

        System.out.println("Sending request");
        if (!isAlive) {
            isAlive = true;
            Thread thread = new Thread(new Sender(buffer));
            thread.start();
        }
        System.out.println("Registered users: " + activeUsers.size());
        System.out.println(activeUsers);
    }

    private byte[] clear(byte[] buffer) {
        return new byte[buffer.length];
    }


}
