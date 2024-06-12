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
		assertEquals(comando.costruisciComando("ComandoInesistente").getNome(),new ComandoNonValido().getNome());
	}
	
	@Test
	public void testCostruisciComandoFine() {
		assertEquals(comando.costruisciComando("fine").getNome(),new ComandoFine().getNome());
	}
	
	@Test
	public void testCostruisciComandoGuarda() {
		assertEquals(comando.costruisciComando("guarda").getNome(),new ComandoGuarda().getNome());
	}
}
