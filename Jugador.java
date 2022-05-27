package monopolio;

import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import monopolio.Listas.DLL;
import monopolio.Nodos.*;

public class Jugador extends Node {

    String nombreJugador;
    int numeroJugador;
    int dinero;
    int patrimonio;
    Node casillaActual;

    int resultadoDado1;
    int resultadoDado2;
    int contadorDobles;
    int turnosCarcel;
    boolean enCarcel;
    
    ImageIcon imgagenJugador;
    JLabel imagenToken;

    public Jugador(String nombreJugador, int numeroJugador) {

        this.nombreJugador = nombreJugador;
        this.numeroJugador = numeroJugador;
        this.dinero = 1500;

    }
    
    public Jugador() {

        this.nombreJugador = "Banco";
        this.dinero = 1000000000;

    }

    //Consultar o cambiar estados del jugador
    public void Aprisionar() {

        enCarcel = true;

    }

    public void intentarSalirCarcel() {

    }

    public void accionTituloDePropiedad() {

        TituloDePropiedad tituloDePropiedad = (TituloDePropiedad) casillaActual;
        Scanner leer = new Scanner(System.in);

        if (tituloDePropiedad.propietario == null) {

            System.out.println("Desea comprar esta propiedad?");
            System.out.print("Si(1): ");

            if (leer.nextInt() == 1) {

                intentarComprarTituloDePropiedad(tituloDePropiedad);

            }

        } else {

        }

    }

    // Comprar propiedades
    public void intentarComprarTituloDePropiedad(TituloDePropiedad tituloDePropiedad) {

        if (dinero >= tituloDePropiedad.valorVenta) {

            tituloDePropiedad.propietario = this;
            estacionesDueño.insertNode(new NodoConTituloDePropiedad(tituloDePropiedad));
            dinero -= tituloDePropiedad.venta;

        } else if (patrimonio >= tituloDePropiedad.venta) {

            System.out.println("No cuenta con el dinero suficiente pero puede hipotecar algo para comparlo");
            System.out.print("Si(1): ");

            if (leer.nextInt() == 1) {

                intentarComprarTituloDePropiedad(tituloDePropiedad);

            }

        }

    }
}
