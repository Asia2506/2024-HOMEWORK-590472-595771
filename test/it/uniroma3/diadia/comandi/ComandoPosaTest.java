package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;


public class ComandoPosaTest {

	private Partita p;
	private Borsa b;
	private Comando c;
	private int numeroAttrezziBorsa;
	
	@Before
	public void setUp() {
		p=new Partita(new Labirinto(),new IOConsole());
		b=p.getGiocatore().getBorsa();
		b.addAttrezzo(new Attrezzo("AttrezzoPresente",10));
		c=new ComandoPosa();
		numeroAttrezziBorsa=b.getNumeroAttrezzi();
	}
	
	
	@Test
	public void testEsegui_ParametroNull() {
		c.setParametro(null);
		c.esegui(p);
		assertEquals(numeroAttrezziBorsa,b.getNumeroAttrezzi());
	}
	
	@Test
	public void testEsegui_AttrezzoPresenteNellaBorsa() {
		c.setParametro("AttrezzoPresente");
		c.esegui(p);
		assertEquals(numeroAttrezziBorsa,b.getNumeroAttrezzi()+1);
	}
	
	@Test
	public void testEsegui_AttrezzoNONPresenteNellaBorsa() {
		c.setParametro("AttrezzoNONPresente");
		c.esegui(p);
		assertEquals(numeroAttrezziBorsa,b.getNumeroAttrezzi());
	}

}
