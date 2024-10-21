package it.its.pw.bank.service;

import it.its.pw.bank.model.Conto;
import java.util.HashMap;
import java.util.UUID;

public class Banca {

    // Variabili
    private final HashMap<String, Conto> conti;

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

    // Metodo per generare un IBAN univoco
    public String generaIban() {
        return "IBAN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // Metodo per aggiungere un conto con IBAN generato automaticamente
    public void creazioneConto(String intestatario) {
        String iban = generaIban();
        Conto conto = new Conto(intestatario, iban);
        conti.put(iban, conto);
        System.out.println("Conto creato con IBAN: " + iban);
    }

    public Conto getConto(String iban) {
        return conti.get(iban);
    }

    // Nuovo metodo per ottenere l'IBAN di un intestatario
    public String getIban(String intestatario) {
        for (Conto conto : conti.values()) {
            if (conto.getIntestatario().equalsIgnoreCase(intestatario)) {
                return conto.getContoCorrente(); // Restituisce l'IBAN associato all'intestatario
            }
        }
        return null; // Restituisce null se non trovato
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

    // Metodo per eliminare un conto
    public void eliminaConto(String iban) {
        Conto conto = conti.remove(iban);
        if (conto != null) {
            System.out.println("Conto chiuso: " + conto.getContoCorrente());
        } else {
            System.out.println("Nessun conto trovato con questo IBAN");
        }
    }

    // Metodo per trasferire denaro tra conti
    public void trasferimentoConto(String ibanIn, String ibanDest, double importo) {
        Conto contoIn = conti.get(ibanIn);
        Conto contoDest = conti.get(ibanDest);

        // Controlla se i conti esistono
        if (contoIn == null || contoDest == null) {
            System.out.println("Uno o entrambi i conti non esistono.");
            return;
        }

        // Prelevare l'importo dal conto di origine
        double importoPrelevato = contoIn.prelievo(importo);

        // Se il prelievo è andato a buon fine
        if (importoPrelevato > 0) {
            contoDest.deposita(importoPrelevato);
            System.out.println("Trasferimento di " + importoPrelevato + " effettuato con successo");
        } else {
            System.out.println("Fondi insufficienti");
        }
    }
}