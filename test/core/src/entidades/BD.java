package entidades;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;



public class BD {
		private static Connection conn;
		public static int id=0;
	
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
				sentencia = "CREATE TABLE player (id INTEGER PRIMARY KEY, nombre varchar(20), score int);";
				Gdx.app.log( "InformacionBD", "Sentencia: " + sentencia);
				statement.executeUpdate( sentencia );
				try {
					Scanner scanner = new Scanner( BD.class.getResourceAsStream("players.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] valores = linea.split( " " );
						Gdx.app.log("InformacionBD","Valores" +valores);
						sentencia = "insert into player (id, nombre, score) values (" + valores[0] + ",'" + valores[1] + "'," + valores[2] + ");";
						Gdx.app.log( "InformacionBD", "Sentencia: " + sentencia );
						statement.executeUpdate( sentencia );
					}
					scanner.close();
				} catch(Exception e) {
					Gdx.app.error( "BD", "Excepcion:", e );
				}
			}
			id= getPlayers().size();
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
		
		
		public static boolean insertarPlayer( Player player) {
			try (Statement statement = conn.createStatement()) {
				id ++;
				Gdx.app.log("InformacionBD", "Numero de Personas ranking " + id);
				String sentencia = "insert into player (id, nombre, score) values (" + id + ",'" + player.getNombre() + "'," + player.getScore() + ");";
				Gdx.app.log( "InformacionBD", "Sentencia: " + sentencia);
				int insert = statement.executeUpdate( sentencia );
				if (insert!=1) return false;  
				ResultSet result = statement.getGeneratedKeys(); 
				result.next();
				int primary = result.getInt( 1 );  
				player.setId( primary );
				return true;
			} catch (Exception e) {
				Gdx.app.error("BD", "Excepcion", e );
				return false;
			}
		}
	
		public static ArrayList<Player> getPlayers() {
			try (Statement statement = conn.createStatement()) {
				String sentencia = "select * from player order by score desc;";
				Gdx.app.log( "InformacionBD", "Sentencia: " + sentencia );
				ResultSet result = statement.executeQuery( sentencia );
				ArrayList<Player> list = new ArrayList<>();
				while( result.next() ) {
					int id = result.getInt("id");
					String nombre = result.getString("nombre");
					int score = result.getInt("score");
					list.add(new Player(id,nombre, score));		
				}
				return list;
			} catch (Exception e) {
				Gdx.app.error( "BD", "Excepcion:", e );
				return null;
			}
		}
	
}

