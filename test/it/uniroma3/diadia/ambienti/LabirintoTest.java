package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;


public class LabirintoTest {
	
	private Labirinto creaLabirinto() throws FileNotFoundException, FormatoFileNonValidoException {
		LabirintoBuilder l = Labirinto.newBuilder(null)
				.addStanzaIniziale("inizio")
				.addStanzaVincente("fine");
		Labirinto labirinto = l.getLabirinto();
	
		return labirinto;
	}
	@Test
	public void testGetStanzaInizialeFunziona() throws FileNotFoundException, FormatoFileNonValidoException {
		assertNotNull(creaLabirinto().getStanzaIniziale());
	}
	@Test
	public void testGetStanzaInizialeIncorretta() throws FileNotFoundException, FormatoFileNonValidoException {
		assertNotEquals("Incorretta",creaLabirinto().getStanzaIniziale().getNome());
	}
	
	@Test
	public void testGetStanzaVincenteFunziona() throws FileNotFoundException, FormatoFileNonValidoException {
		assertNotNull(creaLabirinto().getStanzaVincente());
	}
	@Test
	public void testGetStanzaVincenteIncorretta() throws FileNotFoundException, FormatoFileNonValidoException {
		assertNotEquals("Incorretta",creaLabirinto().getStanzaVincente().getNome());
	}
	//nel labirinto stanza iniziale e finale sono diverse
	@Test
	public void testGetStanza() throws FileNotFoundException, FormatoFileNonValidoException { 
		assertNotEquals(creaLabirinto().getStanzaIniziale(),creaLabirinto().getStanzaVincente());
	}
	

}