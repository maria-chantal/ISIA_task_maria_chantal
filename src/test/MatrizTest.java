package test;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThrows;
import matrices.Matriz;
import matrices.DimensionesIncompatibles;

public class MatrizTest {
    @Test
    public void multiplicarDimensionesIncompatiblesTest() {
        Matriz a = new Matriz(3, 2, false);
        Matriz b = new Matriz(4, 3, false);
        assertThrows(DimensionesIncompatibles.class, () -> Matriz.multiplicarDosMatrices(a, b));
    }

    @Test
    public void multiplicar3x3Test() throws DimensionesIncompatibles {
        Matriz a = new Matriz(3, 3, false);
        Matriz b = new Matriz(3, 3, false);

        int[][] datosA = {{4,7,1},{3,8,12},{3,2,6}};
        int[][] datosB = {{8,4,17},{2,9,10},{7,1,8}};
        a.setDatos(datosA);
        b.setDatos(datosB);

        Matriz resultado = Matriz.multiplicarDosMatrices(a, b);
        Matriz esperados = new Matriz(3, 3, false);

        int[][] datosEsperados = {{53,80,146},{124,96,227},{70,36,119}};
        esperados.setDatos(datosEsperados);
        
        assertEquals(esperados.toString(), resultado.toString());
    }

    @Test
    public void trasponer3x3Test(){
        Matriz a = new Matriz(3,3, false);
        int[][] datosA = {{46,8,25},{73,50,6},{31,7,10}};
        a.setDatos(datosA);

        Matriz traspuesta = new Matriz(3,3, false);
        int[][] datosTraspuesta = {{46,73,31},{8,50,7},{25,6,10}};
        traspuesta.setDatos(datosTraspuesta);

        Matriz resultado = Matriz.traspuesta(a);

        assertEquals(resultado.toString(), traspuesta.toString());

    }

    @Test
    public void traspuestaOriginal(){
        Matriz a = new Matriz(3,3, false);
        int[][] datosA = {{46,8,25},{73,50,6},{31,7,10}};
        a.setDatos(datosA);

        Matriz traspuesta = Matriz.traspuesta(a);
        Matriz original = Matriz.traspuesta(traspuesta);
        assertEquals(a.toString(), original.toString());
    }

    @Test
    public void traspuestaProductoTest() throws DimensionesIncompatibles {
        Matriz a = new Matriz(3, 3, false);
        Matriz b = new Matriz(3, 3, false);

        int[][] datosA = {{2,3,5}, {1,4,6}, {7,8,9}};
        int[][] datosB = {{3,2,1}, {6,5,4}, {9,8,7}};
        a.setDatos(datosA);
        b.setDatos(datosB);

        Matriz multiplicar = Matriz.multiplicarDosMatrices(a, b);
        Matriz traspuestaMultiplicar = Matriz.traspuesta(multiplicar);
        
        Matriz traspuestaA = Matriz.traspuesta(a);
        Matriz traspuestaB = Matriz.traspuesta(b);
        Matriz productoTraspuestas = Matriz.multiplicarDosMatrices(traspuestaB, traspuestaA);

        assertEquals(productoTraspuestas.toString(), traspuestaMultiplicar.toString());
    }

}

