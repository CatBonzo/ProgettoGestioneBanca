package Banca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	//menu
		public static void menu1() {
			System.out.println("------Banca-----------");
			System.out.println("1) Utente non esistente");
			System.out.println("2) Utente gia esistente-Inserire_saldo");
			System.out.println("3) Ritirare saldo");			
			System.out.println("--------------------");
		}
		
	public static void main(String[] args) {
		
		menu1();
		int valoreLetto = 0;
		Scanner input = new Scanner(System.in);
		valoreLetto = input.nextInt();
		
		switch(valoreLetto) {
		
			case 1: // Utente non esistente
					System.out.println("Utente non esistente");
					
				//Creazione utente - iban ,intetsatario
				//Riportare menu
				break;
			case 2: //Utente gia esistente
					System.out.println("Utente gia esistente");
					//Visualizzazioe saldo
					//Inserimento saldo
				break;
			case 3: // Ritirare saldo
					System.out.println("Ritirare saldo");
					//Visualizzazioe saldo
					//Operazione ritiro saldo
				break;
			case 4: 
				break;
		}
		  
	}
}
