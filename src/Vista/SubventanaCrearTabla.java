package Vista;

import Modelo.Conectar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.sql.*;

public class SubventanaCrearTabla extends JFrame implements ActionListener,ItemListener {
  private JComboBox<String> comboBoxBD, comboBoxTipo;
  private JPanel panelBD = new JPanel(), panelCampos = new JPanel(), panelBotones = new JPanel();
  private JTextField nombreCampo;
  private JCheckBox checkPrimaria;
  private JButton botonNuevoCampo, botonCrear;

  public SubventanaCrearTabla() {
    setTitle("Crear Tabla");
    setSize(900, 400);
    setLocationRelativeTo(null);
    setResizable(false);
    setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    panelBD = new JPanel();
    panelBD.setBorder(BorderFactory.createTitledBorder("Base de datos"));
    panelBD.setLayout(null);
    panelBD.setBounds(50,0,500,100);
    comboBoxBD = new JComboBox<String>();
    comboBoxBD.setBounds(20,20,150,30);
    panelBD.add(comboBoxBD);
    panelCampos.setLayout(null);
    panelCampos.setBounds(50,120,500,100); // TODO: revisar creacion de campo
    panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
    botonNuevoCampo = new JButton("Añadir Campo");
    botonCrear = new JButton("Crear Tabla");
    rellenarCombo();
    add(panelBD);
    add(panelCampos);
    panelBotones.add(botonNuevoCampo);
    panelBotones.add(botonCrear);
    add(panelBotones);
    JPanel campoInicial = crearCampo();
    setVisible(true);
  }
  
  public JPanel crearCampo() {
    String[] tipoDatos = { "VARCHAR(50)", "INT", "DECIMAL(6,2)" };
    JPanel campo = new JPanel();
    JLabel labelNombreCampo = new JLabel("Nombre");
    labelNombreCampo.setBounds(10, 20, 60, 10);
    nombreCampo = new JTextField();
    nombreCampo.setBounds(70, 15, 120, 20);
    checkPrimaria = new JCheckBox("Clave primaria");
    checkPrimaria.setBounds(200, 10, 130, 20);
    comboBoxTipo = new JComboBox<String>();
    comboBoxTipo.setBounds(330, 10, 150, 20);
    for (int i = 0; i < tipoDatos.length; i++) {
      comboBoxTipo.addItem(tipoDatos[i]);
    }
    campo.setLayout(null);
    campo.add(labelNombreCampo);
    campo.add(nombreCampo);
    campo.add(checkPrimaria);
    campo.add(comboBoxTipo);

    panelCampos.add(campo);
    return campo;
  }

  public void rellenarCombo() {
    try {
      Statement s = Conectar.conexion.createStatement();
      ResultSet rs = s.executeQuery("SHOW DATABASES");
      while (rs.next()) {
        comboBoxBD.addItem(rs.getString(1));
      }
    }catch(Exception e){}
  }
  @Override
  public void itemStateChanged(ItemEvent e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == botonNuevoCampo) {
      crearCampo();
    }
    
  }
  
}
