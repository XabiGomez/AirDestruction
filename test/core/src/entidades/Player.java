package entidades;


public class Player {
	private int id;
	private String nombre;
	private int score;

	
	public Player(int id, String nombre, int score) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.score = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Player)) {
			return false;
		}
		Player p2 = (Player) obj;
		return nombre.equals(p2.getNombre());
	}
	public String toString() {
		return id +"," + nombre + "," + score;
	}
	
	

}
