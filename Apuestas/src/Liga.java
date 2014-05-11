import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class Liga implements Serializable {
	//Variables
		private int numEquipos;
		private String nombreLiga;
		//private Equipo equipo[];
		private ArrayList <Equipo> equipoLista = new ArrayList <Equipo> ();
		private int idLiga;
	
		//BBDD
		private Connection conexion = null; 
		private Statement instruccion = null;// Hacer consultas en la BBDD
		private ResultSet conjuntoResultados = null;// Aqui se guardan las consultas que vamos haciendo
		private JComboBox comboBox;
		private Equipo equipo;



			
	public Liga(Connection conexion) {
		//Inicializamos
		nombreLiga="Liga Española";
		numEquipos=0;		
		this.conexion=conexion;
		this.comboBox=comboBox;
		// Rellenamos el arrayList
		// for (int i = 0; i <numEquipos; i++) {
		//((equipoLista.add(new Equipo());
		//System.out.println (equipoLista.get (i));
		//}		
		/*
		leerLiga();
		leerCombobox();*/
		
	}

	//Creamos el constructor al que  pasamos los valores que queremos
	public Liga (Connection conexion, int numero, String nombre){
				numEquipos=numero;
				nombreLiga=nombre;
				this.conexion=conexion;
				// Inicializamos el arrayList
//				equipoLista = new ArrayList <Equipo> ();
//				// Rellenamos el arrayList
				for (int i = 0; i <numEquipos; i++) {
				equipoLista.add(new Equipo());
				// System.out.println (equipoLista.get (i));
				}
	}
			
			//Creación de getter y setter 
			public void setnombreLiga(String nomliga){
				nombreLiga=nomliga;
			}
			public String getnombreLiga(){
				return nombreLiga;
			}
			
			
			public int getnumEquipos(){
				return numEquipos;
			}
			
			public void setEquipo(Equipo equipModificar, int posicion){
				equipoLista.set(posicion, equipModificar);
			}
			
			public Equipo getEquipo (int posicion){
				return equipoLista.get(posicion);
			}
			
			//Métodos para añadir y eliminar equipos
			public void nuevoEquipo(){
				equipoLista.add(new Equipo());
				numEquipos++;
			}
			
			public void eliminarEquipo(int posicion, Equipo equipo){
				equipoLista.remove(posicion);
				EliminarEquipoDB(equipo);
			}
			
	public void rellenarCombo(JComboBox j){
		this.comboBox=j;
		leerLiga();
		this.rellenarCombobox();
	}
	
	
	//Metodo leer liga
	public void leerLiga(){
		try{
		instruccion = (Statement) conexion.createStatement();
		conjuntoResultados = instruccion.executeQuery("SELECT idLiga, nombre, numEquipos FROM liga LIMIT 1");	
		conjuntoResultados.next();
		this.idLiga=(int)conjuntoResultados.getObject("idLiga");
		this.nombreLiga=(String)conjuntoResultados.getObject("nombre");
		this.numEquipos=(int)conjuntoResultados.getObject("numEquipos");
		}catch(SQLException excepcionSql ){
			excepcionSql.printStackTrace();	
		}
	}
	
	public void rellenarCombobox(){
		try{
			instruccion = (Statement) conexion.createStatement();
			conjuntoResultados = instruccion.executeQuery ("SELECT nombreEquipo FROM equipos");
			          // Bucle While para iniciar la consulta						
			          while (conjuntoResultados.next())
			          {
			        	  equipo = new Equipo();
			              //Rellenamos combobox a partir de la consulta
			        	  equipo.setNombreEquipo((String)(conjuntoResultados.getObject("nombreEquipo")));
			        	  //comboBox.addItem(conjuntoResultados.getObject("nombreEquipo"));
			        	  //comboBox.addItem(equipo.getNombreEquipo());
			        	  comboBox.addItem(equipo);
			          }
			          //Cerramos conexion
//			          conexion.close();
			      } catch(SQLException e){
			          JOptionPane.showMessageDialog(null,"Error sql no se pueden leer datos");
			      }
			}
	
	
	//Metodo insertar equipo
	public void InsertarEquipo(Equipo equipo){
		try{
			instruccion = (Statement) conexion.createStatement();
			//Insertamos datos
			String insertarBBDD = "INSERT INTO equipos (idLiga,nombreEquipo,golesFavor,golesEnContra,partidosGanados,partidosPerdidos)";	
			insertarBBDD = insertarBBDD + "VALUES ("+idLiga+",'"+equipo.getNombreEquipo()+"',"+equipo.getGolesFavor()+","
					+ ""+equipo.getGolesContra()+","+equipo.getPartidosGanados()+","
							+ ""+equipo.getPartidosPerdidos()+")";
			instruccion.executeUpdate(insertarBBDD);
			}catch(SQLException excepcionSql ){
				excepcionSql.printStackTrace();	
			}		
}
			
		
	//Metodo Eliminar equipo
	public void EliminarEquipoDB(Equipo equipo){
		try{
			instruccion = (Statement) conexion.createStatement();
			String eliminarTeam = "DELETE FROM `apuestas`.`equipos` WHERE `equipos`.`idEquipo` ="+equipo.getIdEquipo();
			instruccion.executeUpdate(eliminarTeam);
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	//Metodo modificar equipo
	public void modificarEquipo(Equipo modificar){
		try{
			instruccion = (Statement) conexion.createStatement();
			instruccion.executeUpdate("UPDATE `equipos` SET `nombreEquipo`=\""+modificar.toString()+"\","
					+ " `golesFavor`="+modificar.getGolesFavor()+
					", `golesEnContra`="+modificar.getGolesContra()+", `partidosGanados`="+modificar.getPartidosGanados()+","
							+ " `partidosPerdidos`="+modificar.getPartidosPerdidos()+" WHERE `idEquipo`="+modificar.getIdEquipo());
			System.out.println("Equipo Guardado");
		}catch (SQLException e){
			e.printStackTrace();
		}
	}

}

		
						

