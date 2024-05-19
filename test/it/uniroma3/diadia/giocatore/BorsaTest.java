package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

import java.util.*;

public class BorsaTest {
	
	private Borsa borsa;
	
	
	@Before
	public void setUp(){
		borsa = new Borsa();
		borsa.addAttrezzo(new Attrezzo("piuma",1));
		borsa.addAttrezzo(new Attrezzo("libro",5));
		borsa.addAttrezzo(new Attrezzo("ps",5));
		borsa.addAttrezzo(new Attrezzo("piombo",10));
	}
	
	private Borsa creaBorsa(int numeroAttrezzi) {
		borsa=new Borsa();
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
	
	
	/**
	 * test per verificare che due attrezzi di stesso peso ma nome 
	 * diverso rimangano distinti nel risultato
	*/
	@Test
	public void testDueAttrezziStessoPesoNomeDiverso() {
		List<Attrezzo> listOrdinataPerPeso = new ArrayList<>();
		listOrdinataPerPeso.add(new Attrezzo("AttrezzoPresente0",1));
		listOrdinataPerPeso.add(new Attrezzo("AttrezzoPresente1",1));
		assertEquals(creaBorsa(2).getContenutoOrdinatoPerPeso(),listOrdinataPerPeso);
	}
	@Test
	public void testgetContenutoOrdinatoPerPeso() {
		List<Attrezzo> listOrdinataPerPeso = new ArrayList<>();
		listOrdinataPerPeso.add(new Attrezzo("piuma",1));
		listOrdinataPerPeso.add(new Attrezzo("libro",5));
		assertEquals(borsa.getContenutoOrdinatoPerPeso(),listOrdinataPerPeso);
	}
	@Test
	public void testgetContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> setOrdinataPerNome = new TreeSet<>();
		setOrdinataPerNome.add(new Attrezzo("libro",5));
		setOrdinataPerNome.add(new Attrezzo("piuma",1));
		assertEquals(borsa.getContenutoOrdinatoPerNome(),setOrdinataPerNome);
	}
	@Test
	public void testgetContenutoRaggruppatoPerPeso() {
		Set<Attrezzo> attrezzi1 = new HashSet<>();
		attrezzi1.add(new Attrezzo("piuma",1));
		Set<Attrezzo> attrezzi2 = new HashSet<>();
		attrezzi2.add(new Attrezzo("libro",5));
		Map<Integer,Set<Attrezzo>>  mappaRaggruppataPerPeso = new HashMap<>();
		mappaRaggruppataPerPeso.put(1,attrezzi1);
		mappaRaggruppataPerPeso.put(5,attrezzi2);
		assertEquals(borsa.getContenutoRaggruppatoPerPeso(),mappaRaggruppataPerPeso);
	}
}
	

