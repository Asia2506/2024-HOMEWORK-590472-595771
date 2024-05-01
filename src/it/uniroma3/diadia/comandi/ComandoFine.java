package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Comando "Fine".
 */
public class ComandoFine implements Comando{

	/**
	* esecuzione del comando
	*/
	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
		
	}

	@Override
	public void setParametro(String parametro) { }

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public String getNome() {
		return "fine";
	}
	
}
