package entidades;
import java.sql.*;
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
				sentencia = "CREATE TABLE jugador (idJugador INTEGER PRIMARY KEY, nombre string, apellidos string ,score int;";
				Gdx.app.log( "InformacionBD", "Sentencia: " + sentencia);
				statement.executeUpdate( sentencia );
				try {
					Scanner scanner = new Scanner( BaseDeDatos.class.getResourceAsStream("players.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] valores = linea.split( "\t" );
						sentencia = "insert into player (id, nombre, apellidos, score) values (" + valores[0] + ",'" + valores[1] + "," + valores[2] + "," + valores[3] +");";
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
			Gdx.app.log( "BD", "Excepcion:", e );
			return false;
		}
	}
	
	public void cierreBD() {
		try {
			Gdx.app.log("InformacionBD", "Cierre de conexion BaseDeDatos");
			conn.close();
		}catch(SQLException e){
			Gdx.app.error("BD", "Excepcion:", e);	
		}
	}
	
	
}
