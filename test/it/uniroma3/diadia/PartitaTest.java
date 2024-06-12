package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

public class PartitaTest {
	
	private Partita creaPartita(String stanzaCorrente,int cfu,boolean finita) throws FileNotFoundException, FormatoFileNonValidoException {
		Labirinto l =Labirinto.newBuilder(null)
				.addStanzaIniziale("StanzaIniziale")
				.addStanzaVincente("StanzaVincente").getLabirinto();
		Partita partita=new Partita(l,new IOConsole());
		
		if(finita)
			partita.setFinita();
		
		partita.getGiocatore().setCfu(cfu);
		if(stanzaCorrente.equals("StanzaVincente"))
			partita.setStanzaCorrente(partita.getLabirinto().getStanzaVincente());
		
		return partita;	
	}

	
	
	@Test
	public void testVinta_InizioPartita_StanzaIniziale() throws FileNotFoundException, FormatoFileNonValidoException {
		assertFalse(creaPartita("StanzaNonVincente",20,false).vinta());
	}
	
	/*@Test
	public void testVinta_PartitaIniziata_StanzaIntermedia_NonVinta() {
		assertFalse(creaPartita("StanzaIntermedia",10,false).vinta());
	}*/
	
	@Test
	public void testVinta_PartitaIniziata_StanzaVincente() throws FileNotFoundException, FormatoFileNonValidoException {
		assertTrue(creaPartita("StanzaVincente",19,false).vinta());
	}
	
	
	
	@Test
	public void testIsFinita_InizioPartita() throws FileNotFoundException, FormatoFileNonValidoException {
		assertFalse(creaPartita("InizioPartita",20,false).isFinita());
	}
	
	@Test
	public void testIsFinita_VerificataPerCFUterminati() throws FileNotFoundException, FormatoFileNonValidoException {
		assertTrue(creaPartita("PartitaFinita_CFUfiniti",0,false).isFinita());
	}
	
	@Test
	public void testIsFinita_VerificataPerVittoria() throws FileNotFoundException, FormatoFileNonValidoException {
		assertTrue(creaPartita("StanzaVincente",16,false).isFinita());
	}
	
	@Test
	public void testIsFinita_VerificataPerPartitaImpostateAfinita() throws FileNotFoundException, FormatoFileNonValidoException {
		assertTrue(creaPartita("PartitaSettataFinita",16,true).isFinita());
	}
	
	

}