package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Una stanza magica ha delle particolarità, che la
 * rendono diversa dalla stanza ordinaria:
 * 
 *	– dopo N volte che in tale stanza viene posato (aggiunto)
 *	un qualsiasi attrezzo da parte del giocatore, la stanza
 *	inizierà a comportarsi «magicamente»
 *
 *	– quando la stanza si comporta magicamente, ogni volta
 *	che posiamo un attrezzo, la stanza "inverte" il nome
 *	dell'attrezzo e ne raddoppia il peso.
 *
 *	– quando la stanza non si comporta magicamente, il
 *	comportamento rimane quello usuale
 */

public class StanzaMagica extends Stanza {
	
	final static private int SOGLIA_MAGICA_DEFAULT=3;
	
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	
	
	
	public StanzaMagica(String nome,int soglia) {
		super(nome);
		this.contatoreAttrezziPosati=0;
		this.sogliaMagica=soglia;
	}
	
	public StanzaMagica(String nome) {
		this(nome,SOGLIA_MAGICA_DEFAULT);
	}
	
	
	
	/**
	 * Applica il comportamento magico.
	 * Inverte il nome dell'attrezzo e
	 * raddoppia il peso dell'attrezzo.
	 * @param attrezzo
	 * @return attrezzo: attrezzo dopo aver subito il comportamento magico
	 */
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		
		attrezzo = new Attrezzo(nomeInvertito.toString(),pesoX2);
		return attrezzo;
	}
	
	
	/**
     * Mette un attrezzo nella stanza.
     * Se il numero di attrezzi nella stanza ha superato la soglia magica
     * applica il comportamento magico e memorizza il nuovo oggetto,
     * altrimenti memorizza l'oggetto che gli è stato passato come parametro.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
	@Override 
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if (this.contatoreAttrezziPosati>this.sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo);
		return super.addAttrezzo(attrezzo);
	}
	
}
