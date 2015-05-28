package Lab6.Data;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Eveler on 28.05.2015.
 */
public class User implements Serializable {
    private static final long serialVersionUID = -7955507795118984257L;
    private InetAddress inetAddress;
    private int port;

    public User() throws UnknownHostException {
        this.inetAddress = null;
        this.port = -1;
    }

    public User(InetAddress inetAddress, int port) {
        this.inetAddress = inetAddress;
        this.port = port;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "User{" +
                "inetAddress=" + inetAddress +
                ", port=" + port +
                '}';
    }

}
