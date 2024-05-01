package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class GiocatoreTest {

	private Giocatore creaGiocatore(int cfu, Borsa borsa) {
		Giocatore giocatore = new Giocatore();
		Attrezzo attrezzoContenuto = new Attrezzo("Contenuto",10);
		
		giocatore.setCfu(cfu);
		giocatore.setBorsa(borsa);
		borsa.addAttrezzo(attrezzoContenuto);
		
		return giocatore; 
	}
	
	@Test
	public void testAddAttrezzoCorrettamente() {
		assertTrue(creaGiocatore(3,new Borsa(20)).addAttrezzoBorsa(new Attrezzo("Lancia",3)));
	}
	@Test
	public void testAddAttrezzoPesoAlLimite() {
		assertTrue(creaGiocatore(3,new Borsa(20)).addAttrezzoBorsa(new Attrezzo("Lancia",10)));
	}
	@Test
	public void testAddAttrezzoPesoTroppoGrande() {
		assertFalse(creaGiocatore(3,new Borsa(10)).addAttrezzoBorsa(new Attrezzo("Lancia",20)));
	}
	
	@Test
	public void testRemoveAttrezzoNonContenuto() {
		assertEquals(null,creaGiocatore(3,new Borsa(10)).removeAttrezzoBorsa("Non Contenuto"));
	}

	@Test
	public void testRemoveAttrezzoContenuto() {
		Giocatore giocatore=creaGiocatore(3,new Borsa(10));
		Attrezzo attrezzoContenuto =giocatore.getBorsa().getAttrezzo("Contenuto");
		assertEquals(attrezzoContenuto,giocatore.removeAttrezzoBorsa("Contenuto"));
	}
	
	public void testRemoveAttrezzoContenutoNotNull() {
		assertNotNull(creaGiocatore(3,new Borsa(10)).removeAttrezzoBorsa("Contenuto"));
	}
	
	

	@Test
	public void testGetCfuCorretti() {
		assertEquals(20, creaGiocatore(20,new Borsa(10)).getCfu());
	}
	@Test
	public void testGetCfuIncorretti() {
		assertNotEquals(2, creaGiocatore(3,new Borsa(10)).getCfu());
	}
	@Test
	public void testGetCfuFiniti() {
		assertEquals(0, creaGiocatore(0,new Borsa(10)).getCfu());
	}
	


}
