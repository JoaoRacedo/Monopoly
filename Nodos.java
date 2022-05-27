package monopolio;

public class Nodos {

    public static class Node {

        Node next;
        Node prev;

    }

    public class Casilla extends Node {

        String nombre;
        int posicion;
        int numJugadores;
        int primerPixelX;
        int primerPixelY;
        int largo;
        int alto;

    }

    public class Propiedad extends Casilla {

        int valorVenta;
        int valorRenta;
        Jugador propietario;

    }

    public static class TituloDePropiedad extends Propiedad {

        public TituloDePropiedad(String nombre, int posicion, int valorVenta, int valorRenta, String color, int primerPixelX, int primerPixelY, int largo, int alto, Jugador banco) {
            this.nombre = nombre;
            this.posicion = posicion;
            this.valorVenta = valorVenta;
            this.valorRenta = valorRenta;
            this.primerPixelX = primerPixelX;
            this.primerPixelY = primerPixelY;
            this.largo = largo;
            this.alto = alto;
            this.propietario = banco;
        }

    }

    public static class Estacion extends Propiedad {

        public Estacion(String nombre, int posicion, int valorVenta, int valorRenta, int primerPixelX, int primerPixelY, int largo, int alto, Jugador banco) {
            this.nombre = nombre;
            this.posicion = posicion;
            this.valorVenta = valorVenta;
            this.valorRenta = valorRenta;
            this.primerPixelX = primerPixelX;
            this.primerPixelY = primerPixelY;
            this.largo = largo;
            this.alto = alto;
            this.propietario = banco;
        }

    }

    public static class ServicioPublico extends Propiedad {

        public ServicioPublico(String nombre, int posicion, int valorVenta, int primerPixelX, int primerPixelY, int largo, int alto, Jugador banco) {
            this.nombre = nombre;
            this.posicion = posicion;
            this.valorVenta = valorVenta;
            this.primerPixelX = primerPixelX;
            this.primerPixelY = primerPixelY;
            this.largo = largo;
            this.alto = alto;
            this.propietario = banco;
        }

    }

    public static class Impuesto extends Casilla {

        int valorImpuesto;

        public Impuesto(String nombre, int posicion, int valorImpuesto, int primerPixelX, int primerPixelY, int largo, int alto) {
            this.nombre = nombre;
            this.posicion = posicion;
            this.valorImpuesto = valorImpuesto;
            this.primerPixelX = primerPixelX;
            this.primerPixelY = primerPixelY;
            this.largo = largo;
            this.alto = alto;
        }

    }

    public static class ArcaComunal extends Casilla {

        String descripcion;
        int numeroCarta;

        public ArcaComunal(int posicion, int primerPixelX, int primerPixelY, int largo, int alto) {
            this.nombre = "COMMUNITY CHEST";
            this.posicion = posicion;
            this.primerPixelX = primerPixelX;
            this.primerPixelY = primerPixelY;
            this.largo = largo;
            this.alto = alto;
        }

    }

    public static class Fortuna extends Casilla {

        String descripcion;
        int numeroCarta;

        public Fortuna(int posicion, int primerPixelX, int primerPixelY, int largo, int alto) {
            this.nombre = "CHANCE";
            this.posicion = posicion;
            this.primerPixelX = primerPixelX;
            this.primerPixelY = primerPixelY;
            this.largo = largo;
            this.alto = alto;
        }

    }

    public static class Salida extends Casilla {

        int pagaSalida;

        public Salida() {
            this.nombre = "GO";
            this.posicion = 0;
            this.pagaSalida = 200;
            this.primerPixelX = 685;
            this.primerPixelY = 679;
            this.largo = 103;
            this.alto = 103;
        }

    }

    public static class SoloVisitas extends Casilla {

        public SoloVisitas() {
            this.nombre = "JUST VISITING";
            this.posicion = 10;
            this.primerPixelX = 14;
            this.primerPixelY = 679;
            this.largo = 103;
            this.alto = 103;
        }

    }

    public static class Carcel extends Casilla {

        public Carcel() {
            this.nombre = "Jail";
            this.posicion = 10;
            this.primerPixelX = 36;
            this.primerPixelY = 680;
            this.largo = 81;
            this.alto = 81;
        }

        public void encarcelarJugador(Jugador jugador) {

            jugador.casillaActual = this;

        }

    }

    public static class ParkingGratuito extends Casilla {

        public ParkingGratuito() {
            this.nombre = "FREE PARKING";
            this.posicion = 20;
            this.primerPixelX = 14;
            this.primerPixelY = 9;
            this.largo = 103;
            this.alto = 103;
        }

    }

    public static class VayaALaCarcel extends Casilla {

        public VayaALaCarcel() {
            this.nombre = "GO TO JAIL";
            this.posicion = 30;
            this.primerPixelX = 685;
            this.primerPixelY = 9;
            this.largo = 103;
            this.alto = 103;
        }

    }

}
