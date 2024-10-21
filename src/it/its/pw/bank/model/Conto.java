package it.its.pw.bank.model;

public class Conto {

    private String contoCorrente;
    private String intestatario; 
    private double saldo;

    public Conto(String intestatario, String iban) {
        this.intestatario = intestatario;
        this.contoCorrente = iban;
        this.saldo = 0; 
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
        if (importo <= 0) {
            System.out.println("L'importo da aggiungere deve essere positivo.");
            return saldo; 
        }
        saldo += importo; 
        System.out.println("Deposito effettuato: " + importo);
        return saldo; 
    }

    public double prelievo(double importo) {
        if (importo <= 0) {
            System.out.println("L'importo da ritirare deve essere positivo.");
            return saldo; 
        } else if (saldo < importo) {
            System.out.println("Saldo insufficiente per effettuare il prelievo di " + importo);
            return saldo; 
        }
        saldo -= importo; 
        System.out.println("Prelievo effettuato: " + importo);
        return saldo; 
    }

    public double trasferimento(double importo, Conto contoDest) {
        if (contoDest == null) {
            System.out.println("Conto di destinazione non valido.");
            return saldo; 
        }

        double saldoDopoPrelievo = this.prelievo(importo);
        if (saldoDopoPrelievo >= 0) {
            contoDest.deposita(importo); 
            System.out.println(
                    "Trasferimento di " + importo + " effettuato con successo a " + contoDest.getContoCorrente());
        } else {
            System.out.println("Trasferimento non riuscito a causa di saldo insufficiente.");
        }
        return saldo; 
    }

    @Override
    public String toString() {
        return "Conto: [ Intestatario: " + intestatario + ", ContoCorrente: " + contoCorrente + ", Saldo: " + saldo
                + " ]";
    }

}