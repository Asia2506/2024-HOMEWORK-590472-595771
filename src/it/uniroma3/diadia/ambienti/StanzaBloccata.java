package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	
	private Direzione direzioneBloccata; //nome direzione bloccata
	private String nomeAttrezzoSbloccante; //nome dell'attrezzo che consente di sbloccare la direzione bloccata

	public StanzaBloccata(String nome, String nomeDirezioneBloccata,String nomeAttrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = Direzione.valueOf(nomeDirezioneBloccata.toUpperCase());
		this.nomeAttrezzoSbloccante = nomeAttrezzoSbloccante;
	}
	
	public Direzione getDirezioneBloccata() {
		return this.direzioneBloccata;
	}
	public String getNomeAttrezzoSbloccante() {
		return this.nomeAttrezzoSbloccante;
	}


	 /**
     * Se nella stanza non è presente l'attrezzo sbloccante, il metodo ritorna un riferimento alla 
	 * stanza corrente altrimenti ritorna la stanza corrispondente all'uscita specificata
     * @param direzione
     */
	@Override 
	public Stanza getStanzaAdiacente(String dir) {
		if(dir==null)
			return null;
		
		
		if(dir.equals(direzioneBloccata.name()) && !hasAttrezzo(this.nomeAttrezzoSbloccante))
			return this;	
		
		return super.getStanzaAdiacente(dir);
	}
	
	
	/**
	* @return Restituisce una descrizione opportuna: il nome dell'attrezzo 
	* sbloccante  e il nome della direzione bloccata 
	*/
	@Override
	public String getDescrizione() {
		
		String s = super.toString();
		
		if(!hasAttrezzo(this.nomeAttrezzoSbloccante)) 
			s = s + "\nAttenzione: Direzione '" + this.direzioneBloccata + "' BLOCCATA."
					+ "\n"+ "\tAttrezzo sbloccante -> " + this.nomeAttrezzoSbloccante;

		return s;
	}
	
}