public class Personaje {

	private int vida;
	
	public Personaje() {
		// TODO Auto-generated constructor stub
		
		vida=10;
		
	}

	public void restarVida(){
		vida--;
	}
	public int cuantaVidaTengo (){
		return vida;
	}
}
