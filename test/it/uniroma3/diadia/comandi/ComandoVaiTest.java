package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

public class ComandoVaiTest {

	private Partita p;
	private ComandoVai cv;
	
	@Before
	public void setUp() {
		p = new Partita(new Labirinto(), new IOConsole());
		cv = new ComandoVai("sud");
		cv.esegui(p);
	}
	

	//non ci stiamo spostando
	@Test
	public void testEsegui_Direzione_Null() {
		ComandoVai c = new ComandoVai(null);
		c.esegui(p);
		assertEquals(p.getStanzaCorrente().getNome(), "Aula N10");
	}
	
	
	//ci stiamo muovendo verso una stanza esistente
	@Test
	public void testEsegui_Direzione_Esistente() {
		ComandoVai c = new ComandoVai("est");
		c.esegui(p);
		assertEquals(p.getStanzaCorrente().getNome(), "Aula N11");
	}
	
	
	//ci stiamo muovendo verso una direzione inesistente
	@Test
	public void testEsegui_ProssimaStanza_Inesistente() {
		ComandoVai c=new ComandoVai("sud");
		c.esegui(p);
		assertEquals(p.getStanzaCorrente().getNome(),"Aula N10");
	}


}
