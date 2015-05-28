package Lab6.Server;

import Lab6.Data.ActiveUsers;
import Lab6.Data.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by Eveler on 28.05.2015.
 */
public class UPDServer {
    boolean isAlive;
    private ActiveUsers activeUsers;
    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;
    private InetAddress inetAddress;
    private int port;

    public UPDServer(int port) throws SocketException {
        this.port = port;
        activeUsers = new ActiveUsers();
        datagramSocket = new DatagramSocket(port);
        datagramPacket = null;
        inetAddress = null;
        isAlive = false;
    }

    public void start(int bufferSize) {
        System.out.println("Server starts");

        class Checker implements Runnable {
            private int bufferSize;

            public Checker(int bufferSize) {
                this.bufferSize = bufferSize;
            }

            @Override
            public void run() {
                while (isAlive) {
                    try {
                        getUserData(bufferSize);
                    } catch (IOException e) {
                        System.out.println("Ops! IOException during getting data");
                    }

                    log(inetAddress, port);

                    try {
                        sendUserData();
                    } catch (IOException e) {
                        System.out.println("Ops! IOException during sending data");
                    }
                }
            }
        }

        if (!isAlive) {
            isAlive = true;
            Thread thread = new Thread(new Checker(bufferSize));
            thread.start();
        }
    }

    /**
     * method must clear byte buffer.
     * is returning empty massive of byte type
     * SOMETHING WRONG
     * <p/>
     *
     * @authore Eveler;
     */
    private byte[] clear(byte[] arr) {
        return new byte[arr.length];
    }

    private void getUserData(int bufferSize) throws IOException {
        byte[] buffer = new byte[bufferSize];
        datagramPacket = new DatagramPacket(buffer, buffer.length);
        datagramSocket.receive(datagramPacket);
        inetAddress = datagramPacket.getAddress();
        port = datagramPacket.getPort();

        User user = new User(inetAddress, port);

        if (activeUsers.isEmpty()) {
            activeUsers.add(user);
        } else if (!activeUsers.contains(user)) {
            activeUsers.add(user);
        }

        buffer = clear(buffer);
    }

    private void sendUserData() throws IOException {
        byte[] buffer;
        for (int i = 0; i < activeUsers.size(); i++) {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject(activeUsers.get(i));
            buffer = bout.toByteArray();
            datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);
            datagramSocket.send(datagramPacket);
        } buffer = "end".getBytes();
        datagramPacket = new DatagramPacket(buffer, 0, inetAddress, port);
        datagramSocket.send(datagramPacket);
    }

    private void log(InetAddress inetAddress, int port) {
        System.out.println("Request from " + inetAddress.getHostAddress() + " port:" + port);
    }

}
