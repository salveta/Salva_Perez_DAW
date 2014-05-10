import java.io.Serializable;


public class Equipo implements Serializable{
	
	//Creamos atributos
	private String nombreEquipo;
	private int golesFavor;
	private int golesEnContra;
	private int partidosGanados;
	private int partidosPerdidos;
	private int idEquipo;

	public Equipo() 
	{
		// El constructor sin argumentos llama al otro constructor con los valores predeterminados
		
		this("", 0, 0, 0, 0, 0);
	} //Fin del constructor sin argumentos

		//Se inicializa un registro con los cuatro argumentos del constructor
	public Equipo(String nom, int golesF, int golesC, int partidosG, int partidosP, int idEquipo) {
		//Inicializamos variables
		nombreEquipo=nom;
		golesFavor=golesF;
		golesEnContra=golesC;
		partidosGanados=partidosG;
		partidosPerdidos=partidosP;
		this.idEquipo=idEquipo;
		
	}// fin del constructor de Equipo con los argumentos
	//Iniciamos los métodos
	public void setNombreEquipo(String nom){
		nombreEquipo= nom;
	}
	public String getNombreEquipo(){
		return nombreEquipo;
	}
	
	
	public void setGolesFavor (int golesF){
		golesFavor=golesF;
	}
	public int getGolesFavor(){
		return golesFavor;
	}
	
	
	public void setGolesContra (int golesC){
		golesEnContra=golesC;
	}
	public int getGolesContra(){
		return golesEnContra;
	}
	
	
	public void setPartidosGanados (int partidosG){
		partidosGanados=partidosG;
	}
	public int getPartidosGanados(){
		return partidosGanados;
	}
	
	
	public void setPartidosPerdidos (int partidosP){
		partidosPerdidos=partidosP;
	}
	public int getPartidosPerdidos(){
		return partidosPerdidos;
	}
	
	//Metodo imprescindible para que ComboBox lea el equipo
	public String toString(){
		return nombreEquipo;
	}
	public int getIdEquipo(){
		return this.idEquipo;
	}

}