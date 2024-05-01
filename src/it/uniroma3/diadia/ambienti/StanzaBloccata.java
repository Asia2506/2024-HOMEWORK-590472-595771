package it.uniroma3.diadia.ambienti;

/**«stanza bloccata»: 
 * una delle direzioni della
 * stanza non può essere seguita a meno che
 * nella stanza non sia presente un oggetto con un
 * nome particolare (ad esempio "passepartout")
 */

public class StanzaBloccata extends Stanza {
	
	//variabili di istanza
	private String nomeDirezioneBloccata;
	private String nomeAttrezzoSbloccante;

	//costruttore
	public StanzaBloccata(String nome, String nomeDirezioneBloccata,String nomeAttrezzoSbloccante) {
		super(nome);
		this.nomeDirezioneBloccata = nomeDirezioneBloccata;
		this.nomeAttrezzoSbloccante = nomeAttrezzoSbloccante;
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
		
		
		if(dir.equals(nomeDirezioneBloccata) && !hasAttrezzo(this.nomeAttrezzoSbloccante)) {
			return this;
		}
		
		return super.getStanzaAdiacente(dir);
	}
	
	
	/**
     * Se nella stanza non è presente l'attrezzo sbloccante, il metodo ritorna un riferimento alla 
	 * stanza corrente altrimenti ritorna la stanza corrispondente all'uscita specificata
     * @param direzione
     */
	@Override
	public String getDescrizione() {
		String s=super.getDescrizione();
		
		if(!hasAttrezzo(nomeAttrezzoSbloccante))
			s=s+"\nAttenzione: Direzione '"+this.nomeDirezioneBloccata+"' BLOCCATA.\n"
					+ "\tAttrezzo Sbloccante-> "+this.nomeAttrezzoSbloccante;
		
		return s;
	}
	
	
	
	//metodi getters and setters
	public String getNomeDirezioneBloccata() {
		return nomeDirezioneBloccata;
	}
	
	public String getNomeAttrezzoSbloccante() {
		return nomeAttrezzoSbloccante;
	}
	
	
	
}
