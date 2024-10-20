package it.its.pw.banca.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.its.pw.banca.model.Conto;
import it.its.pw.banca.repository.ContoRepository;

@Service
public class ContoService {

    @Autowired
    private ContoRepository contoRepository;

    public Conto creaConto(String intestatario) {
        String iban = generaIBAN();
        Conto conto = new Conto(intestatario);
        conto.setContoCorrente(iban);
        return contoRepository.save(conto);
    }

    public List<Conto> getAllConti() {
        return contoRepository.findAll();
    }

    public Optional<Conto> getContoById(Long id) {
        return contoRepository.findById(id);
    }

    public void deposita(Long id, double importo) {
        Optional<Conto> conto = contoRepository.findById(id);
        if (conto.isPresent()) {
            Conto c = conto.get();
            c.deposita(importo);
            contoRepository.save(c); 
        }
    }

    public void prelievo(Long id, double importo) {
        Optional<Conto> conto = contoRepository.findById(id);
        if (conto.isPresent()) {
            Conto c = conto.get();
            c.prelievo(importo);
            contoRepository.save(c); 
        }
    }

    // Metodo per generare un IBAN fittizio
    private String generaIBAN() {
        Random random = new Random();
        String codicePaese = "IT";
        String codiceControllo = "60"; // fisso per semplicità
        String codiceBanca = String.format("%05d", random.nextInt(99999)); // Codice banca (5 cifre)
        String codiceFiliale = String.format("%05d", random.nextInt(99999)); // Codice filiale (5 cifre)
        String numeroConto = String.format("%012d", random.nextInt(999999999)); // Numero conto (12 cifre)

        return codicePaese + codiceControllo + codiceBanca + codiceFiliale + numeroConto;
    }
}