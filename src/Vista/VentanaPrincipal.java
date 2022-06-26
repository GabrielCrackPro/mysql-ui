package Vista;

import Modelo.Conectar;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

public class VentanaPrincipal
  extends JFrame
  implements ActionListener, ItemListener {

  private JMenuBar barraMenu = new JMenuBar();
  private JMenu menuServidor, menuBD, menuTabla;
  public static JMenuItem itemServidorConectar, itemServidorDesconectar, itemBDGestion, itemTablaMostrar;

  private JPanel panelDatosConexion, panelBD, panelTabla, panelMostrarTabla, panelInformacion;

  private static JLabel labelUsuario, labelPuerto;
  public static JLabel labelEstado, labelInformacion, labelNombreBD;

  public static JTextField txtNombreBD;
  private JTextField txtNombreTabla;

  public static JComboBox<String> comboBoxTabla;

  public static JTable tabla;

  public VentanaPrincipal() {
    setTitle("Aplicación de Base de Datos");
    setSize(1000, 450);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(null);

    // Menu

    menuServidor = new JMenu("Servidor");
    menuBD = new JMenu("Base de Datos");
    menuTabla = new JMenu("Tabla");

    itemServidorConectar = new JMenuItem("Conectar");
    itemServidorConectar.setEnabled(true);
    itemServidorDesconectar = new JMenuItem("Desconectar");
    itemServidorDesconectar.setEnabled(false);

    itemBDGestion = new JMenuItem("Gestionar");
    itemBDGestion.setEnabled(false);

    itemTablaMostrar = new JMenuItem("Mostrar");
    itemTablaMostrar.setEnabled(false);

    menuServidor.add(itemServidorConectar);
    menuServidor.add(itemServidorDesconectar);

    menuBD.add(itemBDGestion);

    menuTabla.add(itemTablaMostrar);

    barraMenu.add(menuServidor);
    barraMenu.add(menuBD);
    barraMenu.add(menuTabla);

    setJMenuBar(barraMenu);

    // Paneles

    // Panel de datos de conexión

    panelDatosConexion = new JPanel();
    panelDatosConexion.setBackground(Color.WHITE);
    panelDatosConexion.setBounds(0, 0, 1000, 50);
    panelDatosConexion.setLayout(new GridLayout(1, 3));
    panelDatosConexion.setBorder(
      BorderFactory.createTitledBorder("Datos de la conexión")
    );
    JLabel labelUsuarioTxt = new JLabel("Usuario:");
    JLabel labelPuertoTxt = new JLabel("Puerto:");
    JLabel labelEstadoTxt = new JLabel("Estado:");

    labelUsuario = new JLabel("");
    labelPuerto = new JLabel("");
    labelEstado = new JLabel("Desconectado");

    labelEstado.setForeground(Color.RED);
    labelEstado.setFont(new Font("Arial", Font.BOLD, 14));

    labelUsuario.setForeground(Color.BLUE);
    labelPuerto.setForeground(Color.BLUE);

    panelDatosConexion.add(labelUsuarioTxt);
    panelDatosConexion.add(labelUsuario);
    panelDatosConexion.add(labelPuertoTxt);
    panelDatosConexion.add(labelPuerto);
    panelDatosConexion.add(labelEstadoTxt);
    panelDatosConexion.add(labelEstado);

    add(panelDatosConexion);

    // Panel de base de datos

    panelBD = new JPanel();
    panelBD.setBackground(Color.WHITE);
    panelBD.setLayout(null);
    panelBD.setBounds(0, 50, 1000, 50);
    panelBD.setBorder(BorderFactory.createTitledBorder("Base de Datos"));
    JLabel labelNombreBDTxt = new JLabel("Nombre:");
    labelNombreBDTxt.setBounds(10, 20, 100, 15);
    txtNombreBD = new JTextField();
    txtNombreBD.setBounds(70, 20, 800, 20);
    labelNombreBD = new JLabel("");
    labelNombreBD.setBounds(890, 20, 100, 20);
    labelNombreBD.setForeground(Color.BLUE);
    panelBD.add(labelNombreBDTxt);
    panelBD.add(txtNombreBD);
    panelBD.add(labelNombreBD);

    add(panelBD);

    // Panel de tabla

    panelTabla = new JPanel();
    panelTabla.setBackground(Color.WHITE);
    panelTabla.setLayout(null);
    panelTabla.setBounds(0, 100, 1000, 50);
    panelTabla.setBorder(BorderFactory.createTitledBorder("Tabla"));
    JLabel labelNombreTablaTxt = new JLabel("Nombre:");
    labelNombreTablaTxt.setBounds(10, 20, 100, 15);
    txtNombreTabla = new JTextField();
    txtNombreTabla.setBounds(70, 20, 900, 20);
    panelTabla.add(labelNombreTablaTxt);
    panelTabla.add(txtNombreTabla);

    add(panelTabla);

    // Panel de mostrar tabla

    panelMostrarTabla = new JPanel();
    panelMostrarTabla.setLayout(null);
    panelMostrarTabla.setBackground(Color.WHITE);
    panelMostrarTabla.setBounds(0, 150, 1000, 200);
    panelMostrarTabla.setBorder(
      BorderFactory.createTitledBorder("Mostrar tabla")
    );
    panelMostrarTabla.setLayout(null);
    comboBoxTabla = new JComboBox<String>();
    tabla = new JTable();
    comboBoxTabla.setBounds(10, 20, 140, 20);
    comboBoxTabla.setEnabled(false);
    comboBoxTabla.setCursor(new Cursor(Cursor.HAND_CURSOR));
    panelMostrarTabla.add(comboBoxTabla);
    JScrollPane scroll = new JScrollPane(tabla);
    scroll.setBounds(150, 20, 800, 160);
    panelMostrarTabla.add(scroll);

    add(panelMostrarTabla);

    // Panel de información

    panelInformacion = new JPanel();
    panelInformacion.setBackground(Color.WHITE);
    panelInformacion.setLayout(null);
    panelInformacion.setBounds(0, 350, 1000, 50);
    panelInformacion.setBorder(BorderFactory.createTitledBorder("Información"));
    labelInformacion = new JLabel("");
    labelInformacion.setBounds(10, 20, 900, 15);
    labelInformacion.setFont(new Font("Arial", Font.BOLD, 12));
    labelInformacion.setHorizontalAlignment(JLabel.CENTER);
    panelInformacion.add(labelInformacion);

    add(panelInformacion);

    // Eventos

    itemServidorConectar.addActionListener(this);
    itemServidorDesconectar.addActionListener(this);

    itemBDGestion.addActionListener(this);

    itemTablaMostrar.addActionListener(this);

    comboBoxTabla.addItemListener(this);
  }

  private void mostrarTabla() throws SQLException {
    String nombreTabla = txtNombreTabla.getText();
    if (nombreTabla.equals("")) {
      labelInformacion.setForeground(Color.RED);
      labelInformacion.setText("Introduzca el nombre de la tabla");
    } else {
      labelInformacion.setForeground(Color.BLACK);
      Conectar.mostrarTabla(nombreTabla);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == itemServidorConectar) {
      new SubventanaConexion();
    } else if (e.getSource() == itemServidorDesconectar) {
      try {
        Conectar.desconectar();
      } catch (SQLException ex) {}
    } else if (e.getSource() == itemTablaMostrar) {
      try {
        mostrarTabla();
      } catch (SQLException ex) {}
    } else if (e.getSource() == itemBDGestion) {
      new SubventanaGestionBD();
    }
  }

  public static void setUsuario(String usuario) {
    labelUsuario.setText(usuario);
  }

  public static void setPuerto(String puerto) {
    labelPuerto.setText(puerto);
  }

  public static void setEstado(String estado) {
    labelEstado.setText(estado);
  }

  public static void setNombreBD(String string) {
    labelNombreBD.setText(string);
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    if (e.getSource() == comboBoxTabla) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        String nombreTabla = (String) comboBoxTabla.getSelectedItem();
        if (nombreTabla.equals("")) {
          labelInformacion.setText("Introduzca el nombre de la tabla");
        } else {
          Conectar.mostrarTabla(nombreTabla);
        }
      }
    }
  }
}
