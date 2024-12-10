import Client.Matrix;
import Client.UserInterface;
import javax.swing.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args){
        UserInterface userInterface = new UserInterface(5,5);
        JFrame jFrame= new JFrame("Matrix");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setSize(1000,1000);
        jFrame.add(userInterface);

    }

}