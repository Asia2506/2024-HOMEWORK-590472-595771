package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni sulla stanza e sullo stato della partita.
 */

public class ComandoGuarda extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione()+
										"\nCFU disponibili: "+partita.getGiocatore().getCfu()+
										"\n"+partita.getGiocatore().getBorsa());	
		
	}

}
