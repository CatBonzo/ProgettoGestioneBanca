package Banca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {
	
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
		
		Banca b1 = new Banca();
	
		
		b1.AggiungiUtente("Mario", "IBAN-kishjdbfwkj",89);
		b1.AggiungiUtente("Luigi", "IBAN-6854hbdf",100);
		b1.AggiungiUtente("Rosa", "IBAN-55f5drfgre5",50);
		
		int valoreLetto = 0;
		Scanner input = new Scanner(System.in);
		valoreLetto = input.nextInt();
		
		switch(valoreLetto) {
		
			case 1: // Utente non esistente
					System.out.println("---Utente non esistente--");
					System.out.println("");
					System.out.println("Nome: ");
					
					String intestatario = "";
					Scanner input1 = new Scanner(System.in);
					intestatario = input1.nextLine();
					
					System.out.println("");
					System.out.println("Iban: ");
					String contoCorrente = "";
					Scanner input2 = new Scanner(System.in);
					contoCorrente = input2.nextLine();
					
					b1.AggiungiUtente(intestatario, contoCorrente);
					b1.stampaConti();
					
					
				//Creazione utente - iban ,intetsatario
				//Riportare menu
				break;
			case 2: //Utente gia esistente
					System.out.println("Utente gia esistente");
					
					System.out.println("");
					System.out.println("Intestatario: ");
					
					String intestatario2 = "";
					Scanner input3 = new Scanner(System.in);
					intestatario2 = input3.nextLine();
					
					System.out.println("");
					System.out.println("Deposita: ");
					double deposito = 0;
					Scanner input4 = new Scanner(System.in);
					deposito = input4.nextFloat();
					
					b1.DepositaSaldo(intestatario2, deposito);
					b1.stampaConti();
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
