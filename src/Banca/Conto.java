package Banca;

public class Conto {
	
	
	private String ContoCorrente;
	private String Intestatario;
	private double saldo;
	    
	    
	public Conto(String Intestatario, String iban) {
		this.Intestatario = Intestatario;
	    this.ContoCorrente = iban;
	    this.saldo = 0; // Imposta il saldo a 0 di default
	}
	
	public String getContoCorrente() {
		return ContoCorrente;
	}
	public void setContoCorrente(String contoCorrente) {
		ContoCorrente = contoCorrente;
	}
	public String getIntestatario() {
		return Intestatario;
	}
	public void setIntestatario(String intestatario) {
		Intestatario = intestatario;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public void deposita(double importo) {
		if (importo < 0) {
	        System.out.println("Non puoi aggiungere denaro negativo");
		}
		else {
			this.saldo = this.saldo + importo;
		}
		
	}
	
	public void prelievo(double importo) {
	    if (importo <= 0) {
	        System.out.println("L'importo da ritirare deve essere positivo");
	    } else if (this.saldo < importo) {
	        System.out.println("Saldo insufficiente");
	    } else {
	        this.saldo -= importo; // Sottrae l'importo dal saldo
	        System.out.println("Prelievo effettuato: " + importo);
	    }
	}
	
	@Override
	public String toString() {
		return "Conto: [ Intestatario: " + Intestatario +", ContoCorrente: " + ContoCorrente+", Saldo: " + saldo+" ]";
	}
	
	
}
