package Modelo;

import Vista.SubventanaConexion;
import Vista.SubventanaGestionBD;
import Vista.VentanaPrincipal;
import java.awt.Color;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;

public class Conectar {

  private static String usuario, puerto, estado;
  private static Connection conexion;

  public Conectar() {}

  public void conectar(String usuario, String password, String puerto) {
    try {
      if (usuario.isEmpty() || password.isEmpty() || puerto.isEmpty()) {
        SubventanaConexion.labelEstado.setBackground(Color.RED);
        SubventanaConexion.labelEstado.setText("Introduce todos los datos");
        return;
      }
      conexion =
        DriverManager.getConnection(
          "jdbc:mysql://localhost:" + puerto + "/",
          usuario,
          password
        );
      SubventanaConexion.labelEstado.setText("Conectado");
      SubventanaConexion.panelEstado.setBackground(Color.GREEN);
      VentanaPrincipal.itemServidorConectar.setEnabled(false);
      VentanaPrincipal.itemServidorDesconectar.setEnabled(true);
      VentanaPrincipal.itemBDGestion.setEnabled(true);
      VentanaPrincipal.itemTablaMostrar.setEnabled(true);
      VentanaPrincipal.setUsuario(usuario);
      VentanaPrincipal.setPuerto(puerto);
      VentanaPrincipal.setEstado("Conectado");
      VentanaPrincipal.labelEstado.setForeground(Color.GREEN);
      VentanaPrincipal.labelInformacion.setText("Conectado al servidor");
    } catch (SQLException e) {
      SubventanaConexion.labelEstado.setText("Error al conectar");
      VentanaPrincipal.labelInformacion.setText("Error al conectar");
    }
  }

  public static void desconectar() throws SQLException {
    VentanaPrincipal.setUsuario("");
    VentanaPrincipal.setPuerto("");
    VentanaPrincipal.setEstado("Desconectado");
    VentanaPrincipal.setNombreBD("");
    VentanaPrincipal.txtNombreBD.setText("");
    VentanaPrincipal.labelEstado.setForeground(Color.RED);

    VentanaPrincipal.comboBoxTabla.removeAllItems();
    VentanaPrincipal.comboBoxTabla.setEnabled(false);
    VentanaPrincipal.tabla.setModel(new DefaultTableModel());

    VentanaPrincipal.labelInformacion.setText("Desconectado del servidor");
    VentanaPrincipal.itemServidorConectar.setEnabled(true);
    VentanaPrincipal.itemServidorDesconectar.setEnabled(false);
    VentanaPrincipal.itemTablaMostrar.setEnabled(false);
    conexion.close();
  }

  public static void abrirBD(String nombreBD) {
    try {
      conexion.setCatalog(nombreBD);
      VentanaPrincipal.labelInformacion.setText(
        "Base de datos " + nombreBD + " seleccionada"
      );
      VentanaPrincipal.labelNombreBD.setText(nombreBD);
      Statement s = conexion.createStatement();
      ResultSet rs = s.executeQuery("SHOW TABLES");
      while (rs.next()) {
        VentanaPrincipal.comboBoxTabla.addItem(rs.getString(1));
      }
      VentanaPrincipal.comboBoxTabla.setEnabled(true);
      VentanaPrincipal.labelInformacion.setText(
        "Base de datos " + nombreBD + " abierta"
      );
    } catch (SQLException e) {
      VentanaPrincipal.labelInformacion.setText(
        "Error al abrir la base de datos " + nombreBD
      );
    }
  }

  public static void mostrarTabla(String nombreTabla) {
    try {
      Statement sentencia = conexion.createStatement();
      ResultSet resultado = sentencia.executeQuery(
        "SELECT * FROM " + nombreTabla
      );
      ResultSetMetaData metaDatos = resultado.getMetaData();
      int numColumnas = metaDatos.getColumnCount();
      String[] nombreColumnas = new String[numColumnas];
      for (int i = 0; i < numColumnas; i++) {
        nombreColumnas[i] = metaDatos.getColumnName(i + 1);
      }
      DefaultTableModel modelo = new DefaultTableModel(nombreColumnas, 0);
      while (resultado.next()) {
        Object[] fila = new Object[numColumnas];
        for (int i = 0; i < numColumnas; i++) {
          fila[i] = resultado.getObject(i + 1);
        }
        modelo.addRow(fila);
      }
      VentanaPrincipal.tabla.setModel(modelo);
      VentanaPrincipal.tabla.getTableHeader().setBackground(Color.GRAY);
      VentanaPrincipal.tabla.getTableHeader().setForeground(Color.WHITE);
      VentanaPrincipal.tabla.setBorder(
        BorderFactory.createLineBorder(Color.BLACK)
      );
      VentanaPrincipal.tabla.setGridColor(Color.BLACK);

      VentanaPrincipal.labelInformacion.setText(
        "Tabla " + nombreTabla + " mostrada"
      );
    } catch (SQLException e) {
      VentanaPrincipal.labelInformacion.setText(
        "Error al mostrar tabla " + nombreTabla
      );
    }
  }

  public static void crearBD(String nombreBD) {
    try {
      Statement s = conexion.createStatement();
      s.executeUpdate("CREATE DATABASE " + nombreBD);
      VentanaPrincipal.labelInformacion.setText(
        "Base de datos " + nombreBD + " creada"
      );
    } catch (SQLException e) {
      VentanaPrincipal.labelInformacion.setText(
        "Error al crear la base de datos " + nombreBD
      );
    }
  }

  public static void eliminarBD(String nombreBD) {
    try {
      Statement s = conexion.createStatement();
      s.executeUpdate("DROP DATABASE " + nombreBD);
      VentanaPrincipal.labelInformacion.setText(
        "Base de datos " + nombreBD + " eliminada"
      );
    } catch (SQLException e) {
      VentanaPrincipal.labelInformacion.setText(
        "Error al eliminar la base de datos " + nombreBD
      );
    }
  }

  public static void mostrarBDs() {
    try {
      Statement s = conexion.createStatement();
      ResultSet rs = s.executeQuery("SHOW DATABASES");
      while (rs.next()) {
        SubventanaGestionBD.modeloListaBD.addElement(rs.getString(1));
      }
    } catch (SQLException e) {
      VentanaPrincipal.labelInformacion.setText("Error al mostrar BDs");
    }
  }

  public static void refrescarBDs() {
    SubventanaGestionBD.modeloListaBD.removeAllElements();
    mostrarBDs();
  }

  public static String getUsuario() {
    return usuario;
  }

  public static String getPuerto() {
    return puerto;
  }

  public static String getEstado() {
    return estado;
  }

  public static Connection getConexion() {
    return conexion;
  }
}
