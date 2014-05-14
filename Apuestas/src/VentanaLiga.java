import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;



public class VentanaLiga extends JFrame {

	private JPanel contentPane;
	private JTextField textNombreLiga;
	private JTextField textNumeroEquipos;
	private Liga liga;
	private JComboBox <Equipo> comboBox;
	private VentanaEquipo frameEquipo;
	private VentanaLiga vLiga;
	private Equipo equipo = new Equipo();



	
	public VentanaLiga(Liga ligaModificada) {
		
		liga=ligaModificada;
		vLiga=this;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDeLa = new JLabel("Nombre de la Liga");
		lblNombreDeLa.setBounds(21, 29, 135, 14);
		contentPane.add(lblNombreDeLa);
		
		textNombreLiga = new JTextField();
		textNombreLiga.setBounds(134, 26, 157, 20);
		contentPane.add(textNombreLiga);
		textNombreLiga.setColumns(10);
		
		JLabel lblNumeroEquipos = new JLabel("Numero Equipos");
		lblNumeroEquipos.setBounds(21, 68, 101, 14);
		contentPane.add(lblNumeroEquipos);
		
		textNumeroEquipos = new JTextField();
		textNumeroEquipos.setEnabled(false);
		textNumeroEquipos.setBounds(134, 65, 51, 20);
		contentPane.add(textNumeroEquipos);
		textNumeroEquipos.setColumns(10);
		//Añadimos número de equipos
		textNumeroEquipos.setText(String.valueOf(liga.getnumEquipos()));
		//Añadimos nombre de la Liga
		textNombreLiga.setText(String.valueOf(liga.getnombreLiga()));
		
		
		JLabel lblEquipoAModificar = new JLabel("Equipo a modificar");
		lblEquipoAModificar.setBounds(21, 144, 135, 14);
		contentPane.add(lblEquipoAModificar);
		
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Lanzamos la ventana Equipo
				//OpenEquipoWindow(liga.getEquipo(liga.getnumEquipos()-1),true);
				OpenEquipoWindow(comboBox.getItemAt(comboBox.getSelectedIndex()),true);
				equipo = comboBox.getItemAt(comboBox.getSelectedIndex());
				liga.modificarEquipo(equipo);
			}
		});
		btnModificar.setBounds(247, 208, 89, 23);
		contentPane.add(btnModificar);
		
		comboBox = new JComboBox<Equipo>();
		comboBox.setBounds(134, 141, 204, 20);
		contentPane.add(comboBox);
		
		JButton AñadirEquipo = new JButton("+");
		AñadirEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Llamamos al método crear equipo y le decimos que nos abra una ventana
				liga.nuevoEquipo();
				//System.out.println(liga.getnumEquipos());
				//Le pasamos un nuevo equipo a la ventana que lanzamos para que lo cree
				OpenEquipoWindow(new Equipo(),false);

			}
		});
		AñadirEquipo.setBounds(33, 208, 89, 23);
		contentPane.add(AñadirEquipo);
		
		JButton EliminarEquipo = new JButton("-");
		EliminarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Pasamos objeto equipo al Arraylist Equipo a ver si así se arregla el error
				equipo = comboBox.getItemAt(comboBox.getSelectedIndex());
				liga.eliminarEquipo(comboBox.getSelectedIndex(), equipo);
			}
		});
		EliminarEquipo.setBounds(134, 208, 89, 23);
		contentPane.add(EliminarEquipo);
		
		//Llamamos para que rellene el comboBox
		this.liga.rellenarCombo(this.comboBox);
		
	}
	
	//Creamos método para poder modificar un equipo en una ventana nueva
	private void OpenEquipoWindow(Equipo equipo, boolean modifica){
		frameEquipo = new VentanaEquipo (equipo,this.comboBox, modifica, this.liga);
		frameEquipo.setVisible(true);
		frameEquipo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}



}

