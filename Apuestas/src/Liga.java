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
	}

	//Creamos el constructor al que  pasamos los valores que queremos
	public Liga (Connection conexion, int numero, String nombre){
				numEquipos=numero;
				nombreLiga=nombre;
				this.conexion=conexion;
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
			//Necesitamos pasarle el metodo de eliminar Equipo que se conecta con la BBDD
			public void eliminarEquipo(int posicion, Equipo equipo){
				this.comboBox.removeItemAt(posicion);
				EliminarEquipoDB(equipo);
			}
			

/*--------------------------Metodos de interacción con la Base de Datos-----------------------------------*/
				

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
	
	//Metodo para rellenar el Combobox con la info de la BBDD
	public void rellenarCombobox(JComboBox j){
		try{
			instruccion = (Statement) conexion.createStatement();
			conjuntoResultados = instruccion.executeQuery ("SELECT * FROM equipos");
			          // Bucle While para iniciar la consulta						
			          while (conjuntoResultados.next())
			          {
			        	  equipo = new Equipo();
			              //Rellenamos combobox a partir de la consulta
			        	  equipo.setNombreEquipo((String)(conjuntoResultados.getObject("nombreEquipo")));
			        	  equipo.setIdEquipo(conjuntoResultados.getInt("idEquipo"));
			        	  equipo.setGolesFavor(conjuntoResultados.getInt("golesFavor"));
			        	  equipo.setGolesContra(conjuntoResultados.getInt("golesEnContra"));
			        	  equipo.setPartidosPerdidos(conjuntoResultados.getInt("PartidosPerdidos"));
			        	  equipo.setPartidosGanados(conjuntoResultados.getInt("PartidosGanados"));
			        	  comboBox.addItem(equipo);
			          }
			      } catch(SQLException e){
			          JOptionPane.showMessageDialog(null,"Error sql no se pueden leer datos");
			      }
			}
	
	public void rellenarCombo(JComboBox j){
		this.comboBox=j;
		leerLiga();
		this.rellenarCombobox(j);
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
		if(modificar.getIdEquipo()>0){
		try{
			instruccion = (Statement) conexion.createStatement();
			String UpdateBBDD = ("UPDATE `equipos` SET `nombreEquipo`=\""+modificar.toString()+"\","
					+ " `golesFavor`="+modificar.getGolesFavor()+
					", `golesEnContra`="+modificar.getGolesContra()+", `partidosGanados`="+modificar.getPartidosGanados()+","
							+ " `partidosPerdidos`="+modificar.getPartidosPerdidos()+" WHERE `idEquipo`="+modificar.getIdEquipo());
					instruccion.executeUpdate(UpdateBBDD);
		}catch (SQLException e){
			e.printStackTrace();
		}
		}
	}
}

		
						

