package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.StanzaMagica;

public class IOSimulatorTest {

	List<String> input;
	IOSimulator ios;
	IOConsole io;

	@Before
	public void setUp(){
		input=new ArrayList<String>();
		io=new IOConsole();
	}


	@Test
	public void testBilocale() throws FileNotFoundException, FormatoFileNonValidoException {
		Labirinto bilocale = Labirinto.newBuilder(null)
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAttrezzo("letto",10) // dove? fa riferimento all’ultima stanza aggiunta: la “camera”
				.addAdiacenza("salotto", "camera", "nord") // camera si trova a nord di salotto
				.addAdiacenza("camera","salotto","sud").getLabirinto(); // restituisce il Labirinto così specificato

		//DiaDia gioco=new DiaDia(bilocale,new IOConsole());

		Partita p=new Partita(bilocale,io);
		//DiaDia gioco=new DiaDia(bilocale,io);

		input.add("vai sud");
		input.add("prendo osso");
		input.add("guarda");
		input.add("aiuto");
		input.add("vai nord");

		//ios=new IOSimulator(input,gioco);
		ios=new IOSimulator(input,p);
		String messaggio=null;

		for(int i=0;i<input.size();i++){
			messaggio=ios.leggiRiga();
			io.mostraMessaggio(messaggio);
			ios.mostraMessaggio(messaggio);

		}

		//assertEquals(gioco.getPartita().getStanzaCorrente().getNome(),"camera");
		//assertTrue(gioco.getPartita().isFinita());
		assertEquals(p.getStanzaCorrente().getNome(),"camera");
		assertTrue(p.isFinita());

	}



	@Test 
	public void testTrilocale() throws FileNotFoundException, FormatoFileNonValidoException {
		Labirinto trilocale =Labirinto.newBuilder(null)
				.addStanzaIniziale("salotto")
				.addStanza("cucina")
				.addAttrezzo("pentola",1) // dove? fa riferimento all’ultima stanza aggiunta: la “cucina”
				.addStanzaVincente("camera")
				.addAdiacenza("salotto", "cucina", "nord")
				.addAdiacenza("cucina", "camera","est").getLabirinto(); // restituisce il Labirinto così specificato

		//DiaDia gioco=new DiaDia(bilocale,new IOConsole());

		Partita p=new Partita(trilocale,io);
		//DiaDia gioco=new DiaDia(bilocale,io);

		input.add("guarda");
		input.add("prendi osso");
		input.add("gioca");
		input.add("aiuto");
		input.add("vai nord");
		input.add("prendi");
		input.add("prendi gioco");
		input.add("prendi pentola");
		input.add("guarda");
		input.add("vai");
		input.add("vai est");

		//ios=new IOSimulator(input,gioco);
		ios=new IOSimulator(input,p);
		String messaggio=null;

		for(int i=0;i<input.size();i++){
			messaggio=ios.leggiRiga();
			io.mostraMessaggio(messaggio);
			ios.mostraMessaggio(messaggio);

		}

		//assertEquals(gioco.getPartita().getStanzaCorrente().getNome(),"camera");
		//assertTrue(gioco.getPartita().isFinita());
		assertEquals(p.getStanzaCorrente().getNome(),"camera");
		assertTrue(p.isFinita());

	}

	@Test
	public void testAggiuntaAttrezzoAStanze_MoltepliciAttrezziStessaStanza() throws FileNotFoundException, FormatoFileNonValidoException {
		String nomeAttrezzo1 = "attrezzo1";
		String nomeAttrezzo2 = "attrezzo2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		Labirinto l1 =Labirinto.newBuilder(null)
				.addStanzaIniziale(nomeStanza1)
				.addAttrezzo(nomeAttrezzo1, peso1)
				.addAttrezzo(nomeAttrezzo2, peso2).getLabirinto();

		Partita p=new Partita(l1,io);

		input.add("guarda");
		input.add("prendi attrezzo1");
		input.add("guarda");


		ios=new IOSimulator(input,p);
		String messaggio=null;

		for(int i=0;i<input.size();i++){
			messaggio=ios.leggiRiga();
			io.mostraMessaggio(messaggio);
			ios.mostraMessaggio(messaggio);

		}
		assertTrue(p.getGiocatore().getBorsa().hasAttrezzo("attrezzo1"));
		assertEquals(p.getStanzaCorrente().getNumeroAttrezzi(),1);
	}

	@Test
	public void testLabirintoConStanzaMagica() throws FileNotFoundException, FormatoFileNonValidoException {
		String nomeAttrezzo1 = "pianoforte";
		String nomeAttrezzo2 = "flauto";
		int peso1 = 1;
		int peso2 = 2;
		int sogliaMagica = 1;
		String nomeStanzaMagica = "Stanza Magica";

		Labirinto.LabirintoBuilder l2 =Labirinto.newBuilder(null)
				.addStanzaIniziale("entrata")
				.addAttrezzo(nomeAttrezzo1, peso1)
				.addAttrezzo(nomeAttrezzo2, peso2)
				.addStanzaMagica(nomeStanzaMagica, sogliaMagica)
				.addAdiacenza("entrata","Stanza Magica","sud")
				.addAdiacenza("Stanza Magica","entrata","nord");
		StanzaMagica stanzaMagica = (StanzaMagica)l2.getListaStanze().get(nomeStanzaMagica);

		assertTrue(stanzaMagica.isMagica());
		Partita p=new Partita(l2.getLabirinto(),io);

		input.add("guarda");
		input.add("prendi pianoforte");
		input.add("prendi flauto");
		input.add("guarda");
		input.add("vai sud");
		input.add("posa pianoforte");
		input.add("posa flauto"); //si manifesta il comportamento magico
		input.add("guarda");

		ios=new IOSimulator(input,p);
		String messaggio=null;

		for(int i=0;i<input.size();i++){
			messaggio=ios.leggiRiga();
			io.mostraMessaggio(messaggio);
			ios.mostraMessaggio(messaggio);

		}
		assertEquals(p.getStanzaCorrente().getNumeroAttrezzi(),2);
		assertTrue(p.getStanzaCorrente().hasAttrezzo("otualf")); //nome invertito
		assertEquals(p.getStanzaCorrente().getAttrezzo("otualf").getPeso(),4); //peso raddoppiato
	}
	@Test
	public void testLabirintoConStanzaBloccata_ConPassepartout() throws FormatoFileNonValidoException, FileNotFoundException {
		Labirinto l3 = Labirinto.newBuilder(null)
				.addStanzaIniziale("inizio")
				.addStanzaBloccata("stanza bloccata", "nord", "chiave")
				.addAttrezzo("chiave", 1)
				.addAdiacenza("inizio", "stanza bloccata", "nord")
				.addAdiacenza("stanza bloccata", "inizio", "sud")
				.addStanzaVincente("fine")
				.addAdiacenza("stanza bloccata", "fine", "nord")
				.addAdiacenza("fine", "stanza bloccata", "sud").getLabirinto();

		Partita p=new Partita(l3,io);

		input.add("guarda");
		input.add("vai nord");
		assertFalse(p.getStanzaCorrente().isMagica());
		input.add("guarda");
		input.add("vai nord");
		input.add("guarda");

		ios=new IOSimulator(input,p);
		String messaggio=null;

		for(int i=0;i<input.size();i++){
			messaggio=ios.leggiRiga();
			io.mostraMessaggio(messaggio);
			ios.mostraMessaggio(messaggio);	
		}
		assertTrue(p.isFinita());
	}

	@Test
	public void testLabirintoConStanzaBloccata_SenzaPassepartout() throws FileNotFoundException, FormatoFileNonValidoException {
		Labirinto l4 = Labirinto.newBuilder(null)
				.addStanzaIniziale("inizio")
				.addStanzaBloccata("stanza bloccata", "nord", "chiave")
				.addAdiacenza("inizio", "stanza bloccata", "nord")
				.addAdiacenza("stanza bloccata","inizio", "sud")
				.addStanzaVincente("fine")
				.addAdiacenza("stanza bloccata", "fine", "nord")
				.addAdiacenza("fine", "stanza bloccata", "sud").getLabirinto();

		Partita p=new Partita(l4,io);

		input.add("guarda");
		input.add("vai nord");
		input.add("guarda");
		input.add("vai nord"); //non avanza perchè non possiede l'oggetto sbloccante
		input.add("guarda");

		ios=new IOSimulator(input,p);
		String messaggio=null;

		for(int i=0;i<input.size();i++){
			messaggio=ios.leggiRiga();
			io.mostraMessaggio(messaggio);
			ios.mostraMessaggio(messaggio);	
		}
		assertFalse(p.getStanzaCorrente().isMagica());
		assertFalse(p.isFinita());
		assertEquals(p.getStanzaCorrente().getNome(), "stanza bloccata");
		assertFalse(p.getStanzaCorrente().hasAttrezzo("chiave"));

	}
	@Test
	public void testLabirintoCompletoConTutteLeStanze() throws FileNotFoundException, FormatoFileNonValidoException {

		Labirinto labirintoCompleto = Labirinto.newBuilder(null)
				.addStanzaIniziale("inizio")
				.addStanzaVincente("fine")
				.addStanza("corridoio")
				.addAttrezzo("chiave", 1)
				.addAttrezzo("lanterna", 1)
				.addStanzaBloccata("corridoio bloccato","nord","chiave")
				.addStanzaMagica("stanza magica", 1)
				.addStanzaBuia("stanza buia","lanterna")
				.addStanza("Aula 1")
				.addAdiacenza("inizio", "corridoio", "nord")
				.addAdiacenza("corridoio", "inizio", "sud")
				.addAdiacenza("corridoio", "corridoio bloccato", "nord")
				.addAdiacenza("corridoio bloccato", "corridoio", "sud")
				.addAdiacenza("corridoio bloccato", "Aula 1", "nord")
				.addAdiacenza("Aula 1", "corridoio bloccato", "sud")
				.addAdiacenza("Aula 1", "fine","nord")
				.addAdiacenza("fine", "Aula 1", "sud")
				.addAdiacenza("corridoio", "stanza magica", "est")
				.addAdiacenza("stanza magica", "corridoio", "ovest")
				.addAdiacenza("corridoio", "stanza buia", "ovest")
				.addAdiacenza("stanza buia", "corridoio", "est").getLabirinto();

		Partita p=new Partita(labirintoCompleto ,io);

		input.add("guarda");
		input.add("vai nord"); //in corridoio
		input.add("prendi chiave"); //prende l'oggetto in grado di sbloccare corridoio bloccato
		input.add("prendi lanterna");
		input.add("guarda");
		input.add("vai est"); //in stanza magica
		input.add("posa lanterna"); //ancora non si manifesta il comportameento magico (soglia:1)
		input.add("vai ovest"); //torna in corridoio
		input.add("vai nord"); //corridoio bloccato
		input.add("posa chiave"); //posa l'oggetto in grado di sbloccare la stanza corrente
		input.add("guarda");
		input.add("vai nord");//in Aula 1
		input.add("vai  nord");//Vinto!


		ios=new IOSimulator(input,p);
		String messaggio=null;

		for(int i=0;i<input.size();i++){
			messaggio=ios.leggiRiga();
			io.mostraMessaggio(messaggio);
			ios.mostraMessaggio(messaggio);	
		}
		assertTrue(p.giocatoreIsVivo());
		assertTrue(p.isFinita());

	}


}