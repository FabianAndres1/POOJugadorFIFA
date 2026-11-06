package Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal {
    private JPanel panelPrincipal;
    private JTextField txtNombre;
    private JTextField txtAnio;
    private JTextField txtMes;
    private JTextField txtDia;
    private JButton btnGuardar;

    public VentanaPrincipal() {
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 1. Capturar los datos de los campos de texto
                    String nombre = txtNombre.getText();
                    int anio = Integer.parseInt(txtAnio.getText());
                    int mes = Integer.parseInt(txtMes.getText());
                    int dia = Integer.parseInt(txtDia.getText());

                    // 2. Generar ID aleatorio
                    java.util.Random rnd = new java.util.Random();
                    String idRandom = String.valueOf(rnd.nextInt(90) + 10);

                    // 3. Crear el objeto Jugador y guardarlo usando tu Operation
                    models.Jugador nuevoJugador = new models.Jugador(idRandom, nombre, java.time.LocalDate.of(anio, mes, dia));
                    operations.Operation.savePlayer(nuevoJugador);

                    // 4. Mostrar mensaje de éxito y limpiar las cajas
                    javax.swing.JOptionPane.showMessageDialog(null, "Jugador guardado en CSV exitosamente.");
                    txtNombre.setText("");
                    txtAnio.setText("");
                    txtMes.setText("");
                    txtDia.setText("");

                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Error: Verifica que la fecha sean solo números.");
                }
            }
        });
    }
    public static void main(String[] args) {
        javax.swing.JFrame frame = new javax.swing.JFrame("Crear Jugador - GUI");
        frame.setContentPane(new VentanaPrincipal().panelPrincipal);
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centra la ventana
        frame.setVisible(true);
    }
}
