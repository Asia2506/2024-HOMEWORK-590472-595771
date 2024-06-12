package it.uniroma3.diadia;
import java.io.FileNotFoundException;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO, Asia, Isabella
 * @see Stanza
 * @see Labirinto
 * @see Giocatore
 * @version base
 */

public class Partita {

	private Stanza stanzaCorrente;
	private Labirinto labirinto;
	private boolean finita;
	private Giocatore giocatore;
	private IO io;
	
	
	public Partita(Labirinto labirinto,IO io){
		this.labirinto=labirinto;
		this.stanzaCorrente=this.labirinto.getStanzaIniziale();
		this.finita = false;
		this.giocatore = new Giocatore();
		this.io=io;
	}

    
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== this.getLabirinto().getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}
	
	
	
	public boolean giocatoreIsVivo() {
		return this.giocatore.cfuCheck();
	}
	

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	
	
	//metodi getters and setters
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	public void setLabirinto(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
		this.labirinto=Labirinto.newBuilder(labirinto).getLabirinto();
	}
	
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}


	public IO getIO() {
		return io;
	}


	
}
