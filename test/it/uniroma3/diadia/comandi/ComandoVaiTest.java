package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

public class ComandoVaiTest {
	
	private Partita p;
	
	
	@Before
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		Labirinto l = Labirinto.newBuilder(null)
				.addStanzaIniziale("ingresso")
				.addStanzaVincente("terrazza")
				.addStanza("salotto")
				.addStanza("cucina")
				.addAdiacenza("ingresso","salotto","nord")
				.addAdiacenza("salotto","ingresso", "sud")
				.addAdiacenza("salotto", "cucina","est")
				.addAdiacenza("cucina","salotto", "ovest")
				.addAdiacenza("cucina", "terrazza","nord")
				.addAdiacenza("terrazza","cucina", "sud").getLabirinto();
		p = new Partita(l, new IOConsole());
	}
	

	//non ci stiamo spostando
	@Test
	public void testEsegui_Direzione_Null() {
		ComandoVai c = new ComandoVai(null);
		c.esegui(p);
		assertEquals(p.getStanzaCorrente().getNome(),"ingresso");
	}
	
	//ci stiamo muovendo verso una stanza esistente
	@Test
	public void testEsegui_Direzione_Esistente() {
		ComandoVai c = new ComandoVai("nord");
		c.esegui(p);
		assertEquals(p.getStanzaCorrente().getNome(), "salotto");
	}
	
	//ci stiamo muovendo verso una direzione inesistente
	@Test
	public void testEsegui_ProssimaStanza_Inesistente() {
		ComandoVai c = new ComandoVai("sud");
		c.esegui(p);
		assertEquals(p.getStanzaCorrente().getNome(), "ingresso");
	}

}