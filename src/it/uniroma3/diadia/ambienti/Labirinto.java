package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggio.Cane;
import it.uniroma3.diadia.personaggio.Mago;
import it.uniroma3.diadia.personaggio.Strega;

/**
 * Classe Labirinto - ha la responsabilità di creare il labirinto, 
 * di memorizzare la stanza iniziale (entrata) e quella finale (uscita)
 * 
 * @see Attrezzo
 */

public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	private Labirinto(String nomeFile) throws FormatoFileNonValidoException, FileNotFoundException {
		CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
		c.carica();
		stanzaIniziale = c.getStanzaIniziale();
		stanzaVincente = c.getStanzaVincente();
	}

	private Labirinto() {}

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	


	public static LabirintoBuilder newBuilder(String nomeFileLabirinto) throws FileNotFoundException, FormatoFileNonValidoException {
		return new LabirintoBuilder(nomeFileLabirinto);
	}

	
	
	
	
	public static class LabirintoBuilder{

		private List<Stanza> stanze;
		private Set<Attrezzo> attrezzi; 
		private Labirinto labirinto;

		public LabirintoBuilder(String nomeFileLabirinto) throws FileNotFoundException, FormatoFileNonValidoException{
			try{
				labirinto=new Labirinto(nomeFileLabirinto);
			}catch (Exception e) {
				labirinto=new Labirinto();
			}finally {
				this.stanze=new ArrayList<Stanza>();
				this.attrezzi=new HashSet<Attrezzo>();
			}
		}
		

		public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
			Stanza s=new Stanza(nomeStanza);

			if(!this.stanze.contains(s)) {
				//se la stanza non è presente la inserisco
				this.stanze.add(s);
				this.labirinto.stanzaIniziale=s;
			}else
				this.labirinto.stanzaIniziale=this.stanze.get(this.stanze.indexOf(s));

			return this;
		}


		public LabirintoBuilder addStanzaVincente(String nomeStanza) {
			Stanza s=new Stanza(nomeStanza);

			if(!this.stanze.contains(s)) {
				//se la stanza non è presente la inserisco
				this.stanze.add(s);
				this.labirinto.stanzaVincente=s;
			}else	
				this.labirinto.stanzaVincente=this.stanze.get(this.stanze.indexOf(s));

			return this;
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
		
		public LabirintoBuilder  addMago(String nome, String presentazione, Attrezzo attrezzo) {
			Mago m = new Mago(nome, presentazione, attrezzo);
			if(this.stanze.get(this.stanze.size()-1)==null)
				return this;
			this.stanze.get(this.stanze.size()-1).setPersonaggio(m);
			return this;
		}

		public LabirintoBuilder  addCane(String nome, String presentazione) {
			Cane c = new Cane(nome, presentazione);
			if(this.stanze.get(this.stanze.size()-1)==null)
				return this;
			this.stanze.get(this.stanze.size()-1).setPersonaggio(c);
			return this;
		}

		public LabirintoBuilder  addStrega(String nome, String presentazione) {
			Strega s = new Strega(nome, presentazione);
			if(this.stanze.get(this.stanze.size()-1)==null)
				return this;
			this.stanze.get(this.stanze.size()-1).setPersonaggio(s);
			return this;
		}
		


		public Labirinto getLabirinto() {
			return this.labirinto;
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



		public Set<Attrezzo> getAttrezzi() {
			return attrezzi;
		}

	}


}
