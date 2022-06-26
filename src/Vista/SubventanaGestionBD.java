package Vista;

import Modelo.Conectar;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SubventanaGestionBD
  extends JFrame
  implements ActionListener, ListSelectionListener {

  public static DefaultListModel<String> modeloListaBD;
  public static JList<String> listaBD;
  public JScrollPane scroll;
  public JPanel panelListaBD, panelBotones;
  public JButton btnAbrir, btnEliminar, btnNueva, btnRefrescar;

  public SubventanaGestionBD() {
    setTitle("Gestión de Base de Datos");
    setSize(900, 400);
    setLocationRelativeTo(null);
    setResizable(false);
    setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    panelListaBD = new JPanel();
    panelListaBD.setBounds(0, 0, 300, 400);

    panelBotones = new JPanel();
    panelBotones.setBounds(300, 0, 600, 400);
    panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));

    modeloListaBD = new DefaultListModel<String>();
    listaBD = new JList<String>(modeloListaBD);
    listaBD.setBounds(0, 0, 900, 350);
    listaBD.setBorder(BorderFactory.createTitledBorder("Base de Datos"));
    listaBD.addListSelectionListener(this);

    scroll = new JScrollPane(listaBD);
    scroll.setBounds(0, 0, 900, 350);

    btnAbrir = new JButton("Abrir");
    btnEliminar = new JButton("Eliminar");
    btnNueva = new JButton("Nueva");
    btnRefrescar = new JButton("Refrescar");

    panelListaBD.add(scroll);
    panelListaBD.setLayout(new BoxLayout(panelListaBD, BoxLayout.Y_AXIS));

    panelBotones.add(btnAbrir);
    panelBotones.add(btnEliminar);
    panelBotones.add(btnNueva);
    panelBotones.add(btnRefrescar);

    add(panelListaBD);
    add(panelBotones);

    Conectar.mostrarBDs();
    setVisible(true);

    btnAbrir.addActionListener(this);
    btnEliminar.addActionListener(this);
    btnNueva.addActionListener(this);
    btnRefrescar.addActionListener(this);
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    if (e.getSource() == listaBD) {
      if (listaBD.getSelectedIndex() != -1) {
        btnAbrir.setEnabled(true);
        btnEliminar.setEnabled(true);
      } else {
        btnAbrir.setEnabled(false);
        btnEliminar.setEnabled(false);
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnAbrir) {
      String nombreBD = listaBD.getSelectedValue();
      Conectar.abrirBD(nombreBD);
      dispose();
    } else if (e.getSource() == btnEliminar) {
      String nombreBD = listaBD.getSelectedValue();
      Conectar.eliminarBD(nombreBD);
    } else if (e.getSource() == btnNueva) {
      new SubventanaCrearBD();
      System.out.println("Nueva BD");
    } else if (e.getSource() == btnRefrescar) {
      Conectar.refrescarBDs();
    }
  }
}
