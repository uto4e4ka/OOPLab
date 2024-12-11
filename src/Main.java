import client.UserInterface;
import javax.swing.*;

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