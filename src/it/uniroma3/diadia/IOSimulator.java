package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

public class IOSimulator implements IO{
	
	private List<String> input;
	private int inputLetti;
	private Partita partita;

	public IOSimulator(List<String> input,Partita partita) {
		this.partita=partita;
		this.input=new ArrayList<String>(input);
		this.inputLetti=0;
	}
	
	
	public void mostraMessaggio(String messaggio) {
		FabbricaDiComandiRiflessiva fabbricaComando=new FabbricaDiComandiRiflessiva();
		Comando comando=fabbricaComando.costruisciComando(messaggio);
		comando.esegui(this.partita);
	}

	
	public String leggiRiga() {
		String messaggio=null;
		
		if(this.inputLetti<input.size()) {
			messaggio=this.input.get(inputLetti);
			this.inputLetti++;
		}
			
		return messaggio;
	}
	
}
