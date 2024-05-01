package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	
	private Borsa creaBorsa(int numeroAttrezzi) {
		Borsa borsa=new Borsa();
		boolean piena=false;
		for(int i=0;i<numeroAttrezzi && !piena;i++) {
			borsa.addAttrezzo(new Attrezzo("AttrezzoPresente"+i,1));
		}
		return borsa;
		
	}

	
	
	@Test
	public void testAddAttrezzoBorsaVuota() {
		assertTrue(creaBorsa(0).addAttrezzo(new Attrezzo("Inserito",10)));
	}
	
	@Test
	public void testAddAttrezzoLimitePesoSuperato() {
		assertFalse(creaBorsa(5).addAttrezzo(new Attrezzo("NonInseritoPesoEccessivo",10)));
	}
	
	@Test
	public void testAddAttrezzoLimiteOggettiSuperato() {
		assertFalse(creaBorsa(10).addAttrezzo(new Attrezzo("NonInseritoBorsaPiena",10)));
	}
	
	
	@Test
	public void testGetAttrezzoBorsaVuota() {
		assertNull(creaBorsa(0).getAttrezzo("NonPresente_BorsaVuota"));
	}
	
	@Test
	public void testGetAttrezzoPresente() {
		assertNotNull(creaBorsa(6).getAttrezzo("AttrezzoPresente1"));
	}
	
	@Test
	public void testGetAttrezzoNonPresente() {
		assertNull(creaBorsa(9).getAttrezzo("AttrezzoNonPresente"));
	}
	
	
	@Test
	public void testRemoveAttrezzoBorsaVuota() {
		assertNull(creaBorsa(0).removeAttrezzo("NonRimovibile_BorsaVuota"));
	}
	
	@Test
	public void testRemoveAttrezzoAvvenutoConSuccesso() {
		assertNotNull(creaBorsa(8).removeAttrezzo("AttrezzoPresente3"));
	}
	
	@Test
	public void testRemoveAttrezzoNonEsistente() {
		assertNull(creaBorsa(8).removeAttrezzo("AttrezzoNonPresenteNellaBorsa"));
	}
}
