package Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class ServerConnection {
    Socket socket;
    public ServerConnection() throws IOException {
        socket = new Socket("localhost",1111);
    }
    public void sendObject(Serializable serializable) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(serializable);
    }
}
