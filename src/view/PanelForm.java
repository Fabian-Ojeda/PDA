package view;

import model.PdaAutomatum;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class PanelForm extends JPanel {

    private GridSystem grid;
    private JLabel labelTitle,labelInitial,labelStackInitial, labelStates, labelr, labelalphabet,labelTransactions,labelFinals;
    private JTextField initial,stackInitial, states, r, alphabet,finals;
    private JTextArea transactions;

    private JButton btn;

    public PanelForm(ActionListener l) {
        this.grid=new GridSystem(this);
        initComponents();
        setCommands(l);
    }

    private void initComponents(){
        this.labelTitle=new JLabel("AUTÓMATA DE PILA",SwingConstants.CENTER);
        this.labelTitle.setHorizontalTextPosition(JLabel.CENTER);
        this.add(labelTitle, grid.insertComponent(0,0,12,1));

        this.labelInitial=new JLabel("Estado inicial");
        this.add(labelInitial, grid.insertComponent(1,0,1,1));
        this.initial=new JTextField();
        this.add(initial, grid.insertComponent(2,0,12,1));


        this.labelStackInitial=new JLabel("Símbolo inicial de la pila");
        this.add(labelStackInitial, grid.insertComponent(3,0,1,1));
        this.stackInitial=new JTextField();
        this.add(stackInitial, grid.insertComponent(4,0,12,1));

        this.labelStates=new JLabel("Estados (Separados por espacios: [q1 q2 q3])");
        this.add(labelStates, grid.insertComponent(5,0,1,1));
        this.states=new JTextField();
        this.add(states, grid.insertComponent(6,0,12,1));

        this.labelr=new JLabel("Simbolos de la pila (Separado por espacios: [X @ ?])");
        this.add(labelr, grid.insertComponent(7,0,1,1));
        this.r=new JTextField();
        this.add(r, grid.insertComponent(8,0,12,1));

        this.labelalphabet=new JLabel("Alfabeto (Separado por espacios: [a b c])");
        this.add(labelalphabet, grid.insertComponent(9,0,1,1));
        this.alphabet=new JTextField();
        this.add(alphabet, grid.insertComponent(10,0,12,1));

        this.labelFinals=new JLabel("Estados finales (Separado por espacios: [qf qa])");
        this.add(labelFinals, grid.insertComponent(11,0,1,1));
        this.finals=new JTextField();
        this.add(finals, grid.insertComponent(12,0,12,1));

        this.labelTransactions=new JLabel("Transiciones (Separado por espacios y saltos de lía: [q1 a X ? qf])");
        this.add(labelTransactions, grid.insertComponent(13,0,1,1));
        this.transactions=new JTextArea();
        this.add(transactions, grid.insertComponent(14,0,12,4));

        this.btn=new JButton("CREAR");
        this.add(btn, grid.insertComponent(18,0,12,1));

    }

    private void setCommands(ActionListener l) {
        btn.addActionListener(l);
        btn.setActionCommand("CREAR");
    }

    public PdaAutomatum createPda(){
        String i=this.initial.getText();
        String si=this.stackInitial.getText();
        String [] rtemp=this.r.getText().split(" ");
        ArrayList<String> rt=new ArrayList<>();
        for (int j = 0; j < rtemp.length; j++) {
            rt.add(rtemp[j]);
        }
        String [] stats=this.states.getText().split(" ");
        ArrayList<String> sts=new ArrayList<>();
        for (int j = 0; j < stats.length; j++) {
            sts.add(stats[j]);
        }
        String [] alp=this.alphabet.getText().split(" ");
        ArrayList<String> alpha=new ArrayList<>();
        for (int j = 0; j < alp.length; j++) {
            alpha.add(alp[j]);
        }
        String [] fs=this.finals.getText().split(" ");
        ArrayList<String> sfns=new ArrayList<>();
        for (int j = 0; j < fs.length; j++) {
            sfns.add(fs[j]);
        }
        String [] aux=this.transactions.getText().split("\n");
        ArrayList<String[]> tra=new ArrayList<>();
        for (int j = 0; j < aux.length; j++) {
            tra.add(aux[j].split(" "));
        }
        clean();
        return  new PdaAutomatum(sts, alpha, rt, sfns, tra, i, si);
    }

    public void clean(){
        initial.setText("");
        stackInitial.setText("");
        r.setText("");
        states.setText("");
        alphabet.setText("");
        finals.setText("");
        transactions.setText("");

    }

}
