import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class Apuestas extends JFrame {

	private JPanel contentPane;
	private JTextField textNombreLiga;
	private Liga liga;

			
	//Nos conectamos a la BBDD
	private Connection conexion = null; 



	//Main method
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Apuestas frame = new Apuestas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});			
	}//final main
	
	public Apuestas() {	
		//Conectar con la BBDD	
		Statement instruccion = null;// Hacemos consultas en la BBDD
//		ResultSet conjuntoResultados = null;// Aqui se guardan las consultas que vamos haciendo
			try{
				//Cargamos el driver de SQL JConnector
				Class.forName("com.mysql.jdbc.Driver");
				// Establecemos la conexion con la BBDD y le marcamos la ruta, usuario y contraseña de la BBDD
				conexion = DriverManager.getConnection("jdbc:mysql://localhost/apuestas","root","");			
				//Capturamos en caso de que no conecte con la BBDD
			}catch( SQLException excepcionSql ){
					excepcionSql.printStackTrace();
			}// fin de catch einicio de otro catch para caputrar el Calls.forname en caso de que no lo encuentre
			catch( ClassNotFoundException ClassNotFound ){
					ClassNotFound.printStackTrace();
			}// fin de catch
			liga= new Liga((com.mysql.jdbc.Connection) conexion);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel NombreLiga = new JLabel("Nombre de la Liga");
		NombreLiga.setBounds(34, 22, 150, 24);
		contentPane.add(NombreLiga);
		
		textNombreLiga = new JTextField();
		textNombreLiga.setBounds(34, 45, 179, 20);
		contentPane.add(textNombreLiga);
		textNombreLiga.setColumns(10);
		textNombreLiga.setText(String.valueOf(liga.getnombreLiga()));
		
		
		JButton btnAdministrar = new JButton("Administrar");
		btnAdministrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaLiga(liga);
			}
		});
		btnAdministrar.setBounds(33, 89, 119, 23);
		contentPane.add(btnAdministrar);
		
		JButton btnGenerarApuesta = new JButton("Generar apuesta");
		btnGenerarApuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnGenerarApuesta.setBounds(34, 161, 136, 23);
		contentPane.add(btnGenerarApuesta);
		
		JButton btnSeguimientoDeApuestas = new JButton("Seguimiento de apuestas");
		btnSeguimientoDeApuestas.setBounds(34, 195, 179, 23);
		contentPane.add(btnSeguimientoDeApuestas);
	}
	
	//metodo para abrir la ventana principal
	public void abrirVentanaLiga(Liga liga){
		//Decimos que nos abra la ventanaLiga
		VentanaLiga frame2 = new VentanaLiga(liga);
		frame2.setVisible(true);
		frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
}
