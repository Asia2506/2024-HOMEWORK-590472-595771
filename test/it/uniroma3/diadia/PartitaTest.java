package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {
	
	private Partita creaPartita(String stanzaCorrente,int cfu,boolean finita) {
		Partita partita=new Partita(new Labirinto(),new IOConsole());
		
		if(finita)
			partita.setFinita();
		
		partita.getGiocatore().setCfu(cfu);
		if(stanzaCorrente.equals("StanzaVincente"))
			partita.setStanzaCorrente(partita.getLabirinto().getStanzaVincente());
		
		return partita;	
	}

	
	
	@Test
	public void testVinta_InizioPartita_StanzaIniziale() {
		assertFalse(creaPartita("StanzaNonVincente",20,false).vinta());
	}
	
	/*@Test
	public void testVinta_PartitaIniziata_StanzaIntermedia_NonVinta() {
		assertFalse(creaPartita("StanzaIntermedia",10,false).vinta());
	}*/
	
	@Test
	public void testVinta_PartitaIniziata_StanzaVincente() {
		assertTrue(creaPartita("StanzaVincente",19,false).vinta());
	}
	
	
	
	@Test
	public void tastIsFinita_InizioPartita() {
		assertFalse(creaPartita("InizioPartita",20,false).isFinita());
	}
	
	@Test
	public void tastIsFinita_VerificataPerCFUterminati() {
		assertTrue(creaPartita("PartitaFinita_CFUfiniti",0,false).isFinita());
	}
	
	@Test
	public void tastIsFinita_VerificataPerVittoria() {
		assertTrue(creaPartita("StanzaVincente",16,false).isFinita());
	}
	
	@Test
	public void tastIsFinita_VerificataPerPartitaImpostateAfinita() {
		assertTrue(creaPartita("PartitaSettataFinita",16,true).isFinita());
	}
	
	

}
