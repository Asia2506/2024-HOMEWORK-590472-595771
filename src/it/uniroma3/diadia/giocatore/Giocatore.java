package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Giocatore - ha la responsabilità di gestire i CFU del giocatore 
 * e di memorizzare gli attrezzi in un oggetto istanza della classe Borsa
 * 
 * @see DiaDia
 * @see Attrezzo
 */

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	
	private int cfu;
	private Borsa borsa;
	


	public Giocatore() {
		this.cfu=CFU_INIZIALI;
		this.borsa=new Borsa();
	}
	
	
	
	
	public boolean cfuCheck() {
		return this.cfu>0; 
	}
	
	
	public boolean addAttrezzoBorsa(Attrezzo attrezzo) {
		return this.borsa.addAttrezzo(attrezzo);
	}
		
	
	
	public Attrezzo removeAttrezzoBorsa(String nomeAttrezzo) {
		return this.borsa.removeAttrezzo(nomeAttrezzo); 
	}
		
	
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	
	public Borsa getBorsa() {
		return borsa;
	}

	public void setBorsa(Borsa borsa) {
		this.borsa=borsa;
		
	}
	
}

