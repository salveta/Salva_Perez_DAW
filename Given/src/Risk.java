import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Risk extends JFrame {

	private JPanel contentPane;
	private JTextField textoAtaqueDos;
	private JTextField textoDefensaUno;
	private JTextField textAtaqueUno;
	private JTextField textoDefensa2;
	private JTextField ResultadoAtaque;
	private JTextField ResultadoAtaqueDos;

	private int DadoAtaqueUno [] = new int [2];
	private int DadoAtaqueDos [] = new int [2];
	private int DadoDefensaUno [] = new int [2];
	private int DadoDefensaDos [] = new int [2];
	private int lanzamiento;
	private int resultado;
	private int i=0;
	private JTextField playerVida;
	
	Personaje player = new Personaje();
	
	public Risk() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				playerVida.setText(String.valueOf(player.cuantaVidaTengo()));
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDadoAtaque = new JLabel("Dado Ataque 1");
		lblDadoAtaque.setBounds(34, 24, 90, 14);
		contentPane.add(lblDadoAtaque);
		
		textoAtaqueDos = new JTextField();
		textoAtaqueDos.setBounds(43, 161, 65, 44);
		contentPane.add(textoAtaqueDos);
		textoAtaqueDos.setColumns(10);
		
		JLabel lblDadoDefensa = new JLabel("Dado Defensa 1");
		lblDadoDefensa.setBounds(138, 24, 90, 14);
		contentPane.add(lblDadoDefensa);
		
		textoDefensaUno = new JTextField();
		textoDefensaUno.setColumns(10);
		textoDefensaUno.setBounds(141, 61, 65, 44);
		contentPane.add(textoDefensaUno);
		
		JLabel lblDadoAtaque_1 = new JLabel("Dado Ataque 2");
		lblDadoAtaque_1.setBounds(33, 133, 91, 14);
		contentPane.add(lblDadoAtaque_1);
		
		JLabel lblNewLabel = new JLabel("Dado Defensa 2");
		lblNewLabel.setBounds(138, 133, 90, 14);
		contentPane.add(lblNewLabel);
		
		textAtaqueUno = new JTextField();
		textAtaqueUno.setColumns(10);
		textAtaqueUno.setBounds(43, 61, 65, 44);
		contentPane.add(textAtaqueUno);
		
		textoDefensa2 = new JTextField();
		textoDefensa2.setColumns(10);
		textoDefensa2.setBounds(141, 161, 65, 44);
		contentPane.add(textoDefensa2);
		
		JLabel lblResultado = new JLabel("Perdida vida");
		lblResultado.setBounds(298, 24, 126, 14);
		contentPane.add(lblResultado);
		
		ResultadoAtaque = new JTextField();
		ResultadoAtaque.setBounds(305, 61, 65, 44);
		contentPane.add(ResultadoAtaque);
		ResultadoAtaque.setColumns(10);
		
		JLabel lblResultadoDefensa = new JLabel("Perdida vida");
		lblResultadoDefensa.setBounds(298, 133, 126, 14);
		contentPane.add(lblResultadoDefensa);
		
		ResultadoAtaqueDos = new JTextField();
		ResultadoAtaqueDos.setColumns(10);
		ResultadoAtaqueDos.setBounds(305, 161, 65, 44);
		contentPane.add(ResultadoAtaqueDos);
		
		JButton btnTirar = new JButton("Tirar");
		btnTirar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
        //DadoAtaqueUno[lanzamiento]
		System.out.println("Ataque 1 vale: "+Integer.valueOf(textAtaqueUno.getText()));
		System.out.println("Defensa 1 vale: "+Integer.valueOf(textoDefensaUno.getText()));
		System.out.println("El resultado es: "+(Integer.valueOf(textAtaqueUno.getText())-Integer.valueOf(textoDefensaUno.getText())));
		System.out.println("Ataque 2 vale: "+Integer.valueOf(textoAtaqueDos.getText()));
		System.out.println("Defensa 2 vale: "+Integer.valueOf(textoDefensa2.getText()));
		System.out.println("El resultado es: "+(Integer.valueOf(textoAtaqueDos.getText())-Integer.valueOf(textoDefensa2.getText())));
						
			if ((Integer.valueOf(textAtaqueUno.getText()))<Integer.valueOf(textoDefensaUno.getText())){
				player.restarVida();
				ResultadoAtaque.setText("-1");
				playerVida.setText(String.valueOf(player.cuantaVidaTengo()));
			}
			
			if ((Integer.valueOf(textoAtaqueDos.getText()))<Integer.valueOf(textoDefensa2.getText())){
				player.restarVida();
				ResultadoAtaqueDos.setText("-1");
				playerVida.setText(String.valueOf(player.cuantaVidaTengo()));
			}
			
		}
		});
		btnTirar.setBounds(208, 110, 89, 23);
		contentPane.add(btnTirar);
		
		JLabel lblNewLabel_1 = new JLabel("Vida restante");
		lblNewLabel_1.setBounds(298, 234, 88, 14);
		contentPane.add(lblNewLabel_1);
		
		playerVida = new JTextField();
		playerVida.setBounds(396, 231, 28, 20);
		contentPane.add(playerVida);
		playerVida.setColumns(10);
		
		
	}
}
