/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matrices;

import java.awt.Dimension;
import java.util.Random;

/**
 *
 * @author galvez
 */
public class Matriz {
    private int[][]datos;
    private Random rnd = new Random();
    
    public Matriz(int filas, int columnas, boolean inicializarAleatorio){
        datos = new int[columnas][];
        for(int i=0; i<columnas; i++){
            datos[i] = new int[filas];
            if (inicializarAleatorio)
                for(int j=0; j<filas; j++)
                    datos[i][j] = rnd.nextInt(100);
        }
    }

    public int[][] getDatos() {
        return datos;
    }
    
    public void setDatos(int[][] datos) {
        this.datos = datos;
    }

    public Matriz(Dimension d, boolean inicializarAleatorio){
        this(d.height, d.width, inicializarAleatorio);
    }
    
    public Dimension getDimension(){
        return new Dimension(datos.length, datos[0].length);
    }
    
    public static Matriz sumarDosMatrices(Matriz a, Matriz b) throws DimensionesIncompatibles { 
        if(! a.getDimension().equals(b.getDimension())) throw new DimensionesIncompatibles("La suma de matrices requiere matrices de las mismas dimensiones");        
        int i, j, filasA, columnasA; 
        filasA = a.getDimension().height; 
        columnasA = a.getDimension().width; 
        Matriz matrizResultante = new Matriz(filasA, columnasA, false);
        for (j = 0; j < filasA; j++) { 
            for (i = 0; i < columnasA; i++) { 
                matrizResultante.datos[i][j] += a.datos[i][j] + b.datos[i][j]; 
            } 
        } 
        return matrizResultante; 
    } 
    
    public static Matriz multiplicarDosMatrices(Matriz a, Matriz b) throws DimensionesIncompatibles {
        if (a.getDimension().width != b.getDimension().height) {
            throw new DimensionesIncompatibles("El número de columnas de la primera matriz debe ser igual al número de filas de la segunda matriz");
        }
    
        int filasA = a.getDimension().height;
        int columnasB = b.getDimension().width;
        int columnasA = a.getDimension().width;
        Matriz matrizResultante = new Matriz(filasA, columnasB, false);
    
        for (int i = 0; i < filasA; i++) {
            for (int j = 0; j < columnasB; j++) {
                for (int k = 0; k < columnasA; k++) {
                    matrizResultante.datos[i][j] += a.datos[i][k] * b.datos[k][j];
                }
            }
        }
    
        return matrizResultante;
    }

    public static Matriz traspuesta(Matriz matriz){
        int filas = matriz.getDimension().height;
        int columnas = matriz.getDimension().width;
        Matriz matrizTraspuesta = new Matriz(columnas, filas, false);

        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                matrizTraspuesta.datos[i][j] = matriz.datos[j][i];
            }
        }

        return matrizTraspuesta;
    }


    @Override
    public String toString(){
        String ret = "";
        ret += "[\n";
        for (int i = 0; i < getDimension().width; i++) {
            ret += "(";
            for (int j = 0; j < getDimension().height; j++) {  
                ret += String.format("%3d", datos[i][j]); 
                if (j != getDimension().height - 1) ret += ", ";
            } 
            ret += ")";
            if (i != getDimension().width - 1) ret += ",";
            ret += "\n";
        } 
        ret += "]\n";
        return ret;
    }
}
