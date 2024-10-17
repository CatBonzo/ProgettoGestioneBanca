package it.its.pw.banca;

import java.util.HashMap;
import java.util.UUID;

public class Banca {
    
    // Variabili
    private HashMap<String, Conto> conti;
    
    public Banca() {
        this.conti = new HashMap<>();
 
    }
    
    // Metodo per verificare se un utente esiste utilizzando l'intestatario
    public boolean verificaUtenti(String intestatario) {
        for (Conto conto : conti.values()) {
            if (conto.getIntestatario().equalsIgnoreCase(intestatario)) {
                return true; // Se trova l'utente
            }
        }
        return false; // Se non trova l'utente
    }
    
  public String generaIban() {
        // Genera un IBAN univoco usando UUID
        return "IBAN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
 // Metodo per aggiungere un utente, generando un IBAN automaticamente
    public void creazioneConto(String intestatario) {
       
            String iban = generaIban();
            Conto conto = new Conto(intestatario, iban);
            conti.put(iban, conto);
            
        
    }
    
    public Conto getConto(String iban) {
        return conti.get(iban);
    }
    
    // Metodo per stampare tutti i conti
    public void stampaConti() {
        for (Conto conto : conti.values()) {
            System.out.println(conto);
        }
    }
    
    // Metodo per visualizzare il saldo di un conto tramite l'IBAN
    public void visualizzazioneSaldo(String iban) {
        Conto conto = conti.get(iban);
        if (conto != null) {
            System.out.println("------------");
            System.out.println("Saldo: " + conto.getSaldo());
            System.out.println("------------");
        } else {
            System.out.println("Nessun conto trovato con questo IBAN");
        }
    }

	public void eliminaConto(String iban) {
		Conto conto = conti.get(iban);
		if (conto != null) {
            conti.remove(iban, conto);
            System.out.println("Conto chiuso");
        } else {
            System.out.println("Nessun conto trovato con questo IBAN");
        }
	}
	
	public void trasferimentoConto(String ibanIn, String ibanDest, double importo) {
	    Conto contoIn = conti.get(ibanIn);
	    Conto contoDest = conti.get(ibanDest);

	    
	    // Prelevare l'importo dal conto di origine
	    double importoPrelevato = contoIn.prelievo(importo);

	    // Se il prelievo è andato a buon fine
	    if (importoPrelevato > 0) {
	        contoDest.deposita(importoPrelevato);
	        System.out.println("Trasferimento effettuato con successo");
	    } else {
	        System.out.println("Fondi insufficienti");
	    }
	}
}
