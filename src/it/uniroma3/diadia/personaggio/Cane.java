package it.uniroma3.diadia.personaggio;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{

	private static final String MESSAGGIO = "Sei stato morso...Hai perso un CFU!";
	private static final String MESSAGGIO_APPREZZAMENTO = "BAU,BAU!";
	
	public Cane(String nome, String presentaz) {
		super(nome, presentaz,null);
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		
		return MESSAGGIO;
	}

	
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo==null || !attrezzo.getNome().equals("osso"))
			return agisci(partita);
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		return MESSAGGIO_APPREZZAMENTO;
		
	}
}
