package Banca;

public class Conto {
	
	
	private String ContoCorrente;
	private String Intestatario;
	private double saldo;
		
	public Conto(double saldo) {
		this.saldo = saldo;
	}
	    
	    public Conto(String iban, String Intestatario, double saldo) {
	        this.ContoCorrente = iban;
	        this.Intestatario = Intestatario;
	        this.saldo = saldo; // Imposta il saldo passato come parametro
	    }
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
	 
	@Override
	public String toString() {
		return "Conto: [ Intestatario: " + Intestatario +", ContoCorrente: " + ContoCorrente+", Saldo: " + saldo+" ]";
	}
	
	
}
