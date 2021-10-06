package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PanelPda extends JPanel {
    private JLabel  labelWord;
    private JTextField word;
    private JButton btnBack, btnValidate;
    private GridSystem grid;
    private JTextArea pdaInfo;

    public PanelPda(ActionListener l) {
        this.grid=new GridSystem(this);
        initComponents();
        setCommands(l);
    }

    private void setCommands(ActionListener l) {
        this.btnValidate.addActionListener(l);
        this.btnValidate.setActionCommand("VALIDAR");

        this.btnBack.addActionListener(l);
        this.btnBack.setActionCommand("VOLVER");
    }

    public void changeInfo(String info){
        pdaInfo.setText(info);
    }

    private void initComponents( ) {
        this.btnBack=new JButton("VOLVER");
        this.add(btnBack,grid.insertComponent(0,0,1,1));

        this.pdaInfo = new JTextArea("");
        pdaInfo.setEditable(false);
        this.add(pdaInfo,grid.insertComponent(1,0,12,4));

        this.labelWord=new JLabel("Ingrese una plabra para validar");
        this.add(labelWord,grid.insertComponent(5,0,1,1));
        this.word=new JTextField();
        this.add(word, grid.insertComponent(6,0,12,1));

        this.btnValidate=new JButton("VALIDAR");
        this.add(btnValidate, grid.insertComponent(7,0,12,1));
    }

    public String getWord(){
        return word.getText();
    }
    public void clean(){
        pdaInfo.setText("");
        word.setText("");
    }
}


