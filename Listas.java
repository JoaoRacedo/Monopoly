package monopolio;

import monopolio.Nodos.*;

public class Listas {

    public class DLL {

        Node head;
        Node tail;

        public void insertNode(Node nodo) {

            if (head == null) {
                head = nodo;
            } else {
                tail.next = nodo;
                tail.next.prev = tail;
            }
            tail = nodo;

        }

        public void deleteNode(Node nodo) {

            if (head == null) {
                System.out.println("Lista Vacia");
            } else if (head == nodo) {
                head = head.next;
                if (head.prev != null) {
                    head.prev = null;
                }
            } else {
                nodo.prev.next = nodo.next;
                nodo.next.prev = nodo.prev;
            }
        }

    }

    public static class CDLL {

        Node head;

        public void insertNode(Node nodo) {

            if (head == null) {
                head = nodo;
                head.next = head;
                head.prev = head;
            } else {
                head.prev.next = nodo;
                head.prev.next.next = head;
                head.prev.next.prev = head.prev;
                head.prev = head.prev.next;
            }

        }

        public void deleteNode(Node nodo) {

            if (nodo == nodo.next) {
                nodo = null;
            } else {
                nodo.prev.next = nodo.next;
                nodo.next.prev = nodo.prev;
            }

        }

        public void placeNodeBeforeHeadAsHead(Node nodo) {

            if (head == null) {

                System.out.println("Lista vacia");

            } else if (nodo != head) {

                deleteNode(nodo);

                nodo.next = head;
                nodo.prev = head.prev;

                head.prev.next = nodo;
                nodo.next.prev = nodo;

                head = nodo;
            }
        }

        public void placeNodeAfterNode(Node nodo, Node nodoObjetivo) {

            if (head == null) {

                System.out.println("Lista vacia");

            } else if (nodo != nodoObjetivo) {

                deleteNode(nodo);

                nodo.next = nodoObjetivo.next;
                nodo.prev = nodoObjetivo;

                nodo.prev.next = nodo;
                nodo.next.prev = nodo;
            }
        }

    }

    public static final class Tablero extends CDLL {

        Node carcel;

        public Tablero(Jugador banco) {

            this.carcel = new Carcel();
            llenarTablero(banco);

        }

        public void llenarTablero(Jugador banco) {

            head = null;
            insertNode(new Salida());
            
            insertNode(new TituloDePropiedad("MEDITERRANEAN AVENUE", 1, 60, 2, "BROWN", 621, 703, 62, 79, banco));
            insertNode(new ArcaComunal(2, 559, 679, 62, 103));
            insertNode(new TituloDePropiedad("BALTIC AVENUE", 3, 60, 4, "BROWN", 496, 703, 62, 79, banco));
            insertNode(new Impuesto("INCOME TAX", 4, 200, 433, 679, 62, 103));
            insertNode(new Estacion("READING RAILROAD", 5, 200, 25, 370, 679, 62, 103, banco));
            insertNode(new TituloDePropiedad("ORIENTAL AVENUE", 6, 100, 6, "LIGHT BLUE", 307, 703, 62, 79, banco));
            insertNode(new Fortuna(7, 244, 679, 62, 103));
            insertNode(new TituloDePropiedad("VERMONT AVENUE", 8, 100, 6, "LIGHT BLUE", 181, 703, 62, 79, banco));
            insertNode(new TituloDePropiedad("CONNECTICUT AVENUE", 9, 120, 8, "LIGHT BLUE", 120, 703, 62, 79, banco));

            insertNode(new SoloVisitas());
            carcel.prev = head.prev;

            insertNode(new TituloDePropiedad("ST. CHARLES PLACE", 11, 140, 10, "PINK", 14, 617, 79, 62, banco));
            carcel.next = head.prev;

            insertNode(new ServicioPublico("ELECTRIC COMPANY", 12, 150, 14, 553, 103, 62, banco));
            insertNode(new TituloDePropiedad("STATES AVENUE", 13, 140, 10, "PINK", 14, 491, 79, 62, banco));
            insertNode(new TituloDePropiedad("VIRGINIA AVENUE", 14, 160, 12, "PINK", 14, 428, 79, 62, banco));
            insertNode(new Estacion("PENNSYLVANIA RAILROAD", 15, 200, 25, 14, 365, 103, 62, banco));
            insertNode(new TituloDePropiedad("ST. JAMES PLACE", 16, 180, 14, "ORANGE", 14, 302, 79, 62, banco));
            insertNode(new ArcaComunal(17, 14, 239, 103, 62));
            insertNode(new TituloDePropiedad("TENNESSEE AVENUE", 18, 180, 14, "ORANGE", 14, 176, 79, 62, banco));
            insertNode(new TituloDePropiedad("NEW YORK AVENUE", 19, 200, 16, "ORANGE", 14, 115, 79, 62, banco));

            insertNode(new ParkingGratuito());

            insertNode(new TituloDePropiedad("KENTUCKY AVENUE", 21, 220, 18, "RED", 120, 9, 62, 79, banco));
            insertNode(new Fortuna(22, 181, 9, 62, 103));
            insertNode(new TituloDePropiedad("INDIANA AVENUE", 23, 220, 18, "RED", 244, 9, 62, 79, banco));
            insertNode(new TituloDePropiedad("ILLINOIS AVENUE", 24, 240, 20, "RED", 307, 9, 62, 79, banco));
            insertNode(new Estacion("B. & O. RAILROAD", 25, 200, 25, 370, 9, 62, 103, banco));
            insertNode(new TituloDePropiedad("ATLANTIC AVENUE", 26, 260, 22, "YELLOW", 433, 9, 62, 79, banco));
            insertNode(new TituloDePropiedad("VENTNOR AVENUE", 27, 260, 22, "YELLOW", 496, 9, 62, 79, banco));
            insertNode(new ServicioPublico("WATER WORKS", 28, 150, 559, 9, 62, 103, banco));
            insertNode(new TituloDePropiedad("MARVIN GARDENS", 29, 280, 24, "YELLOW", 623, 9, 62, 79, banco));

            insertNode(new VayaALaCarcel());

            insertNode(new TituloDePropiedad("PACIFIC AVENUE", 31, 300, 26, "GREEN", 708, 115, 79, 62, banco));
            insertNode(new TituloDePropiedad("NORTH CAROLINA AVENUE", 32, 300, 26, "GREEN", 708, 176, 79, 62, banco));
            insertNode(new ArcaComunal(33, 685, 239, 103, 62));
            insertNode(new TituloDePropiedad("PENNSYLVANIA AVENUE", 34, 320, 28, "GREEN", 708, 302, 79, 62, banco));
            insertNode(new Estacion("SHORT LINE", 35, 200, 25, 685, 365, 103, 62, banco));
            insertNode(new Fortuna(36, 685, 428, 103, 62));
            insertNode(new TituloDePropiedad("PARK PLACE", 37, 350, 35, "DARK BLUE", 708, 493, 79, 62, banco));
            insertNode(new Impuesto("LUXURY TAX", 38, 100, 685, 554, 103, 62));
            insertNode(new TituloDePropiedad("BOARDWALK", 39, 400, 50, "DARK BLUE", 708, 616, 79, 62, banco));

        }

    }

}
