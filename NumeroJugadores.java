package monopolio;

import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static monopolio.Menu.clip;

public final class NumeroJugadores extends javax.swing.JFrame {

    public static Listas.CDLL jugadores;

    public void changeCursor() {

        try {

            setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/Imagenes/cursor2.png").getImage(), new Point(0, 0), "My cursor"));

        } catch (HeadlessException | IndexOutOfBoundsException e) {
        }

    }

    public void crearJugadores(int numJugadores) {

        jugadores = new Listas.CDLL();

        for (int i = 1; i <= numJugadores; i++) {

            String nombreJugador;
            while (true) {
                nombreJugador = JOptionPane.showInputDialog("Jugador " + i + ":");
                if ((nombreJugador != null) && (!nombreJugador.trim().equals(""))) {
                    break;
                }
            }

            jugadores.insertNode(new Jugador(nombreJugador, i));

        }

    }

    public NumeroJugadores() {

        initComponents();
        changeCursor();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Jugadores2 = new javax.swing.JButton();
        Jugadores3 = new javax.swing.JButton();
        Jugadores4 = new javax.swing.JButton();
        Caminando1 = new javax.swing.JLabel();
        Caminando2 = new javax.swing.JLabel();
        Caminando3 = new javax.swing.JLabel();
        Caminando4 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(960, 540));
        setMinimumSize(new java.awt.Dimension(960, 540));
        setResizable(false);
        setSize(new java.awt.Dimension(960, 540));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(960, 540));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Jugadores2.setText("2 Jugadores");
        Jugadores2.setMaximumSize(new java.awt.Dimension(100, 25));
        Jugadores2.setMinimumSize(new java.awt.Dimension(100, 25));
        Jugadores2.setPreferredSize(new java.awt.Dimension(100, 25));
        Jugadores2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Jugadores2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Jugadores2MouseExited(evt);
            }
        });
        Jugadores2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jugadores2ActionPerformed(evt);
            }
        });
        jPanel1.add(Jugadores2, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 170, 170, 50));

        Jugadores3.setText("3 Jugadores");
        Jugadores3.setMaximumSize(new java.awt.Dimension(100, 25));
        Jugadores3.setMinimumSize(new java.awt.Dimension(100, 25));
        Jugadores3.setPreferredSize(new java.awt.Dimension(100, 25));
        Jugadores3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Jugadores3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Jugadores3MouseExited(evt);
            }
        });
        Jugadores3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jugadores3ActionPerformed(evt);
            }
        });
        jPanel1.add(Jugadores3, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 230, 170, 50));

        Jugadores4.setText("4 Jugadores");
        Jugadores4.setMaximumSize(new java.awt.Dimension(170, 50));
        Jugadores4.setMinimumSize(new java.awt.Dimension(170, 50));
        Jugadores4.setPreferredSize(new java.awt.Dimension(170, 50));
        Jugadores4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Jugadores4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Jugadores4MouseExited(evt);
            }
        });
        Jugadores4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jugadores4ActionPerformed(evt);
            }
        });
        jPanel1.add(Jugadores4, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 290, 170, 50));

        Caminando1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Caminando1.setMaximumSize(new java.awt.Dimension(46, 73));
        Caminando1.setMinimumSize(new java.awt.Dimension(46, 73));
        Caminando1.setPreferredSize(new java.awt.Dimension(46, 73));
        jPanel1.add(Caminando1, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 403, 46, 73));

        Caminando2.setMaximumSize(new java.awt.Dimension(46, 73));
        Caminando2.setMinimumSize(new java.awt.Dimension(46, 73));
        Caminando2.setPreferredSize(new java.awt.Dimension(46, 73));
        jPanel1.add(Caminando2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 403, 46, 73));

        Caminando3.setMaximumSize(new java.awt.Dimension(46, 73));
        Caminando3.setMinimumSize(new java.awt.Dimension(46, 73));
        Caminando3.setPreferredSize(new java.awt.Dimension(46, 73));
        jPanel1.add(Caminando3, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 403, 46, 73));

        Caminando4.setMaximumSize(new java.awt.Dimension(46, 73));
        Caminando4.setMinimumSize(new java.awt.Dimension(46, 73));
        Caminando4.setPreferredSize(new java.awt.Dimension(46, 73));
        jPanel1.add(Caminando4, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 403, 46, 73));

        fondo.setBackground(new java.awt.Color(255, 255, 255));
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/PosibleFondo1.2.png"))); // NOI18N
        jPanel1.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 540));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Jugadores2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jugadores2MouseEntered

        ImageIcon gifCaminando = new ImageIcon("src/Imagenes/GifCaminando2.gif");
        Caminando1.setIcon(gifCaminando);
        Caminando2.setIcon(gifCaminando);

    }//GEN-LAST:event_Jugadores2MouseEntered

    private void Jugadores3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jugadores3MouseEntered

        ImageIcon gifCaminando = new ImageIcon("src/Imagenes/GifCaminando2.gif");
        Caminando1.setIcon(gifCaminando);
        Caminando2.setIcon(gifCaminando);
        Caminando3.setIcon(gifCaminando);

    }//GEN-LAST:event_Jugadores3MouseEntered

    private void Jugadores4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jugadores4MouseEntered

        ImageIcon gifCaminando = new ImageIcon("src/Imagenes/GifCaminando2.gif");
        Caminando1.setIcon(gifCaminando);
        Caminando2.setIcon(gifCaminando);
        Caminando3.setIcon(gifCaminando);
        Caminando4.setIcon(gifCaminando);

    }//GEN-LAST:event_Jugadores4MouseEntered

    private void Jugadores2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jugadores2MouseExited

        Caminando1.setIcon(null);
        Caminando2.setIcon(null);

    }//GEN-LAST:event_Jugadores2MouseExited

    private void Jugadores3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jugadores3MouseExited

        Caminando1.setIcon(null);
        Caminando2.setIcon(null);
        Caminando3.setIcon(null);

    }//GEN-LAST:event_Jugadores3MouseExited

    private void Jugadores4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jugadores4MouseExited

        Caminando1.setIcon(null);
        Caminando2.setIcon(null);
        Caminando3.setIcon(null);
        Caminando4.setIcon(null);

    }//GEN-LAST:event_Jugadores4MouseExited

    private void Jugadores2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jugadores2ActionPerformed

        crearJugadores(2);
        this.dispose();
        clip.stop();
        new Juego().setVisible(true);

    }//GEN-LAST:event_Jugadores2ActionPerformed

    private void Jugadores3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jugadores3ActionPerformed

        crearJugadores(3);
        this.dispose();
        clip.stop();
        new Juego().setVisible(true);

    }//GEN-LAST:event_Jugadores3ActionPerformed

    private void Jugadores4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jugadores4ActionPerformed

        crearJugadores(4);
        this.dispose();
        clip.stop();
        new Juego().setVisible(true);

    }//GEN-LAST:event_Jugadores4ActionPerformed

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
            java.util.logging.Logger.getLogger(NumeroJugadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NumeroJugadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NumeroJugadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NumeroJugadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NumeroJugadores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Caminando1;
    private javax.swing.JLabel Caminando2;
    private javax.swing.JLabel Caminando3;
    private javax.swing.JLabel Caminando4;
    private javax.swing.JButton Jugadores2;
    private javax.swing.JButton Jugadores3;
    private javax.swing.JButton Jugadores4;
    private javax.swing.JLabel fondo;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
