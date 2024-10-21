package it.its.pw.bank.service;

import it.its.pw.bank.controller.ConnessioneDb;
import it.its.pw.bank.model.Conto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; // Assicurati che il pacchetto sia corretto
import java.util.UUID; // Assicurati che il pacchetto sia corretto

public class ContoService {

    // Metodo per generare un IBAN univoco
    public String generaIban() {
        return "IT" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // Metodo per creare un nuovo conto nel database
    public String creaConto(String intestatario) {
        String iban = generaIban(); // Utilizza il metodo per generare un IBAN
        String sql = "INSERT INTO Conto (iban, intestatario, saldo) VALUES (?, ?, ?)";
        try (Connection con = ConnessioneDb.connectDatabase();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, iban);
            ps.setString(2, intestatario);
            ps.setDouble(3, 0.0); // Imposta saldo iniziale a 0
            ps.executeUpdate();
            return iban; // Restituisce l'IBAN creato
        } catch (SQLException e) {
            System.out.println("Errore nella creazione del conto: " + e.getMessage());
            return null; // Restituisce null in caso di errore
        }
    }

    // Metodo per ottenere un conto tramite IBAN
    public Conto getContoByIban(String iban) {
        String sql = "SELECT * FROM Conto WHERE iban = ?";
        try (Connection con = ConnessioneDb.connectDatabase();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, iban);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String intestatario = rs.getString("intestatario");
                double saldo = rs.getDouble("saldo");
                Conto conto = new Conto(intestatario, iban);
                conto.setSaldo(saldo);
                return conto;
            }
        } catch (SQLException e) {
            System.out.println("Errore nel recupero del conto: " + e.getMessage());
        }
        return null; // Restituisce null se non trovato
    }

// Metodo per depositare denaro su un conto
public boolean deposita(String iban, double importo) {
    if (importo <= 0) {
        System.out.println("L'importo da depositare deve essere positivo.");
        return false; 
    }
    Conto conto = getContoByIban(iban);
    if (conto != null) {
        double nuovoSaldo = conto.deposita(importo);
        return aggiornaSaldo(iban, nuovoSaldo);
    }
    return false; 
}

// Metodo per prelevare denaro da un conto
public boolean prelievo(String iban, double importo) {
    if (importo <= 0) {
        System.out.println("L'importo da prelevare deve essere positivo.");
        return false; 
    }
    Conto conto = getContoByIban(iban);
    if (conto != null) {
        if (conto.getSaldo() >= importo) {
            double nuovoSaldo = conto.prelievo(importo);
            return aggiornaSaldo(iban, nuovoSaldo);
        } else {
            System.out.println("Saldo insufficiente per il prelievo.");
        }
    }
    return false; 
}
    // Metodo per aggiornare il saldo nel database
    private boolean aggiornaSaldo(String iban, double nuovoSaldo) {
        String sql = "UPDATE Conto SET saldo = ? WHERE iban = ?";
        try (Connection con = ConnessioneDb.connectDatabase();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, nuovoSaldo);
            ps.setString(2, iban);
            ps.executeUpdate();
            return true; // Restituisce true se aggiornamento riuscito
        } catch (SQLException e) {
            System.out.println("Errore nell'aggiornamento del saldo: " + e.getMessage());
            return false; // Restituisce false in caso di errore
        }
    }

    // Metodo per trasferire denaro da un conto a un altro
    public boolean trasferimento(String ibanIn, String ibanDest, double importo) {
        Conto contoIn = getContoByIban(ibanIn);
        Conto contoDest = getContoByIban(ibanDest);
        if (contoIn != null && contoDest != null && contoIn.getSaldo() >= importo) {
            prelievo(ibanIn, importo);
            deposita(ibanDest, importo);
            System.out.println("Trasferimento di " + importo + " completato.");
            return true; // Restituisce true se trasferimento riuscito
        } else {
            System.out.println("Trasferimento non riuscito. Controlla i saldi e gli IBAN.");
            return false; // Restituisce false se non riuscito
        }
    }

    // Metodo per eliminare un conto
    public boolean eliminaConto(String iban) {
        String sql = "DELETE FROM Conto WHERE iban = ?";
        try (Connection con = ConnessioneDb.connectDatabase();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, iban);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Restituisce true se conto eliminato
        } catch (SQLException e) {
            System.out.println("Errore nell'eliminazione del conto: " + e.getMessage());
            return false; // Restituisce false in caso di errore
        }
    }

    // Metodo per ottenere tutti i conti
    public List<Conto> getAllConti() {
        List<Conto> conti = new ArrayList<>();
        String sql = "SELECT * FROM Conto";
        try (Connection con = ConnessioneDb.connectDatabase();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String iban = rs.getString("iban");
                String intestatario = rs.getString("intestatario");
                double saldo = rs.getDouble("saldo");
                Conto conto = new Conto(intestatario, iban);
                conto.setSaldo(saldo);
                conti.add(conto);
            }
        } catch (SQLException e) {
            System.out.println("Errore nel recupero dei conti: " + e.getMessage());
        }
        return conti; // Restituisce la lista di conti
    }

    // Metodo per visualizzare il saldo di un conto
    public void visualizzaSaldo(String iban) {
        Conto conto = getContoByIban(iban);
        if (conto != null) {
            System.out.println("Saldo del conto " + iban + ": " + conto.getSaldo());
        } else {
            System.out.println("Nessun conto trovato con questo IBAN.");
        }
    }

    // Metodo per ottenere i conti di un intestatario
    public List<Conto> getContiByIntestatario(String intestatario) {
        List<Conto> listaConti = new ArrayList<>();
        String sql = "SELECT * FROM Conto WHERE intestatario = ?";
        try (Connection con = ConnessioneDb.connectDatabase();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, intestatario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String iban = rs.getString("iban");
                double saldo = rs.getDouble("saldo");
                Conto conto = new Conto(intestatario, iban);
                conto.setSaldo(saldo);
                listaConti.add(conto);
            }
        } catch (SQLException e) {
            System.out.println("Errore nel recupero dei conti: " + e.getMessage());
        }
        return listaConti; // Restituisce la lista di conti
    }
}