import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;


	public class ControllerDB {
		//DB
		Connection conexion = null; //maneja la conexión
		Statement stat= null;
		ResultSet resultado= null;
		

		public ControllerDB() {
			//Nos conectamos a la base de datos
			crearConexion();
		}

		private void crearConexion(){
			//Conectarnos a la base de datos
			try{
				Class.forName("com.mysql.jdbc.Driver");
				// establece la conexión a la base de datos
				conexion = DriverManager.getConnection("jdbc:mysql://localhost/apuestas","root","");
			}catch( SQLException excepcionSql ){
				excepcionSql.printStackTrace();
			}// fin de catch
			catch( ClassNotFoundException noEncontroClase )
			{
				noEncontroClase.printStackTrace();
			}// fin de catch
		}


		public void leerEquipos(JComboBox listadoEquipos){
			//Aquí realizaremos la consulta y actualización del combobox
			try
			{			
				// Preparamos la consulta
				Statement stat = conexion.createStatement();	 
				// Se realiza la consulta
				resultado = stat.executeQuery ("SELECT nombreEquipo FROM equipos");		 
				// Bucle while para la consulta
			while (resultado.next())
			{
				// Comenzamos a rellenar el combobox a partir de la consulta
				listadoEquipos.addItem (resultado.getObject("nombreEquipo"));
				System.out.println("El nombre del equipo es " +resultado.getObject("nombreEquipo")); 
			}	
			
				} catch (Exception e){
				System.out.println (e);
				}
			
		}


		public void insertarEquipos(int idLiga,String nombreEquipo,int golesFavor,int golesEnContra, int partidosGanados,int partidosPerdidos,JComboBox listadoEquipos){
			//Aquí realizaremos la consulta
			 try{
		            stat = (Statement) conexion.createStatement();
		            //Insertamos datos
		            String insertarBBDD = "INSERT INTO equipos (idLiga, nombreEquipo,golesFavor,golesEnContra,partidosGanados,partidosPerdidos)";    
		            insertarBBDD = insertarBBDD + "VALUES ("+idLiga+",'"+nombreEquipo+"',"+golesFavor+","
		                    +golesEnContra+","+partidosGanados+","+ ""+partidosPerdidos+" )";
		    
		            stat.executeUpdate(insertarBBDD);
		            }catch(SQLException excepcionSql ){
		                excepcionSql.printStackTrace();    
		            }
			//Actualización del combobox
			listadoEquipos.removeAllItems();
			leerEquipos(listadoEquipos);
		}
	}