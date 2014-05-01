import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class VentanaEquipo extends JFrame {

	private JPanel contentPane;
	private JTextField textNombreEquipo;
	private JTextField textGolesFavor;
	private JTextField textGolesContra;
	private JTextField textPartidosGanados;
	private JTextField textPartidosPerdidos;
	private Equipo equipo;
	private JButton btnGuardarEnDisco;
	private JButton btnLeerEnDisco;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private boolean modifica;
	private JComboBox comboBox;
	private Liga liga;

	
	public VentanaEquipo(Equipo equipoAModificar, JComboBox comboBox, boolean modifica, Liga liga ) {
		
		//Decimos objeto equipo sea igual al parámetro que pasamos por el constructor
				equipo = equipoAModificar;
				this.comboBox=comboBox;
				this.modifica=modifica;
				this.liga=liga;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 353, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreEquipo = new JLabel("Nombre Equipo");
		lblNombreEquipo.setBounds(20, 21, 95, 14);
		contentPane.add(lblNombreEquipo);
		
		JLabel lblGolesAFavor = new JLabel("Goles a favor");
		lblGolesAFavor.setBounds(20, 67, 84, 14);
		contentPane.add(lblGolesAFavor);
		
		JLabel lblGolesEnContra = new JLabel("Goles en contra");
		lblGolesEnContra.setBounds(20, 92, 95, 14);
		contentPane.add(lblGolesEnContra);
		
		JLabel lblPartidosGanados = new JLabel("Partidos Ganados");
		lblPartidosGanados.setBounds(20, 142, 113, 14);
		contentPane.add(lblPartidosGanados);
		
		JLabel lblPartidosPerdidos = new JLabel("Partidos Perdidos");
		lblPartidosPerdidos.setBounds(20, 167, 113, 14);
		contentPane.add(lblPartidosPerdidos);
		
		textNombreEquipo = new JTextField();
		textNombreEquipo.setBounds(137, 18, 171, 20);
		contentPane.add(textNombreEquipo);
		textNombreEquipo.setColumns(10);
		
		textGolesFavor = new JTextField();
		textGolesFavor.setBounds(136, 64, 61, 20);
		contentPane.add(textGolesFavor);
		textGolesFavor.setColumns(10);
		
		textGolesContra = new JTextField();
		textGolesContra.setBounds(136, 89, 61, 20);
		contentPane.add(textGolesContra);
		textGolesContra.setColumns(10);
		
		textPartidosGanados = new JTextField();
		textPartidosGanados.setBounds(136, 139, 61, 20);
		contentPane.add(textPartidosGanados);
		textPartidosGanados.setColumns(10);
		
		textPartidosPerdidos = new JTextField();
		textPartidosPerdidos.setBounds(136, 164, 61, 20);
		contentPane.add(textPartidosPerdidos);
		textPartidosPerdidos.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Llamamos al método
				guardar();
			}
		});

		btnGuardar.setBounds(219, 215, 89, 23);
		contentPane.add(btnGuardar);
		
		btnGuardarEnDisco = new JButton("Guardar en disco");
		btnGuardarEnDisco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Llamamos a los métodos
				guardar();
				guardarEnFichero();	
				
			}
		});
		btnGuardarEnDisco.setBounds(10, 268, 137, 20);
		contentPane.add(btnGuardarEnDisco);
		
		btnLeerEnDisco = new JButton("Leer en disco");
		btnLeerEnDisco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Evento recuperar fichero
				leerEnFichero();
				textNombreEquipo.setText(equipo.getNombreEquipo());
				textGolesContra.setText(String.valueOf(equipo.getGolesContra()));	
			}
		});
		btnLeerEnDisco.setBounds(10, 303, 137, 23);
		contentPane.add(btnLeerEnDisco);
		
		//Rellenar datos al abrir la ventana
		leer();
			
	}

	//Metodo para guardar datos  en el objeto
	private void guardar(){
		equipo.setNombreEquipo(textNombreEquipo.getText());
		equipo.setGolesFavor(Integer.valueOf(textGolesFavor.getText()));
		equipo.setGolesContra(Integer.valueOf(textGolesContra.getText()));
		equipo.setPartidosGanados(Integer.valueOf(textPartidosGanados.getText()));
		equipo.setPartidosPerdidos(Integer.valueOf(textPartidosPerdidos.getText()));

		//Código para decirle que sino se modifica añada equipo
		if(!modifica){
			comboBox.addItem(equipo);
			//guardo equipo en BBDD
			this.liga.InsertarEquipo(equipo);
			//Aquí modifica el equipo
		}else{
			Equipo equipoElegido=(Equipo)comboBox.getSelectedItem();
			equipoElegido.setNombreEquipo(equipo.getNombreEquipo());
		}
	}
	
	//Metodo para leer datos desde el objeto
	private void leer(){
		textNombreEquipo.setText(equipo.getNombreEquipo());
		textGolesFavor.setText(String.valueOf(equipo.getGolesFavor()));
		textGolesContra.setText(String.valueOf(equipo.getGolesContra()));
		textPartidosGanados.setText(String.valueOf(equipo.getPartidosGanados()));
		textPartidosPerdidos.setText(String.valueOf(equipo.getPartidosPerdidos()));
	}
	
	//Metodo para guardar datos en fichero
	private void guardarEnFichero(){
		try// abre el archivo
		{
			salida = new ObjectOutputStream(new FileOutputStream( "equipos.ser") );
			salida.writeObject(equipo); // envía el registro como salida
			if( salida != null)
				salida.close();
		}// fin de try
		catch( IOException ioException )
		{
			System.err.println("Error al abrir el archivo.");
		}// fin de catch
	}// fin del método guardarEnFichero
	
	
	//Metodo para recuperar datos en fichero
	private void leerEnFichero(){
		try// abre el archivo
		{
			entrada = new ObjectInputStream(new FileInputStream( "equipos.ser") );
			equipo= (Equipo) entrada.readObject(); // envía el registro como entrada
			if( entrada != null)
				entrada.close();
		}// fin de try
		catch( IOException ioException )
		{
			System.err.println("Error al abrir el archivo.");
		} catch( ClassNotFoundException clException ){
			System.err.println("Error al abrir el archivo.");
		}
		
	}// fin del método leerEnFichero
}
