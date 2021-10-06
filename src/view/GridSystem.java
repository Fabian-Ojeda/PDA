package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;

/**
 * Clase encargada de la organizacion en los paneles
 * @version 1
 * @author Omaira Galindo
 */


public class GridSystem {

    private static final int DEFAULT_COLUMN_WIDTH = 1;
    private static final int COLUMNS_NUMBER = 12;

    private GridBagConstraints constraints;
    /**
     * Constructor
     * @param component Clase tipo compente utilizada para los paneles.
     */
    public GridSystem(Container component) {
        component.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        generateBasicGrid(component);
    }

    /**
     * Genera un grid basico de 12 columnas.
     * @param component Clase tipo compente utilizada para los paneles.
     */

    private void generateBasicGrid(Container component){
        constraints.weightx = DEFAULT_COLUMN_WIDTH;
        for (int i = 0; i < COLUMNS_NUMBER; i++) {
            constraints.gridx = i;
            component.add(new JLabel(), constraints);
        }
    }

    /**
     * Genera un grid con diferentes configuraciones
     * @param row Variable de configuracion de estilo.
     * @param column Variable de configuracion de estilo.
     * @param width Variable de configuracion de estilo.
     * @param height Variable de configuracion de estilo.
     * @param weightx Variable de configuracion de estilo.
     * @param weighty Variable de configuracion de estilo
     * @return constraints retorna un tipo de layout.
     */

    public GridBagConstraints insertComponent(int row, int column, int width,int height, double weightx ,double weighty){
        constraints.gridy = row;
        constraints.gridx = column;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.weighty = weighty;
        constraints.weightx = weightx;
        return constraints;
    }


    /**
     * Genera un grid con diferentes configuraciones
     * @param row Variable de configuracion de estilo.
     * @param column Variable de configuracion de estilo.
     * @param width Variable de configuracion de estilo.
     * @param height Variable de configuracion de estilo.
     * @return constraints retorna un tipo de layout.
     */

    public GridBagConstraints insertComponent(int row, int column, int width, double height){
        constraints.gridy = row;
        constraints.gridx = column;
        constraints.gridwidth = width;
        constraints.weighty = height;
        return constraints;
    }

    /**
     * Inserta los componentes en la posicion del layaut definido
     * @param column Columna en la cual se inserta.
     * @param width Fila en la que se inserta.
     * @return constraints retorna un tipo de layout.
     */
    public GridBagConstraints insertComponent(int column, int width){
        constraints.gridx = column;
        constraints.gridwidth = width;
        return constraints;
    }


    /**
     * Inserta un borde definifo en los parametros el tamaño
     * @param top  tamaño  del borde superior.
     * @param right tamaño  del borde derecho.
     * @param bottom tamaño  del borde Inferior.
     * @param left tamaño  del borde Izquierdo.
     * @return constraints retorna un tipo de layout.
     */
    public void addExternalBorder(int top, int right, int bottom, int left){
        constraints.insets = new Insets(top,right,bottom,left);
    }
}