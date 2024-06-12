package it.uniroma3.diadia.personaggio;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio{
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	
	private static final String MESSAGGIO_REGALO = "Grazie del regalo!";
	
	
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione,attrezzo);
	}
	
	
	@Override
	public String agisci(Partita partita) {
		String msg;
		if (super.getAttrezzo()!=null) {
			partita.getStanzaCorrente().addAttrezzo(super.getAttrezzo());
			super.setAttrezzo(null);
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}

	
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo==null)
			return null;
		Attrezzo nuovoAttrezzo=new Attrezzo(attrezzo.getNome(),attrezzo.getPeso()/2);
		partita.getStanzaCorrente().addAttrezzo(nuovoAttrezzo);
		return MESSAGGIO_REGALO;
		
	}
}