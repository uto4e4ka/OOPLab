package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserInterface extends JPanel {
    private JTable jTable;
    private JScrollPane jScrollPane;
    private JPanel mainP = new JPanel(new BorderLayout(10,10));
    private Matrix matrix;
    private int defW,defH;
    private ServerConnection serverConnection;
    public UserInterface(int defW,int defH){
        this.defH = defH;
        this.defW = defW;
        loadInterface();
        setTable(defW,defH);
        try {
            serverConnection = new ServerConnection();
        } catch (IOException e) {
            showError("Ошибка подключения",e.toString());
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
        jTextWeight.setText(defW+"");
        jTextWeight.setPreferredSize(new Dimension(50, 20));
        JTextPane jTextHeight = new JTextPane();
        jTextHeight.setText(defH+"");
        jTextHeight.setPreferredSize(new Dimension(50, 20));

        textPanel.add(new JLabel("Высота"));
        textPanel.add(new JLabel("Ширина"));
        textPanel.add(jTextHeight);
        textPanel.add(jTextWeight);

        sizPanel.add(textPanel,BorderLayout.CENTER);
        JButton changeButton = new JButton("Изменить");
        sizPanel.add(changeButton,BorderLayout.SOUTH);

        JPanel resulPanel = new JPanel(new BorderLayout());
        JTextPane resultText = new JTextPane();
        resultText.setPreferredSize(new Dimension(50, 20));
        resulPanel.add(resultText,BorderLayout.SOUTH);
        resulPanel.add(new JLabel("Результат:"),BorderLayout.CENTER);

        add(sizPanel);
        add(mainP);
        add(resulPanel);

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r =0 ,c= 0;
                try {
                    r = Integer.parseInt(jTextHeight.getText());
                    c = Integer.parseInt(jTextWeight.getText());
                    setTable(r,c);
                }catch (Exception ex){
                    showError("Ошибка","Ошибка ввода размера матрицы.");
                }
                revalidate();
            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean error= false;
                Matrix m = new Matrix(matrix.getRows(),matrix.getColumns());
                for (int i =0;i<matrix.getRows();i++){
                    for (int k =0;k<matrix.getColumns();k++){
                        try {
                            String val = (String) jTable.getValueAt(i, k);
                            double d = Double.parseDouble(val);
                            if (d % 1 == 0&&d<1_0000_000&&d>=0)
                                m.set(i,k,d);
                            else
                                error = true;
                        }catch (Exception ex){
                            error = true;
                        }

                    }
                }
                   System.out.println(m);
                if(!error) {
                    try {
                        serverConnection.sendObject(m);
                        resultText.setText("" + serverConnection.getDouble());
                    }catch (Exception ex){
                        showError("Ошибка обмена с сервером",ex.toString());
                    }

                }else {
                    showError("Ошибка","Ошибка ввода значений в матрицу.");
                }
            }
        });
    }
    public void showError(String title,String message){
        JOptionPane.showMessageDialog(this,message,title,JOptionPane.ERROR_MESSAGE);
    }
    public void setTable(int row,int col){
        if(row<0||col<0||row>100||col>100) {
            showError("Ошибка","Введен недопустимый размер матрицы.");
            return;
        }
        if(jScrollPane !=null)mainP.remove(jScrollPane);
        jTable = new JTable(row,col);
        jTable.setRowHeight(50);
        for (int i=0;i<col;i++){
            jTable.getColumnModel().getColumn(i).setPreferredWidth(50);
        }
        matrix = new Matrix(row,col);
        for (int i =0;i<matrix.getRows();i++){
            for (int k =0;k<matrix.getColumns();k++){
                jTable.setValueAt(""+matrix.get(i,k),i,k);
            }
        }
        jTable.setTableHeader(null);
        jScrollPane = new JScrollPane(jTable);
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jScrollPane.setPreferredSize(new Dimension(800, 600));
        mainP.add(jScrollPane,BorderLayout.CENTER);

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
