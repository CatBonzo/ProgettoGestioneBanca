package it.its.pw.banca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Conto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contoCorrente;
    private String intestatario;
    private double saldo;

    public Conto() {
    }

    public Conto(String intestatario) {
        this.intestatario = intestatario;
        this.saldo = 0;
    }

    public Long getId() {
        return id;
    }

    public String getContoCorrente() {
        return contoCorrente;
    }

    public void setContoCorrente(String contoCorrente) {
        this.contoCorrente = contoCorrente;
    }

    public String getIntestatario() {
        return intestatario;
    }

    public void setIntestatario(String intestatario) {
        this.intestatario = intestatario;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double deposita(double importo) {
        if (importo < 0) {
            throw new IllegalArgumentException("Non puoi aggiungere denaro negativo");
        }
        this.saldo += importo; // Aggiorna il saldo
        return this.saldo; // Restituisce il nuovo saldo
    }

    public double prelievo(double importo) {
        if (importo <= 0) {
            throw new IllegalArgumentException("L'importo da ritirare deve essere positivo");
        } else if (this.saldo < importo) {
            throw new IllegalArgumentException("Saldo insufficiente");
        }
        this.saldo -= importo; // Sottrae l'importo dal saldo
        return this.saldo; // Restituisce il nuovo saldo
    }

    @Override
    public String toString() {
        return "Conto [intestatario=" + intestatario + ", contoCorrente=" + contoCorrente + ", saldo=" + saldo + "]";
    }
}