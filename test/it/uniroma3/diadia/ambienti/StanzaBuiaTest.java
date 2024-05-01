package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class StanzaBuiaTest {
	
	private StanzaBuia s;

	@Before
	public void setUp() {
		s = new StanzaBuia("Aula N10", "lanterna");	
	}
	
	
	//attrezzo per ottenere descrizione è presente nella stanza
	@Test
	public void testGetDescrizione_AttrezzoPresente() {
		s.addAttrezzo(new Attrezzo("lanterna",10));
		assertEquals(s.getDescrizione(),s.getDescrizione());
	}
		
	//attrezzo per ottenere descrizione NON è presente nella stanza
	@Test
	public void testGetDescrizione_AttrezzoNonPresente() {
		assertEquals(s.getDescrizione(),"Qui c'è buio pesto");
	}
	
	//nella stanza non sono presenti oggetti
	@Test
	public void testGetDescrizione_StanzaVuota() {
		assertEquals(s.getDescrizione(),"Qui c'è buio pesto");
	}
	
}