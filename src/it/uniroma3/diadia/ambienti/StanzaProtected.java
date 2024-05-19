package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaProtected {
	static final protected int NUMERO_MASSIMO_DIREZIONI = 4;
	static final protected int NUMERO_MASSIMO_ATTREZZI = 10;
	
	protected String nome;
	protected List<Attrezzo> attrezzi;
	protected Map<String,Stanza> stanzeAdiacenti;
	
    
	
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
	public StanzaProtected(String descrizione){
		this.stanzeAdiacenti = new HashMap<>();
		this.attrezzi = new ArrayList<>();
		this.nome = descrizione;
	}
    
	
	@Override
	public boolean equals(Object o) {
		StanzaProtected that=(StanzaProtected)o;
		return this.nome.equals(that.getNome());
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}
	
    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanzaAdiacente) {
    	if(this.getDirezioni().size()<NUMERO_MASSIMO_DIREZIONI) 
    		this.stanzeAdiacenti.put(direzione,stanzaAdiacente);
    		
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}


    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if(this.attrezzi.size()<NUMERO_MASSIMO_ATTREZZI)
    		return this.attrezzi.add(attrezzo);
    	else
    		return false;
    }
    
    
   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	risultato.append(this.getDirezioni().toString());
    	risultato.append("\nAttrezzi nella stanza: ");
    	risultato.append(this.attrezzi.toString());
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		
		return this.attrezzi.contains(new Attrezzo(nomeAttrezzo,0));
	}

	
	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo.isEmpty() || !hasAttrezzo(nomeAttrezzo))
			return null;
		Attrezzo a=new Attrezzo(nomeAttrezzo,0);
		return this.attrezzi.get(this.attrezzi.indexOf(a));
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo);
	}


	public List<String> getDirezioni() {
		return new ArrayList<String>(this.stanzeAdiacenti.keySet());
    }
	
	
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}
	
	/**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public List<Attrezzo> getAttrezzi() {
        return this.attrezzi;
    }
    
    
    public Map<String,Stanza> getMapStanzeAdiacenti(){
    	return this.stanzeAdiacenti;
    }
    
    
    public boolean isMagica() {
		return false;
	}



	

}
