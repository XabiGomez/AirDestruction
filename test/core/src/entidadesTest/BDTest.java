package entidadesTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entidades.BD;
import entidades.Player;
//No funciona correctamente porque al crear JUnit sobre una clase de libgdx crear un error que no sabemos solucionar
public class BDTest {

	@Before
	public void setUp() throws Exception {
		BD.conexionBd("BDtesteo.bd", true);
	}
	
	@After
	public void tearDown() throws Exception {
		BD.cierreBD();
	}
	
	@Test
	public void testConexionBd() {
		BD.cierreBD();
		assertTrue(BD.conexionBd("BDtesteo.bd", true));
	}
	
	@Test
	public void testInsertarPlayer() {
		Player p = new Player(1, "Rodrigo", 10);
		assertEquals(true,BD.insertarPlayer(p));
	}
	
	@Test
	public void testGetPlayers() {
		ArrayList<Player> list = new ArrayList<>();
		Player p = new Player(1, "Rodrigo", 10);
		list.add(p);
		assertEquals(list, BD.getPlayers());
	}
}
