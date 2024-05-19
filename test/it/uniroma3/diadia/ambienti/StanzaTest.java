package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {

	//private static final int NUMERO_MASSIMO_ATTREZZI = 10;

		//metodo che crea una stanza contenente numeroAttrezziPresenti attrezzi
		private Stanza creaStanza(int numeroAttrezziPresenti) {
			Stanza s1=new Stanza("Test");
			boolean inserimento=true;
			//if(numeroAttrezziPresenti<NUMERO_MASSIMO_ATTREZZI) {
				for(int i=0;i<numeroAttrezziPresenti && inserimento;i++) {
					inserimento=s1.addAttrezzo(new Attrezzo("Attrezzo"+i,i));
				}
				return s1;
			/*}else
				return ;*/
		}
		
		
		
		@Test
		public void testAddAttrezzoStanzaVuota() {
			assertTrue(creaStanza(0).addAttrezzo(new Attrezzo("Lancia",10)));
		}
		
		@Test
		public void testAddAttrezzoStanzaPiena() {
			assertFalse(creaStanza(10).addAttrezzo(new Attrezzo("Lancia",10)));
		}
		
		@Test
		public void testAddAttrezzoStanzaContenenteDegliAttrezzi() {
			assertTrue(creaStanza(5).addAttrezzo(new Attrezzo("Lancia",10)));
		}
		
		/*
		@Test
		public void testAddAttrezzoGiaPresenteNelLabirinto() {
			Labirinto l=new Labirinto();
			Stanza s=l.getStanzaIniziale();
			Attrezzo a=new Attrezzo("Lancia",10);
			s.addAttrezzo(a);
			assertFalse(s.getStanzaAdiacente("nord").addAttrezzo(a));
		}
		*/
		
		
		@Test 
		public void testHasAttrezzoStanzaVuota() {
			assertFalse(creaStanza(0).hasAttrezzo("Inesistente"));
		}
		
		@Test
		public void testHasAttrezzoElementoPresenteNellaStanza() {
			assertTrue(creaStanza(10).hasAttrezzo("Attrezzo9"));
		}
		
		@Test
		public void testHasAttrezzoElementoNonPresenteNellaStanza() {
			assertFalse(creaStanza(10).hasAttrezzo("NonPresente"));
		}
		
		
		
		@Test 
		public void testGetAttrezzoStanzaVuota() {
			assertNull(creaStanza(0).getAttrezzo("inesistente"));
		}
		
		@Test 
		public void testGetAttrezzoStanzaNonVuotaPresente() {
			assertNotNull(creaStanza(1).getAttrezzo("Attrezzo0"));
		}
		
		@Test 
		public void testGetAttrezzoStanzaNonVuotaNonPresente() {
			assertNull(creaStanza(10).getAttrezzo("nomeAttrezzoNonPresente"));
		}
		
		
		
		@Test
		public void testRemoveAttrezzoEsistente() {
			/*Attrezzo a=new Attrezzo("AttrezzoPresente",1);
			Stanza s=creaStanza(7);
			s.addAttrezzo(a);*/
			assertTrue(creaStanza(7).removeAttrezzo(new Attrezzo("Attrezzo0",0)));
		}
		
		@Test
		public void testRemoveAttrezzoNonContenuto() {
			assertFalse(creaStanza(3).removeAttrezzo(new Attrezzo("NonContenuto",7)));
		}
		
		@Test
		public void testRemoveAttrezzoStanzaVuota() {
			assertFalse(creaStanza(0).removeAttrezzo(new Attrezzo("Luce",1)));
		}
		
}