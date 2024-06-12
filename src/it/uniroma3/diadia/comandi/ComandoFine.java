package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Comando "Fine".
 */
public class ComandoFine extends AbstractComando{
	
	/**
	* esecuzione del comando
	*/
	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
		
	}

}
