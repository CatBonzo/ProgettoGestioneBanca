package Banca;

import java.util.Scanner;

public class Main2 {
    
    // Menu
    public static void menu() {
        System.out.println("------Banca-----------");
        System.out.println("1) Creazione conto");
        System.out.println("2) Inserimento saldo");
        System.out.println("3) Ritirare saldo");
        System.out.println("4) Visualizzazione saldo");
        System.out.println("5) Esci");
        System.out.println("----------------------");
    }
    
    public static void main(String[] args) {
        
        Banca b1 = new Banca();
        Scanner input = new Scanner(System.in);
        String intestatario = "";
        int scelta = 0;
        
        // Ciclo infinito per mostrare il menu finché non si sceglie di uscire
        while (scelta != 5) {
            menu();
            System.out.print("Scegli un'opzione: ");
            scelta = input.nextInt();
            input.nextLine(); // Consuma la linea rimasta vuota
            
            switch(scelta) {
                case 1: // Creazione conto
                    System.out.print("Intestatario: ");
                    intestatario = input.nextLine();
                    
                    b1.AggiungiUtente(intestatario);
                    b1.stampaConti();
                    break;
                    
                case 2: // Inserimento saldo
                    System.out.print("IBAN: ");
                    String ibanDeposito = input.nextLine();
                    
                    System.out.print("Deposita: ");
                    double deposito = input.nextDouble();
                    
                    Conto contoDeposito = b1.getConto(ibanDeposito);
                    if (contoDeposito != null) {
                        contoDeposito.deposita(deposito);
           
                    } else {
                        System.out.println("Nessun conto trovato con questo IBAN.");
                    }
                    
                    break;
                    
                case 3: // Ritirare saldo
                    System.out.print("IBAN: ");
                    String ibanPrelievo = input.nextLine();
                    
                    System.out.print("Importo da ritirare: ");
                    double prelievo = input.nextDouble();
                    
                    Conto contoPrelievo = b1.getConto(ibanPrelievo);
                    if (contoPrelievo != null) {
                        contoPrelievo.prelievo(prelievo);
               
                    } else {
                        System.out.println("Nessun conto trovato con questo IBAN.");
                    }
                    break;
                    
                case 4: // Visualizzazione saldo
                    System.out.print("IBAN: ");
                    String ibanVisualizza = input.nextLine();
                    
                    b1.visualizzazioneSaldo(ibanVisualizza);
                    break;
                
                case 5: // Esci
                    System.out.println("Uscita dal programma.");
                    break;
                
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
        
        input.close();
    }
}
