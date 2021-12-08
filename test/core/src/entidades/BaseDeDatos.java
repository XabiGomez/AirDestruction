package entidades;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import com.badlogic.gdx.Gdx;


public class BaseDeDatos {
	private static Connection conn;
	
	
	public static boolean conexionBd( String nomBD, boolean reinicio ) {
		try {
			Gdx.app.log( "InformacionBD", "Carga de la libreria org.sqlite.JDBC" );
			Class.forName("org.sqlite.JDBC");  
			Gdx.app.log( "InformacionBD", "Conexion con nuestra base de datos " + nomBD );
			conn = DriverManager.getConnection("jdbc:sqlite:" + nomBD );
			if (reinicio) {
				Statement statement = conn.createStatement();
				String sentencia = "DROP TABLE IF EXISTS player";
				Gdx.app.log( "InformacionBD", "Sentencia: " + sentencia );
				statement.executeUpdate( sentencia);
				sentencia = "CREATE TABLE player (idJugador INTEGER PRIMARY KEY, nombre string, score int;";
				Gdx.app.log( "InformacionBD", "Sentencia: " + sentencia);
				statement.executeUpdate( sentencia );
				try {
					Scanner scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("players.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] valores = linea.split( "\t" );
						sentencia = "insert into player (id, nombre, score) values (" + valores[0] + ",'" + valores[1] + "," + valores[2] + ");";
						Gdx.app.log( "InformacionBD", "Sentencia: " + sentencia );
						statement.executeUpdate( sentencia );
					}
					scanner.close();
				} catch(Exception e) {
					Gdx.app.error( "BD", "Excepcion:", e );
				}
			}
			return true;
		} catch(Exception e) {
			Gdx.app.error( "BD", "Excepcion:", e );
			return false;
		}
	}
	
	public static void cierreBD() {
		try {
			Gdx.app.log("InformacionBD", "Cierre de conexion BaseDeDatos");
			conn.close();
		}catch(SQLException e){
			Gdx.app.error("BD", "Excepcion:", e);	
		}
	}
	
	public static ArrayList<Player> getPlayers() {
		try (Statement statement = conn.createStatement()) {
			String sentencia = "select * from player;";
			Gdx.app.log( "InformacionBD", "Sentencia: " + sentencia );
			ResultSet result = statement.executeQuery( sentencia );
			ArrayList<Player> list = new ArrayList<>();
			while( result.next() ) {
				int id = result.getInt("id");
				String nombre = result.getString("nombre");
				int score = result.getInt("result");
				list.add(new Player(id,nombre, score));		
			}
			return list;
		} catch (Exception e) {
			Gdx.app.error( "BD", "Excepcion:", e );
			return null;
		}
	}
	
}
