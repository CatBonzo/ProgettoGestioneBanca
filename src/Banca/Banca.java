package Banca;

import java.util.ArrayList;

public class Banca {
	
	//Variabili
	private ArrayList<Conto> conti;
	
	public Banca () {
		this.conti = new ArrayList<>();
	}
	
	public boolean verificaUtenti(String intestatario) {
		for (Conto conto : conti) { // conto oggetto ||conti Lista
            // Controlla se l'intestatario o l'IBAN corrispondono
            if (conto.getIntestatario().equalsIgnoreCase(intestatario) ) {
                return true; // Se trova Utente
            }
        }
        return false; // Se non trova Utente
	}
	
	public void AggiungiUtente(String intestatario,String iban) {
		if(verificaUtenti(intestatario)) {
			System.out.println("Utente gia esistente");
		}
		else {
			Conto conto = new Conto(intestatario,iban);
			conti.add(conto);
		}
		
	}
	
	public void AggiungiUtente(String intestatario,String iban,double saldo) {
			Conto conto = new Conto(intestatario,iban,saldo);
			conti.add(conto);
		
	}
	
	public void stampaConti() {
		for (Conto conto : conti) { 
            System.out.println(conto);
        }
	}
	
	public void DepositaSaldo(String intestatario,double denaroIn) {
		for (Conto conto : conti) { // conto oggetto ||conti Lista
			if(verificaUtenti(intestatario)) {
				
				conto.setSaldo() = conto.getSaldo() + denaroIn;
			}
        }
	}
	public void RitiraiSaldo() {
		
	}
	
	
}
