package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 */
public class ComandoAiuto extends AbstractComando{
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa","guarda","saluta","interagisci","regala"};
	
	/**
	* esecuzione del comando
	*/
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			partita.getIO().mostraMessaggio(elencoComandi[i]+" ");
		partita.getIO().mostraMessaggio("");
		
	}

}
