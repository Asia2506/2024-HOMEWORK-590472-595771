package it.uniroma3.diadia.personaggio;

import java.util.Collection;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{
	private static final String MESSAGGIO_POSITIVO = "Sei stato spostato nella stanza con più attrezzi!";
	
	private static final String MESSAGGIO_NEGATIVO = "Sei stato spostato nella stanza con meno attrezzi!";
	
	private static final String MESSAGGIO_RISATA = "AHAHAHAHAH!";
	
	
	
	public Strega(String nome, String presentaz) {
		super(nome, presentaz,null);
	}

	
	@Override
	public String agisci(Partita partita) {
		Collection<Stanza> adiacenti=partita.getStanzaCorrente().getMapStanzeAdiacenti().values();
		
		if(!adiacenti.iterator().hasNext())
			return null;
		
		Stanza destinazione=partita.getStanzaCorrente();
		String msg=null;
		if(this.haSalutato()) {
			msg=MESSAGGIO_POSITIVO;
			for(Stanza s:adiacenti) {
				if(destinazione.getNumeroAttrezzi()<s.getNumeroAttrezzi())
					destinazione=s;
			}
		}else {
			msg=MESSAGGIO_NEGATIVO;
			for(Stanza s:adiacenti) {
				if(destinazione.getNumeroAttrezzi()>s.getNumeroAttrezzi())
					destinazione=s;
			}
		}
		partita.setStanzaCorrente(destinazione);
		return msg;
	}
	
	
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo==null)
			return null;
		
		return MESSAGGIO_RISATA;
		
	}

}