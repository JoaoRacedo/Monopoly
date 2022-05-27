package monopolio;

import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import static monopolio.Menu.playMusic;
import static monopolio.Menu.playSound;
import static monopolio.Menu.clip;
import static monopolio.NumeroJugadores.jugadores;

public final class Juego extends javax.swing.JFrame {

    public Jugador jugadorEnTurno = (Jugador) jugadores.head;
    public Jugador banco = new Jugador();
    public Listas.Tablero tablero = new Listas.Tablero(banco);

    public void changeCursor() {

        try {

            setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/Imagenes/cursor2.png").getImage(), new Point(0, 0), "My cursor"));

        } catch (HeadlessException | IndexOutOfBoundsException e) {
        }

    }

    public static void cambiarImagenDado(int dado, JLabel Dado) {

        switch (dado) {

            case 1:
                ImageIcon icon1 = new ImageIcon("src/Imagenes/D1.png");
                Dado.setIcon(icon1);
                break;
            case 2:
                ImageIcon icon2 = new ImageIcon("src/Imagenes/D2.png");
                Dado.setIcon(icon2);
                break;
            case 3:
                ImageIcon icon3 = new ImageIcon("src/Imagenes/D3.png");
                Dado.setIcon(icon3);
                break;
            case 4:
                ImageIcon icon4 = new ImageIcon("src/Imagenes/D4.png");
                Dado.setIcon(icon4);
                break;
            case 5:
                ImageIcon icon5 = new ImageIcon("src/Imagenes/D5.png");
                Dado.setIcon(icon5);
                break;
            case 6:
                ImageIcon icon6 = new ImageIcon("src/Imagenes/D6.png");
                Dado.setIcon(icon6);
                break;

        }

    }

    public Jugador buscarJugadorConDadoMasAlto(Jugador jugadorInicial) {

        int resultadoMayor = 0;
        Jugador jugadorActual = (Jugador) jugadorInicial;
        Jugador jugadorDadoMayor = null;

        do {

            if (jugadorActual.resultadoDado1 > resultadoMayor) {
                resultadoMayor = jugadorActual.resultadoDado1;
                jugadorDadoMayor = jugadorActual;
            }

            jugadorActual = (Jugador) jugadorActual.next;

        } while (jugadorActual != jugadores.head);

        return jugadorDadoMayor;

    }

    public void organizarJugadores() {

        Jugador jugadorMayor = buscarJugadorConDadoMasAlto((Jugador) jugadores.head);
        jugadores.placeNodeBeforeHeadAsHead(jugadorMayor);
        int organizados = 1;

        while (true) {
            Jugador ultimoOrganizado = (Jugador) jugadorMayor;

            if (ultimoOrganizado == jugadores.head.prev) {
                break;
            }

            jugadorMayor = buscarJugadorConDadoMasAlto((Jugador) ultimoOrganizado.next);
            jugadores.placeNodeAfterNode(jugadorMayor, ultimoOrganizado);
            organizados++;
        }

        jugadorEnTurno = (Jugador) jugadores.head;
        String texto = "EL ORDEN DE LOS JUGADORES ES:\n";

        for (int i = 1; i <= organizados; i++) {

            jugadorEnTurno.numeroJugador = i;

            switch (i) {
                case 1:
                    token1.setIcon(new ImageIcon("src/Imagenes/canon1_preview_rev_1.png"));
                    jugadorEnTurno.imagenToken = token1;
                    jugadorEnTurno.imgagenJugador = new ImageIcon("src/Imagenes/canon1_preview_rev_1.png");
                    break;
                case 2:
                    token2.setIcon(new ImageIcon("src/Imagenes/Caballo1_preview_rev_1.png"));
                    jugadorEnTurno.imagenToken = token2;
                    jugadorEnTurno.imgagenJugador = new ImageIcon("src/Imagenes/canon1_preview_rev_1.png");
                    break;
                case 3:
                    token3.setIcon(new ImageIcon("src/Imagenes/bota1_preview_rev_1.png"));
                    jugadorEnTurno.imagenToken = token3;
                    jugadorEnTurno.imgagenJugador = new ImageIcon("src/Imagenes/canon1_preview_rev_1.png");
                    break;
                case 4:
                    token4.setIcon(new ImageIcon("src/Imagenes/cosa2.png"));
                    jugadorEnTurno.imagenToken = token4;
                    jugadorEnTurno.imgagenJugador = new ImageIcon("src/Imagenes/canon1_preview_rev_1.png");
                    break;
            }

            Nodos.Casilla salida = (Nodos.Casilla) tablero.head;
            jugadorEnTurno.casillaActual = salida;
            Posicionar();
            salida.numJugadores++;

            texto = texto + jugadorEnTurno.numeroJugador + ") " + jugadorEnTurno.nombreJugador.toUpperCase() + "\n";
            jugadorEnTurno = (Jugador) jugadorEnTurno.next;
        }

        JOptionPane.showMessageDialog(null, texto);

    }

    public void Posicionar() {

        Nodos.Casilla casillaActual = (Nodos.Casilla) jugadorEnTurno.casillaActual;
        int cambioPixelX = casillaActual.primerPixelX + casillaActual.largo / 4 - 16;
        int cambioPixelY = casillaActual.primerPixelY + casillaActual.alto / 4 - 16;

        switch (casillaActual.numJugadores) {

            case 0:
                jugadorEnTurno.imagenToken.setLocation(cambioPixelX, cambioPixelY);
                break;
            case 1:
                jugadorEnTurno.imagenToken.setLocation(cambioPixelX + casillaActual.largo / 2, cambioPixelY);
                break;
            case 2:
                jugadorEnTurno.imagenToken.setLocation(cambioPixelX, cambioPixelY + casillaActual.alto / 2);
                break;
            case 3:
                jugadorEnTurno.imagenToken.setLocation(cambioPixelX + casillaActual.largo / 2, cambioPixelY + casillaActual.alto / 2);
                break;
        }

    }

    public void moverJugadorHaciaAdelante() {

        Nodos.Casilla casilla = (Nodos.Casilla) jugadorEnTurno.casillaActual;
        casilla.numJugadores--;

        Timer timer = new Timer(400, new ActionListener() {

            int i = 0;
            int fin = jugadorEnTurno.resultadoDado1 + jugadorEnTurno.resultadoDado2;

            @Override
            public void actionPerformed(ActionEvent e) {

                jugadorEnTurno.casillaActual = jugadorEnTurno.casillaActual.next;
                Nodos.Casilla casillaActual = (Nodos.Casilla) jugadorEnTurno.casillaActual;
                casillaActual.numJugadores++;

                if (casillaActual.nombre.equals("GO")) {
                    JOptionPane.showMessageDialog(null, "Recibes 200");
                    bancoPagaJugador(200);
                }

                Posicionar();
                playSound("src/Imagenes/fichas1.wav");
                i++;

                if (i == fin) {
                    ((Timer) e.getSource()).stop();
                    if (jugadorEnTurno.resultadoDado1 != jugadorEnTurno.resultadoDado2) {
                        PasarTurno.setEnabled(true);
                    } else {
                        jugadorEnTurno.contadorDobles++;
                        TirarDados.setEnabled(true);
                    }
                    actuarSegunCasilla();
                } else {
                    casillaActual.numJugadores--;
                }
            }
        });

        timer.start();
    }

    public void actuarSegunCasilla() {

        if (jugadorEnTurno.casillaActual instanceof Nodos.Propiedad) {

            accionesPropiedad();

        } else if (jugadorEnTurno.casillaActual instanceof Nodos.ArcaComunal) {

        } else if (jugadorEnTurno.casillaActual instanceof Nodos.Fortuna) {

        } else if (jugadorEnTurno.casillaActual instanceof Nodos.Impuesto) {

        } else if (jugadorEnTurno.casillaActual instanceof Nodos.VayaALaCarcel) {

        }

    }

    public void accionesPropiedad() {
        System.out.println("a");

        if (jugadorEnTurno.casillaActual instanceof Nodos.TituloDePropiedad) {

            Nodos.TituloDePropiedad propiedad = (Nodos.TituloDePropiedad) jugadorEnTurno.casillaActual;

            if (propiedad.propietario == banco) {

                int dialogButton = JOptionPane.showConfirmDialog(null, "Desea comprar esta propiedad por " + propiedad.valorVenta + "?", "COMPRAR PROPIEDAD", JOptionPane.YES_NO_OPTION);

                if (dialogButton == JOptionPane.YES_OPTION) {

                    propiedad.propietario = jugadorEnTurno;
                    jugadorEnTurno.dinero -= propiedad.valorVenta;

                }

            } else {

                pagarJugador(propiedad.propietario, propiedad.valorRenta);
                JOptionPane.showMessageDialog(null, "Le pagaste " + propiedad.valorRenta + " a " + propiedad.propietario.nombreJugador);

            }

        } else if (jugadorEnTurno.casillaActual instanceof Nodos.Estacion) {

        } else if (jugadorEnTurno.casillaActual instanceof Nodos.ServicioPublico) {

        }

    }
    
    public int contarEstacionesPropietario(Jugador jugador){
        
        Nodos.Casilla casilla = (Nodos.Casilla) tablero.head;
        int contador = 0;
        
        do{
            if((casilla instanceof Nodos.Estacion)){
                Nodos.Estacion estacion = (Nodos.Estacion) casilla;
                if (estacion.propietario == jugador){
                    contador++;
                }
            }
        } while(casilla != tablero.head);
        
        return contador;
    }
    
    public int contarServiciosPropietario(Jugador jugador){
        
        Nodos.Casilla casilla = (Nodos.Casilla) tablero.head;
        int contador = 0;
        
        do{
            if((casilla instanceof Nodos.ServicioPublico)){
                Nodos.ServicioPublico estacion = (Nodos.ServicioPublico) casilla;
                if (estacion.propietario == jugador){
                    contador++;
                }
            }
        } while(casilla != tablero.head);
        
        return contador;
    }

    public void bancoPagaJugador(int monto) {

        banco.dinero -= monto;
        jugadorEnTurno.dinero += monto;

    }

    public void pagarBanco(int monto) {

        banco.dinero += monto;
        jugadorEnTurno.dinero -= monto;

    }

    public void pagarJugador(Jugador jugadorAPagar, int monto) {

        jugadorEnTurno.dinero -= monto;
        jugadorAPagar.dinero += monto;

    }

    public void pagarJugadores(int monto) {

        Jugador jugadorAPagar = (Jugador) jugadores.head;
        do {
            if (jugadorAPagar != jugadorEnTurno) {
                jugadorAPagar.dinero += monto;
                jugadorEnTurno.dinero -= monto;
            }
        } while (jugadorAPagar != jugadores.head);

    }

    public void jugadoresPagan(int monto) {

        Jugador jugadorAPagar = (Jugador) jugadores.head;
        do {
            if (jugadorAPagar != jugadorEnTurno) {
                jugadorAPagar.dinero -= monto;
                jugadorEnTurno.dinero += monto;
            }
        } while (jugadorAPagar != jugadores.head);

    }

    public void SalirCarcel() {

        if (jugadorEnTurno.turnosCarcel == 0) {
            JOptionPane.showMessageDialog(null, "Se ha usado la carta de salir de la carcel");
        } else {
            JOptionPane.showMessageDialog(null, "Se le ha descontado 50 para salir de la carcel");
        }

    }

    public Juego() {

        initComponents();
        changeCursor();
        playMusic("src/Imagenes/Mjuego.wav");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        token1 = new javax.swing.JLabel();
        token2 = new javax.swing.JLabel();
        token3 = new javax.swing.JLabel();
        token4 = new javax.swing.JLabel();
        Tablero = new javax.swing.JLabel();
        Mediterranean = new javax.swing.JLabel();
        Baltic = new javax.swing.JLabel();
        Reading = new javax.swing.JLabel();
        Oriental = new javax.swing.JLabel();
        Vermont = new javax.swing.JLabel();
        Connecticut = new javax.swing.JLabel();
        StCharles = new javax.swing.JLabel();
        Electric = new javax.swing.JLabel();
        States = new javax.swing.JLabel();
        Virginia = new javax.swing.JLabel();
        Penn = new javax.swing.JLabel();
        StJames = new javax.swing.JLabel();
        Tennessee = new javax.swing.JLabel();
        NewYork = new javax.swing.JLabel();
        Kentucky = new javax.swing.JLabel();
        Indiana = new javax.swing.JLabel();
        Illinois = new javax.swing.JLabel();
        BO = new javax.swing.JLabel();
        Atlantic = new javax.swing.JLabel();
        Ventnor = new javax.swing.JLabel();
        Water = new javax.swing.JLabel();
        Marvin = new javax.swing.JLabel();
        Pacific = new javax.swing.JLabel();
        NorthC = new javax.swing.JLabel();
        PennAV = new javax.swing.JLabel();
        Shortline = new javax.swing.JLabel();
        Park = new javax.swing.JLabel();
        BoardWalk = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Dado1 = new javax.swing.JLabel();
        Dado2 = new javax.swing.JLabel();
        TirarDado = new javax.swing.JButton();
        TirarDados = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Dinero = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PasarTurno = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(1100, 825));
        setMinimumSize(new java.awt.Dimension(1100, 825));
        setResizable(false);
        setSize(new java.awt.Dimension(1100, 825));

        jPanel1.setMaximumSize(new java.awt.Dimension(800, 791));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 791));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 791));
        jPanel1.setLayout(null);
        jPanel1.add(token1);
        token1.setBounds(690, 695, 32, 32);
        jPanel1.add(token2);
        token2.setBounds(690, 728, 32, 32);
        jPanel1.add(token3);
        token3.setBounds(722, 698, 32, 32);
        jPanel1.add(token4);
        token4.setBounds(722, 734, 32, 32);

        Tablero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tablero2.jpg"))); // NOI18N
        Tablero.setMaximumSize(new java.awt.Dimension(800, 825));
        Tablero.setMinimumSize(new java.awt.Dimension(800, 825));
        Tablero.setPreferredSize(new java.awt.Dimension(800, 825));
        jPanel1.add(Tablero);
        Tablero.setBounds(0, 0, 800, 791);

        Mediterranean.setText("jLabel4");
        Mediterranean.setMaximumSize(new java.awt.Dimension(62, 103));
        Mediterranean.setMinimumSize(new java.awt.Dimension(62, 103));
        Mediterranean.setPreferredSize(new java.awt.Dimension(62, 103));
        Mediterranean.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MediterraneanMouseEntered(evt);
            }
        });
        jPanel1.add(Mediterranean);
        Mediterranean.setBounds(621, 679, 62, 103);

        Baltic.setText("jLabel4");
        Baltic.setMaximumSize(new java.awt.Dimension(62, 103));
        Baltic.setMinimumSize(new java.awt.Dimension(62, 103));
        Baltic.setPreferredSize(new java.awt.Dimension(62, 103));
        Baltic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BalticMouseEntered(evt);
            }
        });
        jPanel1.add(Baltic);
        Baltic.setBounds(496, 679, 62, 103);

        Reading.setText("jLabel4");
        Reading.setMaximumSize(new java.awt.Dimension(62, 103));
        Reading.setMinimumSize(new java.awt.Dimension(62, 103));
        Reading.setPreferredSize(new java.awt.Dimension(62, 103));
        Reading.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ReadingMouseEntered(evt);
            }
        });
        jPanel1.add(Reading);
        Reading.setBounds(370, 679, 62, 103);

        Oriental.setText("jLabel4");
        Oriental.setMaximumSize(new java.awt.Dimension(62, 103));
        Oriental.setMinimumSize(new java.awt.Dimension(62, 103));
        Oriental.setPreferredSize(new java.awt.Dimension(62, 103));
        Oriental.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OrientalMouseEntered(evt);
            }
        });
        jPanel1.add(Oriental);
        Oriental.setBounds(307, 679, 62, 103);

        Vermont.setText("jLabel4");
        Vermont.setMaximumSize(new java.awt.Dimension(62, 103));
        Vermont.setMinimumSize(new java.awt.Dimension(62, 103));
        Vermont.setPreferredSize(new java.awt.Dimension(62, 103));
        Vermont.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                VermontMouseEntered(evt);
            }
        });
        jPanel1.add(Vermont);
        Vermont.setBounds(181, 679, 62, 103);

        Connecticut.setText("jLabel4");
        Connecticut.setMaximumSize(new java.awt.Dimension(62, 103));
        Connecticut.setMinimumSize(new java.awt.Dimension(62, 103));
        Connecticut.setPreferredSize(new java.awt.Dimension(62, 103));
        Connecticut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ConnecticutMouseEntered(evt);
            }
        });
        jPanel1.add(Connecticut);
        Connecticut.setBounds(120, 679, 62, 103);

        StCharles.setText("jLabel4");
        StCharles.setMaximumSize(new java.awt.Dimension(62, 103));
        StCharles.setMinimumSize(new java.awt.Dimension(62, 103));
        StCharles.setPreferredSize(new java.awt.Dimension(62, 103));
        StCharles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                StCharlesMouseEntered(evt);
            }
        });
        jPanel1.add(StCharles);
        StCharles.setBounds(14, 617, 103, 62);

        Electric.setText("jLabel4");
        Electric.setMaximumSize(new java.awt.Dimension(62, 103));
        Electric.setMinimumSize(new java.awt.Dimension(62, 103));
        Electric.setPreferredSize(new java.awt.Dimension(62, 103));
        Electric.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ElectricMouseEntered(evt);
            }
        });
        jPanel1.add(Electric);
        Electric.setBounds(14, 553, 103, 62);

        States.setText("jLabel4");
        States.setMaximumSize(new java.awt.Dimension(62, 103));
        States.setMinimumSize(new java.awt.Dimension(62, 103));
        States.setPreferredSize(new java.awt.Dimension(62, 103));
        States.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                StatesMouseEntered(evt);
            }
        });
        jPanel1.add(States);
        States.setBounds(14, 491, 103, 62);

        Virginia.setText("jLabel4");
        Virginia.setMaximumSize(new java.awt.Dimension(62, 103));
        Virginia.setMinimumSize(new java.awt.Dimension(62, 103));
        Virginia.setPreferredSize(new java.awt.Dimension(62, 103));
        Virginia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                VirginiaMouseEntered(evt);
            }
        });
        jPanel1.add(Virginia);
        Virginia.setBounds(14, 428, 103, 62);

        Penn.setText("jLabel4");
        Penn.setMaximumSize(new java.awt.Dimension(62, 103));
        Penn.setMinimumSize(new java.awt.Dimension(62, 103));
        Penn.setPreferredSize(new java.awt.Dimension(62, 103));
        Penn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PennMouseEntered(evt);
            }
        });
        jPanel1.add(Penn);
        Penn.setBounds(14, 365, 103, 62);

        StJames.setText("jLabel4");
        StJames.setMaximumSize(new java.awt.Dimension(62, 103));
        StJames.setMinimumSize(new java.awt.Dimension(62, 103));
        StJames.setPreferredSize(new java.awt.Dimension(62, 103));
        StJames.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                StJamesMouseEntered(evt);
            }
        });
        jPanel1.add(StJames);
        StJames.setBounds(14, 302, 103, 62);

        Tennessee.setText("jLabel4");
        Tennessee.setMaximumSize(new java.awt.Dimension(62, 103));
        Tennessee.setMinimumSize(new java.awt.Dimension(62, 103));
        Tennessee.setPreferredSize(new java.awt.Dimension(62, 103));
        Tennessee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TennesseeMouseEntered(evt);
            }
        });
        jPanel1.add(Tennessee);
        Tennessee.setBounds(14, 176, 103, 62);

        NewYork.setText("jLabel4");
        NewYork.setMaximumSize(new java.awt.Dimension(62, 103));
        NewYork.setMinimumSize(new java.awt.Dimension(62, 103));
        NewYork.setPreferredSize(new java.awt.Dimension(62, 103));
        NewYork.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NewYorkMouseEntered(evt);
            }
        });
        jPanel1.add(NewYork);
        NewYork.setBounds(14, 115, 103, 62);

        Kentucky.setText("jLabel4");
        Kentucky.setMaximumSize(new java.awt.Dimension(62, 103));
        Kentucky.setMinimumSize(new java.awt.Dimension(62, 103));
        Kentucky.setPreferredSize(new java.awt.Dimension(62, 103));
        Kentucky.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                KentuckyMouseEntered(evt);
            }
        });
        jPanel1.add(Kentucky);
        Kentucky.setBounds(120, 9, 62, 103);

        Indiana.setText("jLabel4");
        Indiana.setMaximumSize(new java.awt.Dimension(62, 103));
        Indiana.setMinimumSize(new java.awt.Dimension(62, 103));
        Indiana.setPreferredSize(new java.awt.Dimension(62, 103));
        Indiana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                IndianaMouseEntered(evt);
            }
        });
        jPanel1.add(Indiana);
        Indiana.setBounds(244, 9, 62, 103);

        Illinois.setText("jLabel4");
        Illinois.setMaximumSize(new java.awt.Dimension(62, 103));
        Illinois.setMinimumSize(new java.awt.Dimension(62, 103));
        Illinois.setPreferredSize(new java.awt.Dimension(62, 103));
        Illinois.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                IllinoisMouseEntered(evt);
            }
        });
        jPanel1.add(Illinois);
        Illinois.setBounds(307, 9, 62, 103);

        BO.setText("jLabel4");
        BO.setMaximumSize(new java.awt.Dimension(62, 103));
        BO.setMinimumSize(new java.awt.Dimension(62, 103));
        BO.setPreferredSize(new java.awt.Dimension(62, 103));
        BO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BOMouseEntered(evt);
            }
        });
        jPanel1.add(BO);
        BO.setBounds(370, 9, 62, 103);

        Atlantic.setText("jLabel4");
        Atlantic.setMaximumSize(new java.awt.Dimension(62, 103));
        Atlantic.setMinimumSize(new java.awt.Dimension(62, 103));
        Atlantic.setPreferredSize(new java.awt.Dimension(62, 103));
        Atlantic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AtlanticMouseEntered(evt);
            }
        });
        jPanel1.add(Atlantic);
        Atlantic.setBounds(433, 9, 62, 103);

        Ventnor.setText("jLabel4");
        Ventnor.setMaximumSize(new java.awt.Dimension(62, 103));
        Ventnor.setMinimumSize(new java.awt.Dimension(62, 103));
        Ventnor.setPreferredSize(new java.awt.Dimension(62, 103));
        Ventnor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                VentnorMouseEntered(evt);
            }
        });
        jPanel1.add(Ventnor);
        Ventnor.setBounds(496, 9, 62, 103);

        Water.setText("jLabel4");
        Water.setMaximumSize(new java.awt.Dimension(62, 103));
        Water.setMinimumSize(new java.awt.Dimension(62, 103));
        Water.setPreferredSize(new java.awt.Dimension(62, 103));
        Water.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                WaterMouseEntered(evt);
            }
        });
        jPanel1.add(Water);
        Water.setBounds(559, 9, 62, 103);

        Marvin.setText("jLabel4");
        Marvin.setMaximumSize(new java.awt.Dimension(62, 103));
        Marvin.setMinimumSize(new java.awt.Dimension(62, 103));
        Marvin.setPreferredSize(new java.awt.Dimension(62, 103));
        Marvin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MarvinMouseEntered(evt);
            }
        });
        jPanel1.add(Marvin);
        Marvin.setBounds(623, 9, 62, 103);

        Pacific.setText("jLabel4");
        Pacific.setMaximumSize(new java.awt.Dimension(62, 103));
        Pacific.setMinimumSize(new java.awt.Dimension(62, 103));
        Pacific.setPreferredSize(new java.awt.Dimension(62, 103));
        Pacific.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PacificMouseEntered(evt);
            }
        });
        jPanel1.add(Pacific);
        Pacific.setBounds(685, 115, 103, 62);

        NorthC.setText("jLabel4");
        NorthC.setMaximumSize(new java.awt.Dimension(62, 103));
        NorthC.setMinimumSize(new java.awt.Dimension(62, 103));
        NorthC.setPreferredSize(new java.awt.Dimension(62, 103));
        NorthC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NorthCMouseEntered(evt);
            }
        });
        jPanel1.add(NorthC);
        NorthC.setBounds(685, 176, 103, 62);

        PennAV.setText("jLabel4");
        PennAV.setMaximumSize(new java.awt.Dimension(62, 103));
        PennAV.setMinimumSize(new java.awt.Dimension(62, 103));
        PennAV.setPreferredSize(new java.awt.Dimension(62, 103));
        PennAV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PennAVMouseEntered(evt);
            }
        });
        jPanel1.add(PennAV);
        PennAV.setBounds(685, 302, 103, 62);

        Shortline.setText("jLabel4");
        Shortline.setMaximumSize(new java.awt.Dimension(62, 103));
        Shortline.setMinimumSize(new java.awt.Dimension(62, 103));
        Shortline.setPreferredSize(new java.awt.Dimension(62, 103));
        Shortline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ShortlineMouseEntered(evt);
            }
        });
        jPanel1.add(Shortline);
        Shortline.setBounds(685, 365, 103, 62);

        Park.setText("jLabel4");
        Park.setMaximumSize(new java.awt.Dimension(62, 103));
        Park.setMinimumSize(new java.awt.Dimension(62, 103));
        Park.setPreferredSize(new java.awt.Dimension(62, 103));
        Park.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ParkMouseEntered(evt);
            }
        });
        jPanel1.add(Park);
        Park.setBounds(685, 493, 103, 62);

        BoardWalk.setText("jLabel4");
        BoardWalk.setMaximumSize(new java.awt.Dimension(62, 103));
        BoardWalk.setMinimumSize(new java.awt.Dimension(62, 103));
        BoardWalk.setPreferredSize(new java.awt.Dimension(62, 103));
        BoardWalk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BoardWalkMouseEntered(evt);
            }
        });
        jPanel1.add(BoardWalk);
        BoardWalk.setBounds(685, 616, 103, 62);

        jPanel2.setBackground(new java.awt.Color(218, 255, 236));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Dado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/D1.png"))); // NOI18N
        Dado1.setOpaque(true);
        jPanel2.add(Dado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 126, 127));

        Dado2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/D1.png"))); // NOI18N
        jPanel2.add(Dado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 10, -1, -1));

        TirarDado.setText("Tirar Dado");
        TirarDado.setPreferredSize(new java.awt.Dimension(94, 25));
        TirarDado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TirarDadoActionPerformed(evt);
            }
        });
        jPanel2.add(TirarDado, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 143, 100, -1));

        TirarDados.setText("Tirar Dados");
        TirarDados.setEnabled(false);
        TirarDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TirarDadosActionPerformed(evt);
            }
        });
        jPanel2.add(TirarDados, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 143, -1, -1));

        jPanel3.setBackground(new java.awt.Color(218, 255, 236));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel5.setBackground(new java.awt.Color(204, 255, 204));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cabeza1.1.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Jugador 1");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        Dinero.setText("Dinero");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Dinero, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Dinero, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );

        jPanel4.setBackground(new java.awt.Color(218, 255, 236));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/AtlanticAvenue.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PasarTurno.setText("Pasar Turno");
        PasarTurno.setEnabled(false);
        PasarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasarTurnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(PasarTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PasarTurno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TirarDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TirarDadosActionPerformed

        TirarDados.setEnabled(false);
        Random random = new Random();
        int dado1 = 1 + random.nextInt(6);
        int dado2 = 1 + random.nextInt(6);
        jugadorEnTurno.resultadoDado1 = dado1;
        jugadorEnTurno.resultadoDado2 = dado2;
        cambiarImagenDado(dado1, Dado1);
        cambiarImagenDado(dado2, Dado2);
        playSound("src/Imagenes/Mdados.wav");

        if ((dado1 == dado2) && (jugadorEnTurno.contadorDobles == 2)) {
            jugadorEnTurno.contadorDobles = 0;
            jugadorEnTurno.enCarcel = true;
            jugadorEnTurno.casillaActual = tablero.carcel;
            Posicionar();
        } else {
            moverJugadorHaciaAdelante();
        }

    }//GEN-LAST:event_TirarDadosActionPerformed

    private void TirarDadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TirarDadoActionPerformed

        Random random = new Random();
        int dado1 = 1 + random.nextInt(6);
        cambiarImagenDado(dado1, Dado1);
        playSound("src/Imagenes/Mdados.wav");

        jugadorEnTurno.resultadoDado1 = dado1;
        jugadorEnTurno = (Jugador) jugadorEnTurno.next;

        if (jugadorEnTurno == (Jugador) jugadores.head) {
            organizarJugadores();
            jPanel2.remove(TirarDado);
            TirarDados.setEnabled(true);
        }

    }//GEN-LAST:event_TirarDadoActionPerformed

    private void MediterraneanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MediterraneanMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/MediterraneanAvenue.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_MediterraneanMouseEntered

    private void BalticMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BalticMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/BalticAvenue.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_BalticMouseEntered

    private void ReadingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReadingMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/ReadingRailroad.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_ReadingMouseEntered

    private void OrientalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrientalMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/OrientalAvenue.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_OrientalMouseEntered

    private void VermontMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VermontMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/VermontAvenue.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_VermontMouseEntered

    private void ConnecticutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConnecticutMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/ConnecticutAvenue.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_ConnecticutMouseEntered

    private void StCharlesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StCharlesMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/StCharlesPlace.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_StCharlesMouseEntered

    private void ElectricMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ElectricMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/ElectricCompany.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_ElectricMouseEntered

    private void StatesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StatesMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/StatesAvenue.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_StatesMouseEntered

    private void VirginiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VirginiaMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/VirginiaAvenue.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_VirginiaMouseEntered

    private void PennMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PennMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/PennsylvaniaRailRoad.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_PennMouseEntered

    private void StJamesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StJamesMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/StJamesPlace.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_StJamesMouseEntered

    private void TennesseeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TennesseeMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/TennesseeAvenue.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_TennesseeMouseEntered

    private void NewYorkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewYorkMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/NewYorkAvenue.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_NewYorkMouseEntered

    private void KentuckyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KentuckyMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/KentuckyAvenue.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_KentuckyMouseEntered

    private void IndianaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IndianaMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/IndianaAvenue.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_IndianaMouseEntered

    private void IllinoisMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IllinoisMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/IllinoisAvenue.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_IllinoisMouseEntered

    private void BOMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BOMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/BORailroad.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_BOMouseEntered

    private void AtlanticMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AtlanticMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/AtlanticAvenue.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_AtlanticMouseEntered

    private void VentnorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VentnorMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/VentnorAvenue.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_VentnorMouseEntered

    private void WaterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WaterMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/WaterWorks.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_WaterMouseEntered

    private void MarvinMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MarvinMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/MarvinGardens.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_MarvinMouseEntered

    private void PacificMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PacificMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/PacificAvenue.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_PacificMouseEntered

    private void NorthCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NorthCMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/NorthCarolinaAvenue.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_NorthCMouseEntered

    private void PennAVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PennAVMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/PennsylvaniaAvenue.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_PennAVMouseEntered

    private void ShortlineMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShortlineMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/ShortLineRailroad.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_ShortlineMouseEntered

    private void ParkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ParkMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/ParkPlace.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_ParkMouseEntered

    private void BoardWalkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BoardWalkMouseEntered
        ImageIcon propiedad = new ImageIcon("src/Imagenes/Boardwalk.jpg");
        jLabel1.setIcon(propiedad);
    }//GEN-LAST:event_BoardWalkMouseEntered

    private void PasarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasarTurnoActionPerformed
        jugadorEnTurno = (Jugador) jugadorEnTurno.next;
        PasarTurno.setEnabled(false);
        TirarDados.setEnabled(true);
    }//GEN-LAST:event_PasarTurnoActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Juego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Atlantic;
    private javax.swing.JLabel BO;
    private javax.swing.JLabel Baltic;
    private javax.swing.JLabel BoardWalk;
    private javax.swing.JLabel Connecticut;
    private javax.swing.JLabel Dado1;
    private javax.swing.JLabel Dado2;
    private javax.swing.JLabel Dinero;
    private javax.swing.JLabel Electric;
    private javax.swing.JLabel Illinois;
    private javax.swing.JLabel Indiana;
    private javax.swing.JLabel Kentucky;
    private javax.swing.JLabel Marvin;
    private javax.swing.JLabel Mediterranean;
    private javax.swing.JLabel NewYork;
    private javax.swing.JLabel NorthC;
    private javax.swing.JLabel Oriental;
    private javax.swing.JLabel Pacific;
    private javax.swing.JLabel Park;
    private javax.swing.JButton PasarTurno;
    private javax.swing.JLabel Penn;
    private javax.swing.JLabel PennAV;
    private javax.swing.JLabel Reading;
    private javax.swing.JLabel Shortline;
    private javax.swing.JLabel StCharles;
    private javax.swing.JLabel StJames;
    private javax.swing.JLabel States;
    private javax.swing.JLabel Tablero;
    private javax.swing.JLabel Tennessee;
    private javax.swing.JButton TirarDado;
    private javax.swing.JButton TirarDados;
    private javax.swing.JLabel Ventnor;
    private javax.swing.JLabel Vermont;
    private javax.swing.JLabel Virginia;
    private javax.swing.JLabel Water;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel token1;
    private javax.swing.JLabel token2;
    private javax.swing.JLabel token3;
    private javax.swing.JLabel token4;
    // End of variables declaration//GEN-END:variables
}
