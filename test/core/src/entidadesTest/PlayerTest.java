package entidadesTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entidades.Player;


public class PlayerTest {
	private Player p;
	
	@Before
	public void setUp() {
		p = new Player(2,"Julio",5);
	}
	
	@Test
	public void testGetId() {
		assertEquals(2, p.getId());
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("Julio", p.getNombre());
	}
	
	@Test
	public void testGetScore() {
		assertEquals(5, p.getScore());
	}
	
}




