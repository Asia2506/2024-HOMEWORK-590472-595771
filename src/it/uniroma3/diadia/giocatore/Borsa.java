package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.attrezzi.Attrezzo;



/**
 * Classe Borsa - Definisce una borsa caratterizzata da un peso massimo 
 * e un numero massimo di attrezzi da contenere.
 * 
 * @see DiaDia
 * @see Attrezzo
 */


public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}
	
	
	/**
     * Mette un attrezzo nella borsa.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}
	
	
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	
	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella borsa.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi && a==null; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
					a = attrezzi[i];
		
		return a;
	}
	
	/**
	 * Restituisce il peso totale del contenuto della borsa.
	 * @return somma dei pesi degli attrezzi contenuti nella borsa.
	 */
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();

		return peso;
	}
	
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	
	/**
	* Controlla se un attrezzo esiste nella borsa (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella borsa, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	
	/**
	 * Rimuove un attrezzo dalla borsa (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo CancellaAttrezzo = null;
		CancellaAttrezzo=getAttrezzo(nomeAttrezzo);
		boolean trovato=false;
		if(CancellaAttrezzo!=null) {
			for(int i=0;i<this.numeroAttrezzi && !trovato;i++) {
				if(this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
					trovato=true;
					for(int j=i;j<this.numeroAttrezzi-1;j++)
						this.attrezzi[j]=this.attrezzi[j+1];
				}	
			}
			this.numeroAttrezzi--;
		}
		return CancellaAttrezzo;
	}
	
	/**
	* Restituisce una rappresentazione stringa di questa borsa,
	* stampadone la descrizione(peso massimo e peso corrente) e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
		
	}
	
	public int getNumeroAttrezzi() {
		return this.numeroAttrezzi;
	}

}