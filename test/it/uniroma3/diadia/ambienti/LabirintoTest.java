package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

public class LabirintoTest {

	private Labirinto creaLabirinto() {
		Labirinto labirinto = new Labirinto();
	
		return labirinto;
	}
	
	
	@Test
	public void testGetStanzaInizialeFunziona() {
		assertNotNull(creaLabirinto().getStanzaIniziale());
	}
	
	@Test
	public void testGetStanzaInizialeIncorretta() {
		assertNotEquals("Incorretta",creaLabirinto().getStanzaIniziale().getNome());
	}
	
	@Test
	public void testGetStanzaVincenteFunziona() {
		assertNotNull(creaLabirinto().getStanzaVincente());
	}
	
	@Test
	public void testGetStanzaVincenteIncorretta() {
		assertNotEquals("Incorretta",creaLabirinto().getStanzaVincente().getNome());
	}
	
	
	//nel labirinto stanza iniziale e finale sono diverse
	@Test
	public void testGetStanza() { 
		assertNotEquals(creaLabirinto().getStanzaIniziale(),creaLabirinto().getStanzaVincente());
	}


}
