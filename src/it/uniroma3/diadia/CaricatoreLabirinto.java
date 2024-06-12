package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggio.Cane;
import it.uniroma3.diadia.personaggio.Mago;
import it.uniroma3.diadia.personaggio.Strega;
import it.uniroma3.diadia.personaggio.AbstractPersonaggio;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";
	
	/* prefisso di una singola riga di testo contenente i nomi delle stanze buie */
	private static final String STANZE_BUIE_MARKER = "Buie:";    

	/* prefisso di una singola riga di testo contenente i nomi delle stanze bloccate */
	private static final String STANZE_BLOCCATE_MARKER = "Bloccate:";

	/* prefisso di una singola riga di testo contenente i nomi delle stanze magiche */
	private static final String STANZE_MAGICHE_MARKER = "Magiche:";
	
	/* prefisso di una singola riga di testo contenente i nomi dei cani */
	private static final String PERSONAGGI_MARKER_CANE = "Cani:";
	
	/* prefisso di una singola riga di testo contenente i nomi dei maghi */
	private static final String PERSONAGGI_MARKER_MAGO = "Maghi:";
	
	/* prefisso di una singola riga di testo contenente i nomi delle streghe */
	private static final String PERSONAGGI_MARKER_STREGA = "Streghe:";
	
	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	
	private BufferedReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	
	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}
	
	

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzeBuie();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiECreaMaghi();
			this.leggiECreaStreghe();
			this.leggiECreaCani();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	
	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza,stanza);
		}
	}
	
	
	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String specificheStanzeBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);

		for(String  specificaStanzaBloccata : separaStringheAlleVirgole(specificheStanzeBloccate)) {
			try (Scanner scannerLinea = new Scanner(specificaStanzaBloccata)) {
				while(scannerLinea.hasNext()) {
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza bloccata."));
					String nomeStanzaBloccata = scannerLinea.next();
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione della stanza bloccata "+nomeStanzaBloccata +"."));
					String nomeDirezioneBloccata = scannerLinea.next();
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo con cui sbloccare la direzione dell stanza"+ nomeDirezioneBloccata+"."));
					String nomeAttrezzoSbloccante = scannerLinea.next();
					
					StanzaBloccata stanzaBloccata = new StanzaBloccata(nomeStanzaBloccata,nomeDirezioneBloccata,nomeAttrezzoSbloccante);
					nome2stanza.put(nomeStanzaBloccata, stanzaBloccata);
				}
			}
		}
	}
	
	
	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new StanzaMagica(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}
	
	
	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String specificheStanzeBuie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);

		for(String  specificaStanzaBuia : separaStringheAlleVirgole(specificheStanzeBuie)) {
			
			try (Scanner scannerLinea = new Scanner(specificaStanzaBuia)) {
				while(scannerLinea.hasNext()) {
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una stanza buia."));
					String nomeStanzaBuia = scannerLinea.next();
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione della stanza buia "+nomeStanzaBuia +"."));
					String nomeAttrezzo = scannerLinea.next();
					System.out.println("Attrezzo per vedere: "+nomeAttrezzo);
					StanzaBuia stanzaBuia = new StanzaBuia(nomeStanzaBuia,nomeAttrezzo);
					nome2stanza.put(nomeStanzaBuia, stanzaBuia);
				}
			}	
		}
	}

	
	
	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext())
				result.add(scannerDiParole.next());
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
		
	}

	
	
	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				while(scannerLinea.hasNext()) {
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
					nomeAttrezzo = scannerLinea.next();
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
					pesoAttrezzo = scannerLinea.next();
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
					nomeStanza = scannerLinea.next();
				}
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}
	
	
	
	private void leggiECreaMaghi() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_MAGO);
		for(String specifica : separaStringheAlleVirgole(specificheStanze)) {
			
			try (Scanner scannerDiLinea = new Scanner(specifica)) 	{	
				while (scannerDiLinea.hasNext()) {
					
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  stanza "+ specifica+"per aggiungere il mago non esiste\n"));
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("problemini nella creazione del mago ...\n"));
					String mago = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("specifica la presentazione del mago\n"));
					String presentazione = scannerDiLinea.next();					
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("vi è stato qualche problema nella creazione dell'attrezzo per il mago della stanza "+specifica+"\n"));
					String attrezzo = scannerDiLinea.next();

					AbstractPersonaggio personaggio = new Mago(mago, presentazione, new Attrezzo(attrezzo, 4));
					this.nome2stanza.get(nomeStanza).setPersonaggio(personaggio);
				}
			}
		} 
	}
	
	
	
	private void leggiECreaStreghe() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_STREGA);
		for(String specifica : separaStringheAlleVirgole(specificheStanze)) {
			
			try (Scanner scannerDiLinea = new Scanner(specifica)) 	{	
				while (scannerDiLinea.hasNext()) {
					
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  stanza "+ specifica+"per aggiungere la strega non esiste\n"));
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("problemini nella creazione della strega ...\n"));
					String strega = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("specifica la presentazione della strega\n"));
					String presentazione = scannerDiLinea.next();					

					AbstractPersonaggio personaggio = new Strega(strega, presentazione);
					this.nome2stanza.get(nomeStanza).setPersonaggio(personaggio);
				}
			}
		} 
	}
	
	
	
	private void leggiECreaCani() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_CANE);
		for(String specifica : separaStringheAlleVirgole(specificheStanze)) {
			
			try (Scanner scannerDiLinea = new Scanner(specifica)) 	{	
				while (scannerDiLinea.hasNext()) {
					
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  stanza "+ specifica+"per aggiungere il cane non esiste\n"));
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("problemini nella creazione del cane ...\n"));
					String cane = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("specifica la presentazione del cane\n"));
					String presentazione = scannerDiLinea.next();					


					AbstractPersonaggio personaggio = new Cane(cane, presentazione);
					this.nome2stanza.get(nomeStanza).setPersonaggio(personaggio);
				}
			}
		} 
	}

	
	
	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	
	
	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specificaUscite : separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(specificaUscite)) {	
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();
					//System.out.println("Partenza: "+stanzaPartenza);
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();
					//System.out.println("Destinezione: "+stanzaDestinazione);
					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				
				}
			}
		}
	}
	
	
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	
	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}


	
	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido ["+((LineNumberReader) this.reader).getLineNumber() + "] "+messaggioErrore);
	}

	
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	
	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
	

	
	
	
	
	
	
}