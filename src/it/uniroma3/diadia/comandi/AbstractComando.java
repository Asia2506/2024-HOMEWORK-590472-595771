package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando{
	
	abstract public void esegui(Partita partita);
	

	public void setParametro(String parametro) {}


	
	public String getParametro() {
		return null;
	}
	
	
	public String getNome() {
		return this.getClass().getName();
	}
	
}
