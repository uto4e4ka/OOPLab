package Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserInterface extends JPanel {
    private JTable jTable;
    private JPanel mainP = new JPanel(new BorderLayout(10,10));
    Matrix matrix;
    ServerConnection serverConnection;
    public UserInterface(int defW,int defH){
        loadInterface();
        setTable(defW,defH);
        try {
            serverConnection = new ServerConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadInterface(){
        setLayout(new FlowLayout(FlowLayout.LEFT,50,10));
        JButton sendButton = new JButton("Отправить");

        mainP.add(sendButton,BorderLayout.SOUTH);

        JPanel sizPanel = new JPanel(new BorderLayout(10,10));
        JPanel textPanel = new JPanel(new GridLayout(2,2,10,10));
        JTextPane jTextWeight = new JTextPane();
        jTextWeight.setPreferredSize(new Dimension(50, 20));
        JTextPane jTextHeight = new JTextPane();
        jTextHeight.setPreferredSize(new Dimension(50, 20));

        textPanel.add(new JLabel("Высота"));
        textPanel.add(new JLabel("Ширина"));
        textPanel.add(jTextHeight);
        textPanel.add(jTextWeight);

        sizPanel.add(textPanel,BorderLayout.CENTER);
        JButton changeButton = new JButton("Изменить");
        sizPanel.add(changeButton,BorderLayout.SOUTH);
        add(sizPanel);
        add(mainP);

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r =0 ,c= 0;
                try {
                    r = Integer.parseInt(jTextHeight.getText());
                    c = Integer.parseInt(jTextWeight.getText());
                    setTable(r,c);
                }catch (NumberFormatException ex){

                }
                revalidate();
            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    serverConnection.sendObject(matrix);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void setTable(int row,int col){
        if(row<0||col<0) {
            return;
        }
        if(jTable!=null)mainP.remove(jTable);
        jTable = new JTable(row,col);
        jTable.setRowHeight(50);
        for (int i=0;i<col;i++){
            jTable.getColumnModel().getColumn(i).setPreferredWidth(50);
        }
        matrix = new Matrix(row,col);
        mainP.add(jTable,BorderLayout.CENTER);

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
