package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 */
public class ComandoVai implements Comando  {
	
	private Direzione direzione;
	
	public ComandoVai(String direzione) {
		try {
			this.direzione = Direzione.valueOf(direzione.toUpperCase());
		}catch(Exception e) {
			this.direzione=null;
		}
	}
	public ComandoVai() {
		this(null);
	}

	
	/**
	 * esecuzione del comando
	 */
	 @Override
	 public void esegui(Partita partita) {
	
		 	Stanza stanzaCorrente = partita.getStanzaCorrente();
		 	Stanza prossimaStanza = null;
		 	if(direzione==null) {
		 		partita.getIO().mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
		 		return;					
		 	}
		 	//dir = Direzione.valueOf(this.direzione);//this.direzione.getClass();
		 	prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione.name());
		 	if(prossimaStanza==null) {
		 		partita.getIO().mostraMessaggio("Direzione inesistente");
		 		return;
		 	}
		 	partita.setStanzaCorrente(prossimaStanza);
		 	partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getNome());
		 	partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);	
	 }
	 
	 
	 @Override
	 public void setParametro(String parametro) {
		 try {
			 this.direzione = Direzione.valueOf(parametro.toUpperCase());
		 }catch(Exception e) {
			 this.direzione=null;
		 }
	 }

	 
	@Override
	public String getNome() {
		return "vai";
	}

	
	@Override
	public String getParametro() {
		return this.direzione.toString();
	}
}