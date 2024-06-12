package it.uniroma3.diadia;

import java.util.Scanner;


public class IOConsole implements IO{
	
	Scanner scannerDiLinee;
	
	public IOConsole(Scanner scannerDiParole) {
		this.scannerDiLinee=scannerDiParole;
	}
	
	public IOConsole() {
		this.scannerDiLinee=new Scanner(System.in);
	}
	
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	public String leggiRiga() {
		String riga = scannerDiLinee.nextLine();
		//scannerDiLinee.close();
		return riga;
	}
}