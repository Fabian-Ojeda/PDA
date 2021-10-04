package view;

import javax.swing.*;
import java.util.ArrayList;

public class IoManager {

    public IoManager(){

    }

    public String readString(String message){
        String toReturn = JOptionPane.showInputDialog(null, message);
        return toReturn;
    }

    public ArrayList<String> readStrings(String message){
        ArrayList<String> temp=new ArrayList<>();
        int val=1;
        do {
            temp.add(readString(message));
            val=Integer.parseInt(readString("DESEA INGRESAR MÁS CADENAS: \n0)no 1) SI "));
        } while(val==1);
        return temp;
    }

    public void showInfo(String message){
        JOptionPane.showMessageDialog(null, message);
    }
}
