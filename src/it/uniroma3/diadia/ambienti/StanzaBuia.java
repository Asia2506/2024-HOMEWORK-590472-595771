package it.uniroma3.diadia.ambienti;


/**«stanza buia»: 
 * se nella stanza non è presente un attrezzo con un nome particolare
 * (ad esempio "lanterna") il metodo getDescrizione() di una stanza buia 
 * ritorna la stringa "qui c'è un buio pesto"
 * 
 * @author Asia, Isabella
 */


public class StanzaBuia extends Stanza{
	
	//variabili di istanza
	private String nomeAttrezzo;

	//costruttore
	public StanzaBuia(String nome,String nomeAttrezzo) {
		super(nome);
		this.nomeAttrezzo=nomeAttrezzo;
	}


	/**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
	@Override
	public String getDescrizione() {

		if(this.hasAttrezzo(nomeAttrezzo))
			return super.getDescrizione();

		return "Qui c'è buio pesto";
	}


	//metodo getter
	public String getNomeAttrezzo() {
		return nomeAttrezzo;
	}




}
