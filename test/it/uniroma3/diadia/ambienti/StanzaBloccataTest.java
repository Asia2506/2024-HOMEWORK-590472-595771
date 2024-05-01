package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	
	private StanzaBloccata sb;
	private Stanza sNord;
	private Stanza sSud;
	
	@Before
	public void setUp() {
		sb=new StanzaBloccata("Corrente","nord","piedediporco");
		sNord=new Stanza("StanzaNord");
		sSud=new Stanza("StanzaSud");
		sb.impostaStanzaAdiacente("nord",sNord );
		sb.impostaStanzaAdiacente("sud", sSud);
	}
	
	
	@Test
	public void testGetStanzaAdiacente_direzioneNull() {
		assertNull(sb.getStanzaAdiacente(null));
	}
	
	@Test
	public void testGetStanzaAdiacente_direzioneNonBloccata() {
		assertEquals(sb.getStanzaAdiacente("sud").getNome(),sSud.getNome());
	}
	
	@Test
	public void testGetStanzaAdiacente_direzioneBloccataAttrezzoSbloccantePresente() {
		sb.addAttrezzo(new Attrezzo("piedediporco",20));
		assertEquals(sb.getStanzaAdiacente("nord").getNome(),sNord.getNome());
	}
	
	@Test
	public void testGetStanzaAdiacente_direzioneBloccataAttrezzoSbloccanteNONPresente() {
		//sb.addAttrezzo(new Attrezzo("lancia",10));
		assertEquals(sb.getStanzaAdiacente("nord").getNome(),sb.getNome());
	}

}
