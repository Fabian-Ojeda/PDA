package view;

import model.PdaAutomatum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class IoManager extends JFrame{

    private PanelForm panelForm;
    private PanelPda panelPda;

    public IoManager(ActionListener l){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(500,700);
        this.setLocationRelativeTo(null);
        initComponents(l);
        this.setTitle("PDA");
        this.setVisible(true);
    }

    private void initComponents(ActionListener l) {
        this.panelForm=new PanelForm(l);
        this.panelPda=new PanelPda(l);
        this.add(panelForm, BorderLayout.CENTER);
    }

    public PdaAutomatum getAutomatum(){
        return panelForm.createPda();
    }

    public void showInfo(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    public void showPda(String info) {
        this.panelPda.changeInfo(info);
        this.panelForm.setVisible(false);
        this.add(panelPda, BorderLayout.CENTER);

        panelPda.setVisible(true);
    }

    public String getWord(){
        return panelPda.getWord();
    }
    public void showForm() {
        this.panelPda.changeInfo("");
        panelPda.clean();
        this.panelPda.setVisible(false);
        this.add(panelForm, BorderLayout.CENTER);

        panelForm.setVisible(true);
    }
}
