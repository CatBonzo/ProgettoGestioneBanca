package it.its.pw.bank;

import it.its.pw.bank.ui.BancaApp;
import javax.swing.SwingUtilities; 

public class Main {
public static void main(String[] args) {
        SwingUtilities.invokeLater(BancaApp::new); // Avvia l'applicazione
    }
}