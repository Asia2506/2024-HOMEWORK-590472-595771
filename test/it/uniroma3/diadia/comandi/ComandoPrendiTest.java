package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendiTest {
	
	private Partita p;
	private Comando cp;
	private Borsa b;
	
	@Before
	public void setUp() {
		p = new Partita(new Labirinto(), new IOConsole());
		cp = new ComandoPrendi();
		b = p.getGiocatore().getBorsa();
	}

	//non stiamo prendendo nulla dall'atrio
	@Test
	public void testEsegui_NomeAttrezzoNull() {
		cp.setParametro(null);
		cp.esegui(p);
		assertEquals(p.getStanzaCorrente().getNumeroAttrezzi(),1);
	}

	//prendendiamo l'osso dall'atrio
	@Test
	public void testEsegui_NomeAttrezzoValido() {
		cp.setParametro("osso");
		cp.esegui(p);
		assertTrue(p.getGiocatore().getBorsa().hasAttrezzo("osso"));
	}

	//vogliamo prendere un oggetto che non è presente nell'atrio
	@Test
	public void testEsegui_NomeAttrezzoNonValido() {
		cp.setParametro("lanterna");
		cp.esegui(p);
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("lanterna"));
	}

}
