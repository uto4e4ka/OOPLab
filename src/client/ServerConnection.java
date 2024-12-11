package client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection {
    Socket socket;
    DataInputStream dataInputStream;
    ObjectOutputStream objectOutputStream;
    public ServerConnection() throws IOException {
        socket = new Socket("localhost",1111);
        dataInputStream= new DataInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    }
    public void sendObject(Matrix matrix) throws IOException {

            objectOutputStream.writeObject(matrix);
            objectOutputStream.flush();
            //objectOutputStream.close();


    }
    public Double getDouble(){
        try {
            return dataInputStream.readDouble();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
