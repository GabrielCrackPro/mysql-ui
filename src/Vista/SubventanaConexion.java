package Vista;

import Modelo.Conectar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SubventanaConexion extends JFrame implements ActionListener {

  private JTextField txtUsuario, txtPuerto;
  private JPasswordField txtPassword;
  private JButton btnConectar, btnAceptar;
  private JPanel panelFormulario;
  private JPanel panelBotones;
  private JLabel labelUsuario, labelPassword, labelPuerto;
  public static JPanel panelEstado;
  public static JLabel labelEstado;

  public SubventanaConexion() {
    setTitle("Servidor");
    setSize(500, 280);
    setLocationRelativeTo(null);
    setResizable(false);
    setLayout(null);

    // Paneles
    panelFormulario = new JPanel();
    panelFormulario.setBackground(Color.WHITE);
    panelFormulario.setBounds(0, 0, 500, 150);
    panelFormulario.setLayout(null);
    labelUsuario = new JLabel("Usuario:");
    labelPassword = new JLabel("Password:");
    labelPuerto = new JLabel("Puerto:");

    txtUsuario = new JTextField();
    txtPassword = new JPasswordField();
    txtPuerto = new JTextField();

    labelUsuario.setBounds(10, 10, 100, 20);
    labelPassword.setBounds(10, 40, 100, 20);
    labelPuerto.setBounds(10, 70, 100, 20);

    txtUsuario.setBounds(110, 10, 360, 30);
    txtPassword.setBounds(110, 40, 360, 30);
    txtPuerto.setBounds(110, 70, 360, 30);

    panelFormulario.add(labelUsuario);
    panelFormulario.add(txtUsuario);
    panelFormulario.add(labelPassword);
    panelFormulario.add(txtPassword);
    panelFormulario.add(labelPuerto);
    panelFormulario.add(txtPuerto);
    add(panelFormulario);

    panelEstado = new JPanel();
    panelEstado.setBackground(Color.WHITE);
    panelEstado.setBounds(0, 150, 500, 50);
    panelEstado.setLayout(new GridLayout(1, 2));
    labelEstado = new JLabel("Desconectado");
    labelEstado.setHorizontalAlignment(SwingConstants.CENTER);
    labelEstado.setFont(new Font("Arial", Font.BOLD, 20));

    panelEstado.setBackground(Color.RED);
    panelEstado.add(labelEstado);
    add(panelEstado);

    panelBotones = new JPanel();
    panelBotones.setBackground(Color.WHITE);
    panelBotones.setBounds(0, 200, 500, 50);
    panelBotones.setLayout(new GridLayout(1, 2));
    btnConectar = new JButton("Conectar");
    btnAceptar = new JButton("Aceptar");
    panelBotones.add(btnConectar);
    panelBotones.add(btnAceptar);
    add(panelBotones);
    setVisible(true);

    btnConectar.addActionListener(this);
    btnAceptar.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnConectar) {
      Conectar c = new Conectar();
      c.conectar(
        txtUsuario.getText(),
        String.valueOf(txtPassword.getPassword()),
        txtPuerto.getText()
      );
    } else if (e.getSource() == btnAceptar) {
      dispose();
    }
  }
}
