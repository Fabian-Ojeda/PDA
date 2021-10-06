package controller;

import model.PdaAutomatum;
import view.IoManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private IoManager io;
    private PdaAutomatum pda;

    public Controller(){
      io=new IoManager(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "CREAR":
                createPda();
                break;

            case "VALIDAR":
                validateWord();
                break;
            case "VOLVER":
                back();
                break;

            default:
                break;
        }
    }

    private void back() {
        io.showForm();
    }

    private void validateWord() {
        boolean a=pda.validateWord(io.getWord());
        if (a){
            io.showInfo("Cadena aceptada");
        }else{
            io.showInfo("Cadena denegada");
        }
    }

    private void createPda() {
        this.pda=io.getAutomatum();
        io.showPda(pda.getInfo());
    }
}
