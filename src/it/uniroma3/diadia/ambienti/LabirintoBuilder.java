package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * LabirintoBuilder: Classe dedicata esclusivamente
 * alla creazione di oggetti Labirinto utilizzando una
 * tecnica (method-chaining) che faciliti la costruzione
 * incrementale di un oggetto il cui stato è composito
 * 
 * @see Attrezzo
 * @author Asia, Isabella
 */


public class LabirintoBuilder extends Labirinto{
	
	private List<Stanza> stanze;
	private Set<Attrezzo> attrezzi; 
	
	public LabirintoBuilder() {
		this.stanze=new ArrayList<Stanza>();
		this.attrezzi=new HashSet<Attrezzo>();
	}
	
	
	
	public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
		Stanza s=new Stanza(nomeStanza);
	
		if(!this.stanze.contains(s)) {
			//se la stanza non è presente la inserisco
			this.stanze.add(s);
			this.setStanzaIniziale(s);
		}else
			this.setStanzaIniziale(this.stanze.get(this.stanze.indexOf(s)));
			
		return this;
	}
	
	
	public LabirintoBuilder addStanzaVincente(String nomeStanza) {
		Stanza s=new Stanza(nomeStanza);
		
		if(!this.stanze.contains(s)) {
			//se la stanza non è presente la inserisco
			this.stanze.add(s);
			this.setStanzaVincente(s);
		}else	
			this.setStanzaVincente(this.stanze.get(this.stanze.indexOf(s)));
			
		return this;
		/*this.setStanzaVincente(new Stanza(nomeStanza));
		this.stanze.add(this.getStanzaVincente());
		return this;*/
	}
	
	
	public LabirintoBuilder addStanza(String nomeStanza) {
		Stanza s=new Stanza(nomeStanza);
		if(!this.stanze.contains(s))
			this.stanze.add(s);
		return this;
	}
	
	
	public LabirintoBuilder addAdiacenza(String nomeStanzaCorrente,String nomeStanzaAdiacente,String direzione) {
		Stanza stanzaCorrente=new Stanza(nomeStanzaCorrente);
		Stanza stanzaAdiacente=new Stanza(nomeStanzaAdiacente);
		
		if(!this.stanze.contains(stanzaCorrente)) 
			//se la stanza non esiste
			this.stanze.add(stanzaCorrente);
		else
			stanzaCorrente=this.stanze.get(this.stanze.indexOf(stanzaCorrente));
		
		if(!this.stanze.contains(stanzaAdiacente)) 
			//se la stanza non esiste
			this.stanze.add(stanzaAdiacente);
		else
			stanzaAdiacente=this.stanze.get(this.stanze.indexOf(stanzaAdiacente));
			
		stanzaCorrente.impostaStanzaAdiacente(direzione, stanzaAdiacente);
	
		return this;
	}
	
	
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso){
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		if(this.attrezzi.add(attrezzo))
			this.stanze.get(this.stanze.size()-1).addAttrezzo(attrezzo);
			//if(this.stanze.iterator().hasNext())
				//this.stanze.iterator().next().addAttrezzo(attrezzo);
		return this;
	}
	 
	
	
	public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica,int sogliaMagica) {
		this.stanze.add(new StanzaMagica(nomeStanzaMagica,sogliaMagica));
		return this;
	}
	
	
	public LabirintoBuilder addStanzaBloccata(String nomeStanzaBloccata,String nomeDirezioneBloccata,String nomeAttrezzoSbloccante) {
		this.stanze.add(new StanzaBloccata(nomeStanzaBloccata,nomeDirezioneBloccata,nomeAttrezzoSbloccante));
		return this;
	}
	
	
	public LabirintoBuilder addStanzaBuia(String nomeStanzaBuia,String nomeAttrezzo) {
		this.stanze.add(new StanzaBuia(nomeStanzaBuia,nomeAttrezzo));
		return this;
	}
	
	
	public Labirinto getLabirinto() {
		return this;
	}
	
	
	public Map<String,Stanza> getListaStanze(){
		Map<String,Stanza> mappa=new HashMap<String,Stanza>();
		
		Iterator<Stanza> iter=this.stanze.iterator();
		Stanza stanza=null;
		
		while(iter.hasNext()) {
			stanza=iter.next();
			mappa.put(stanza.getNome(), stanza);
		}
		
		return mappa;
	}
	
	
	
}
