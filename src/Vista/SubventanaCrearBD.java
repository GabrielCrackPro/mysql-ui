package Vista;

import Modelo.Conectar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SubventanaCrearBD extends JFrame implements ActionListener {

  private JTextField txtNombreBD;
  private JButton btnCrear, btnAceptar;
  private JPanel panelFormulario, panelBotones;
  private JLabel labelNombreBD;

  public SubventanaCrearBD() {
    setTitle("Crear Base de Datos");
    setSize(500, 200);
    setLocationRelativeTo(null);
    setResizable(false);
    setLayout(null);

    // Paneles
    panelFormulario = new JPanel();
    panelFormulario.setBackground(Color.WHITE);
    panelFormulario.setBounds(0, 0, 500, 100);
    panelFormulario.setLayout(null);

    labelNombreBD = new JLabel("Nombre: ");
    txtNombreBD = new JTextField();

    labelNombreBD.setBounds(10, 10, 200, 20);
    txtNombreBD.setBounds(110, 10, 360, 30);

    panelFormulario.add(labelNombreBD);
    panelFormulario.add(txtNombreBD);

    add(panelFormulario);

    panelBotones = new JPanel();
    panelBotones.setBackground(Color.WHITE);
    panelBotones.setBounds(0, 100, 500, 100);
    panelBotones.setLayout(new GridLayout(1, 2));

    btnCrear = new JButton("Crear");
    btnAceptar = new JButton("Aceptar");
    panelBotones.add(btnCrear);
    panelBotones.add(btnAceptar);

    add(panelBotones);

    btnCrear.addActionListener(this);
    btnAceptar.addActionListener(this);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnCrear) {
      String nombreBD = txtNombreBD.getText();
      Conectar.crearBD(nombreBD);
    } else if (e.getSource() == btnAceptar) {
      dispose();
    }
  }
}
