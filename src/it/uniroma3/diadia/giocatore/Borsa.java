package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configuratore;
import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


/**
 * Classe Borsa - Definisce una borsa caratterizzata da un peso massimo 
 * e un numero massimo di attrezzi da contenere.
 * 
 * @see DiaDia
 * @see Attrezzo
 */


public class Borsa {		
	
	
	public final static int DEFAULT_PESO_MAX_BORSA = Configuratore.getPesoMax();
	
	private List<Attrezzo> attrezzi;
	private int pesoMax;
	
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi=new ArrayList<Attrezzo>();
	}
	
	
	/**
     * Mette un attrezzo nella borsa.
     * @param attrezzo l'attrezzo da mettere nella borsa.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		return this.attrezzi.add(attrezzo);
	}
	
	
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	
	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella borsa.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella borsa.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {	
		if(this.attrezzi.isEmpty() || !hasAttrezzo(nomeAttrezzo))
			return null;
		
		Attrezzo a=new Attrezzo(nomeAttrezzo,0);
		return this.attrezzi.get(this.attrezzi.indexOf(a));
	}
	
	/**
	 * Restituisce il peso totale del contenuto della borsa.
	 * @return somma dei pesi degli attrezzi contenuti nella borsa.
	 */
	public int getPeso(){
		int pesoTotale = 0;
		Iterator<Attrezzo> iteratore =this.attrezzi.iterator();
		
		while (iteratore.hasNext()) {
			Attrezzo a = iteratore.next();
			pesoTotale += a.getPeso();
		}
		
		return pesoTotale;
	}
	
	
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	
	/**
	* Controlla se un attrezzo esiste nella borsa (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella borsa, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.contains(new Attrezzo(nomeAttrezzo,0));//getAttrezzo(nomeAttrezzo)!=null;
	}
	
	
	/**
	 * Rimuove un attrezzo dalla borsa (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = this.getAttrezzo(nomeAttrezzo);
		this.attrezzi.remove(a);
		return a;
	}
	
	
	/**
	 * restituisce la lista degli attrezzi nella borsa ordinati per peso e quindi, 
	 * a parità di peso, per nome
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		final List<Attrezzo> ordinata= new ArrayList<>(this.attrezzi);
		final ComparatorePerPeso cmp=new ComparatorePerPeso();

		Collections.sort(ordinata,cmp);
		return ordinata;
	}
	
	
	/**
	 * restituisce l'insieme degli attrezzi nella borsa ordinati per peso e quindi, 
	 * a parità di peso, per nome
	 * @return
	 */
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		final ComparatorePerPeso cmp=new ComparatorePerPeso();
		final SortedSet<Attrezzo> ordinata=new TreeSet<>(cmp);
		
		ordinata.addAll(this.attrezzi);
		return ordinata;
	}
	
	
	/**
	 * restituisce l'insieme degli attrezzi nella borsa ordinati per nome
	 */
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		final SortedSet<Attrezzo> ordinata=new TreeSet<>(this.attrezzi);
		return ordinata;
	}
	
	
	/**
	 * restituisce una mappa che associa un intero (rappresentante un peso) 
	 * con l’insieme (comunque non vuoto) degli attrezzi di tale peso: 
	 * tutti gli attrezzi dell'insieme che figura come valore hanno lo stesso peso 
	 * pari all'intero che figura come chiave
	 */
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		final Map<Integer,Set<Attrezzo>> peso2attrezzi = new HashMap<>();
		
		for(Attrezzo attrezzo : this.attrezzi) {
			if(peso2attrezzi.containsKey(attrezzo.getPeso())){
				//questo attrezzo ha un peso che ho gia visto in precedenza
				 //pesco il vecchio Set di Attrezzi con questo peso e aggiungo il nuovo Peso
				final Set<Attrezzo> stessoPeso = peso2attrezzi.get(attrezzo.getPeso());
				stessoPeso.add(attrezzo);
				
			}
			else{
				//questo attrezzo ha un peso mai visto prima
				//creo un nuovo set per ospitare tutti gli attrezzi correnti e futuri con questo peso 
				final Set<Attrezzo> nuovoSet = new HashSet<>();
				peso2attrezzi.put(attrezzo.getPeso(),nuovoSet);
				nuovoSet.add(attrezzo);
			}	
		}
		
		return peso2attrezzi;
	}
	
	public String guarda(List<Attrezzo> lista) {
		return lista.toString();
	}
	
	public String guarda(Set<Attrezzo> set) {
		StringBuilder s = new StringBuilder();
		
		Iterator<Attrezzo> iteratore=set.iterator();
		
		s.append("{ ");
		if(iteratore.hasNext())
			s.append(iteratore.next().toString());
		while(iteratore.hasNext())
			s.append(", "+iteratore.next().toString());
		s.append(" }");
		
		return s.toString();
	}
	
	
	public String guarda(Map<Integer,Set<Attrezzo>> mappa) {
		StringBuilder s = new StringBuilder();
		
		Set<Integer> pesi=mappa.keySet();
		Iterator<Integer> iteratore=pesi.iterator();
		Integer chiave;
		
		if(iteratore.hasNext()) {
			chiave=iteratore.next();
			s.append("("+chiave.toString()+","+this.guarda(mappa.get(chiave))+" )");
		}
		while(iteratore.hasNext()) {
			chiave=iteratore.next();
			s.append(";("+chiave.toString()+","+this.guarda(mappa.get(chiave))+" )");
		}
		
		
		return s.toString();
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
			s.append(this.attrezzi.toString()+"\n");
			s.append(guarda(this.getContenutoOrdinatoPerNome())+"\n");
			s.append(guarda(this.getContenutoOrdinatoPerPeso())+"\n");
			s.append(guarda(this.getContenutoRaggruppatoPerPeso()));
		}
		else
			s.append("Borsa vuota");
		return s.toString();
		
	}
	
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}

}