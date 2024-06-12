package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggio.Mago;
import it.uniroma3.diadia.personaggio.Strega;

public class AbstractComandoTest {
	
	private Mago m;
	private Strega s;
	private Attrezzo attrezzoMago, attrezzoGiocatore, attrezzo1;
	private Partita p;
	
	@Before
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException, InstantiationException, IllegalAccessException, ClassNotFoundException{
	
		
		String nomeStanzaMago = "StanzaMago";
		String nomeStanzaStrega = "StanzaStrega";
		attrezzoMago = new Attrezzo("bacchetta", 1);
		attrezzo1 = new Attrezzo("lanterna", 1);
		m = new Mago("Merlino","Ciao, sono Mago Merlino", attrezzoMago);
		s = new Strega("Morgana","Hihihi");
		
		Labirinto l = Labirinto.newBuilder(null)
				.addStanzaIniziale(nomeStanzaMago)
				.addMago(m.getNome(), m.getPresentazione(),m.getAttrezzo())
				.addAttrezzo(attrezzo1.getNome(), attrezzo1.getPeso())
				.addStanzaVincente(nomeStanzaStrega)
				.addStrega(s.getNome(), s.getPresentazione())
				.addAdiacenza(nomeStanzaMago, nomeStanzaStrega, "nord")
				.addAdiacenza(nomeStanzaStrega, nomeStanzaMago, "sud")
				.getLabirinto();	
		p=new Partita(l,new IOConsole());
		attrezzoGiocatore = new Attrezzo("torcia", 2);
		p.getGiocatore().addAttrezzoBorsa(attrezzoGiocatore);
		
	}
	
	
	public ComandoRegala creaComandoRegala() {
		ComandoRegala cr = new ComandoRegala();
		cr.setParametro(attrezzoGiocatore.getNome());
		return cr;
	}
	
	public ComandoSaluta creaComandoSaluta() {
		ComandoSaluta cs = new ComandoSaluta();
		return cs;
	}

	
	@Test
	public void testRegalaAttrezzoAlMago() {
		
		assertEquals(p.getGiocatore().getBorsa().getPeso(), 2);
		assertTrue(p.getGiocatore().getBorsa().hasAttrezzo(attrezzoGiocatore.getNome()));
		creaComandoRegala().esegui(p);
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo(attrezzoGiocatore.getNome()));
		assertEquals(p.getGiocatore().getBorsa().getPeso(), 0);
		assertTrue(p.getLabirinto().getStanzaIniziale().hasAttrezzo(attrezzoGiocatore.getNome()));
	}
	
	
	@Test
	public void testSalutaIlMago() {
		creaComandoSaluta().esegui(p);
		assertTrue(p.getStanzaCorrente().getPersonaggio().haSalutato());
		Attrezzo attrezzoDelMago = p.getStanzaCorrente().getPersonaggio().getAttrezzo();
		p.getStanzaCorrente().getPersonaggio().agisci(p);
		assertTrue(p.getStanzaCorrente().hasAttrezzo(attrezzoDelMago.getNome()));
	}
	
	
	@Test
	public void testRegalaAllaStrega() {
		p.setStanzaCorrente(p.getLabirinto().getStanzaVincente());
		creaComandoRegala().esegui(p);
	}
	
	
	@Test
	public void testSalutaLaStrega() {
		
		p.setStanzaCorrente(p.getLabirinto().getStanzaVincente());
		creaComandoSaluta().esegui(p);
		assertTrue(p.getStanzaCorrente().getPersonaggio().haSalutato());
		p.getStanzaCorrente().getPersonaggio().agisci(p);
		assertEquals(p.getStanzaCorrente(), p.getLabirinto().getStanzaIniziale());
		
	}
	
}