package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {
	

	private StanzaMagica sm;
	private Attrezzo a;

	@Before
	public void setUp() {
		sm = new StanzaMagica("Magica",2);	
		a = new Attrezzo("TroppiAttrezzi",2);
		sm.addAttrezzo(new Attrezzo("lanterna",2));
	}

	//aggiunta di un attrezzo esistente
	@Test
	public void testAddAttrezzoEsistente() {
		assertTrue(sm.addAttrezzo(new Attrezzo("osso",2)));
	}

	//aggiunta di un attrezzo con soglia superata
	@Test
	public void testAddAttrezzoSogliaSuperata() {
		sm.addAttrezzo(new Attrezzo("osso",2));
		sm.addAttrezzo(a);
		assertFalse(sm.hasAttrezzo("TroppiAttrezzi"));
	}
	
	@Test
	public void testModificaAttrezzo_AddAttrezzoSogliaSuperata() {
		sm.addAttrezzo(new Attrezzo("osso",2));
		sm.addAttrezzo(a);
		assertNotNull(sm.getAttrezzo("izzerttAipporT"));
	}
	
}