package client;

import java.io.*;
import java.net.Socket;

public class ServerConnection implements Closeable {
    private Socket socket;
    private DataInputStream dataInputStream;
    private ObjectOutputStream objectOutputStream;
    public ServerConnection() throws IOException {
        socket = new Socket("localhost",7777);
        dataInputStream= new DataInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    }
    public void sendObject(Matrix matrix) throws IOException {

            objectOutputStream.writeObject(matrix);
            objectOutputStream.flush();


    }
    public Double getDouble() throws IOException {

            return dataInputStream.readDouble();
    }

    @Override
    public void close() throws IOException {
        if(socket!=null)
            socket.close();
    }
}
