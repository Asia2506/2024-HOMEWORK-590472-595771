package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica comando;
	
	@Before
	public void setUp() {
		comando=new FabbricaDiComandiFisarmonica();
	}
	
	@Test
	public void testCostruisciComandoVai() {
		assertEquals(comando.costruisciComando("vai").getNome(),"vai");
	}
	
	@Test
	public void testCostruisciComandoInesistente() {
		assertEquals(comando.costruisciComando("ComandoInesistente").getNome(),"Comando non valido");
	}
	
	@Test
	public void testCostruisciComandoFine() {
		assertEquals(comando.costruisciComando("fine").getNome(),"fine");
	}
	
	@Test
	public void testCostruisciComandoGuarda() {
		assertEquals(comando.costruisciComando("guarda").getNome(),"guarda");
	}
}
