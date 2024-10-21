package it.its.pw.bank.ui;

import it.its.pw.bank.model.Conto;
import it.its.pw.bank.service.ContoService;
import java.util.List;
import javax.swing.*;

public class BancaApp {

    private final ContoService contoService = new ContoService();
    private JTextArea textAreaOutput;
    private JTextField textImporto;
    private JTextField textIntestatario; 
    private Conto contoSelezionato;

    public BancaApp() {
        initialize();
    }

    private void initialize() {
        JFrame frame = new JFrame("Gestione Conti Bancari");
        frame.setSize(500, 700); // Aumentata la dimensione della finestra
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Crea la barra dei menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenu menuConti = new JMenu("Conti");
        JMenu menuTuttiConti = new JMenu("Tutti i Conti");
        JMenu menuNuovoConto = new JMenu("Nuovo Conto"); // Nuovo menu per creare conti

        // Aggiungi elementi ai menu
        JMenuItem menuItemEsci = new JMenuItem("Esci");
        menuItemEsci.addActionListener(e -> System.exit(0));
        menuFile.add(menuItemEsci);

        menuBar.add(menuFile);
        menuBar.add(menuConti);
        menuBar.add(menuTuttiConti);
        menuBar.add(menuNuovoConto);

        // Pannello per le operazioni
        JLabel labelImporto = new JLabel("Importo:");
        labelImporto.setBounds(10, 20, 100, 25);
        frame.add(labelImporto);

        textImporto = new JTextField();
        textImporto.setBounds(120, 20, 250, 25);
        frame.add(textImporto);

        JButton btnDeposita = new JButton("Deposita");
        btnDeposita.setBounds(10, 60, 150, 25);
        frame.add(btnDeposita);

        JButton btnPreleva = new JButton("Preleva");
        btnPreleva.setBounds(170, 60, 150, 25);
        frame.add(btnPreleva);

        JButton btnVisualizzaSaldo = new JButton("Visualizza Saldo");
        btnVisualizzaSaldo.setBounds(10, 100, 310, 25);
        frame.add(btnVisualizzaSaldo);

        // Pannello per l'output
        textAreaOutput = new JTextArea();
        textAreaOutput.setBounds(10, 140, 460, 500); // Aumentata l'area di output
        textAreaOutput.setEditable(false);
        textAreaOutput.setLineWrap(true);
        textAreaOutput.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textAreaOutput);
        scrollPane.setBounds(10, 140, 460, 500);
        frame.add(scrollPane);

        // Eventi per i pulsanti
        btnDeposita.addActionListener(e -> deposita());
        btnPreleva.addActionListener(e -> preleva());
        btnVisualizzaSaldo.addActionListener(e -> visualizzaSaldo());

        // Aggiungi i conti al menu "Conti"
        aggiornaMenuConti(menuConti);

        // Aggiungi i conti al menu "Tutti i Conti"
        aggiornaMenuTuttiConti(menuTuttiConti);

        // Aggiungi l'opzione per creare un nuovo conto
        JMenuItem menuItemCreaConto = new JMenuItem("Crea Nuovo Conto");
        menuItemCreaConto.addActionListener(e -> creaNuovoContoDialog());
        menuNuovoConto.add(menuItemCreaConto);

        // Imposta la barra dei menu
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    // Aggiorna il menu "Conti" con i conti correnti dell'utente
    private void aggiornaMenuConti(JMenu menuConti) {
        menuConti.removeAll(); // Pulisce il menu esistente

        List<Conto> conti = contoService.getAllConti();
        if (conti.isEmpty()) {
            JMenuItem menuItemNoConti = new JMenuItem("Nessun conto disponibile");
            menuItemNoConti.setEnabled(false);
            menuConti.add(menuItemNoConti);
        } else {
            for (Conto conto : conti) {
                JMenuItem contoItem = new JMenuItem(
                        "Intestatario: " + conto.getIntestatario() + " | IBAN: " + conto.getContoCorrente());
                contoItem.addActionListener(e -> selezionaConto(conto));
                menuConti.add(contoItem);
            }
        }
    }

    // Aggiorna il menu "Tutti i Conti" con la lista di tutti i conti
    private void aggiornaMenuTuttiConti(JMenu menuTuttiConti) {
        menuTuttiConti.removeAll(); // Pulisce il menu esistente

        List<Conto> conti = contoService.getAllConti();
        if (conti.isEmpty()) {
            JMenuItem menuItemNoConti = new JMenuItem("Nessun conto esistente");
            menuItemNoConti.setEnabled(false);
            menuTuttiConti.add(menuItemNoConti);
        } else {
            JMenuItem menuItemVisualizzaTutti = new JMenuItem("Visualizza tutti i conti");
            menuItemVisualizzaTutti.addActionListener(e -> visualizzaTuttiConti(conti));
            menuTuttiConti.add(menuItemVisualizzaTutti);
        }
    }

    // Seleziona il conto corrente
    private void selezionaConto(Conto conto) {
        contoSelezionato = conto;
        textAreaOutput.append("Conto selezionato: " + conto.getContoCorrente() + " (Intestatario: "
                + conto.getIntestatario() + ")\n");
    }

    private void deposita() {
        if (contoSelezionato == null) {
            textAreaOutput.append("Nessun conto selezionato.\n");
            return;
        }

        try {
            double importo = Double.parseDouble(textImporto.getText().trim());
            contoService.deposita(contoSelezionato.getContoCorrente(), importo);
            textAreaOutput.append(
                    "Deposito di " + importo + " effettuato sul conto: " + contoSelezionato.getContoCorrente() + "\n");
        } catch (NumberFormatException ex) {
            textAreaOutput.append("Importo non valido.\n");
        }
    }

    private void preleva() {
        if (contoSelezionato == null) {
            textAreaOutput.append("Nessun conto selezionato.\n");
            return;
        }

        try {
            double importo = Double.parseDouble(textImporto.getText().trim());
            contoService.prelievo(contoSelezionato.getContoCorrente(), importo);
            textAreaOutput.append(
                    "Prelievo di " + importo + " effettuato dal conto: " + contoSelezionato.getContoCorrente() + "\n");
        } catch (NumberFormatException ex) {
            textAreaOutput.append("Importo non valido.\n");
        } catch (IllegalArgumentException ex) {
            textAreaOutput.append(ex.getMessage() + "\n");
        }
    }

    private void visualizzaSaldo() {
        if (contoSelezionato == null) {
            textAreaOutput.append("Nessun conto selezionato.\n");
            return;
        }

        Conto conto = contoService.getContoByIban(contoSelezionato.getContoCorrente());
        if (conto != null) {
            textAreaOutput
                    .append("Saldo del conto " + contoSelezionato.getContoCorrente() + ": " + conto.getSaldo() + "\n");
        } else {
            textAreaOutput.append("Conto non trovato.\n");
        }
    }

    // Visualizza tutti i conti
    private void visualizzaTuttiConti(List<Conto> conti) {
        textAreaOutput.setText(""); // Pulisci l'area di output prima di mostrare la lista
        if (conti.isEmpty()) {
            textAreaOutput.append("Nessun conto esistente.\n");
        } else {
            textAreaOutput.append("Lista di tutti i conti:\n");
            for (Conto conto : conti) {
                // Ottieni il saldo aggiornato per ciascun conto
                Conto contoAggiornato = contoService.getContoByIban(conto.getContoCorrente());
                if (contoAggiornato != null) {
                    textAreaOutput.append("IBAN: " + contoAggiornato.getContoCorrente() + ", Intestatario: "
                            + contoAggiornato.getIntestatario() + ", Saldo: " + contoAggiornato.getSaldo() + "\n");
                } else {
                    textAreaOutput.append("IBAN: " + conto.getContoCorrente() + " - Errore nel recupero del conto.\n");
                }
            }
        }
    }

    // Metodo per aprire una finestra di dialogo per la creazione di un nuovo conto
    private void creaNuovoContoDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Crea Nuovo Conto");
        dialog.setSize(300, 200);
        dialog.setLayout(null);

        JLabel labelIntestatario = new JLabel("Intestatario:");
        labelIntestatario.setBounds(10, 20, 100, 25);
        dialog.add(labelIntestatario);

        textIntestatario = new JTextField();
        textIntestatario.setBounds(120, 20, 150, 25);
        dialog.add(textIntestatario);

        JButton btnCrea = new JButton("Crea");
        btnCrea.setBounds(10, 100, 260, 25);
        btnCrea.addActionListener(e -> creaNuovoConto());
        dialog.add(btnCrea);

        dialog.setVisible(true);
    }

    // Metodo per creare un nuovo conto
    private void creaNuovoConto() {
        try {
            String intestatario = textIntestatario.getText().trim();

            if (intestatario.isEmpty()) {
                textAreaOutput.append("Errore: L'intestatario non può essere vuoto.\n");
                return;
            }

            // Creazione del conto con intestatario e saldo iniziale
            String nuovoIban = contoService.creaConto(intestatario); // Usa il servizio per creare un conto con saldo 0
            if (nuovoIban != null) {
                Conto nuovoConto = contoService.getContoByIban(nuovoIban); // Recupera il nuovo conto creato

                textAreaOutput.append("Nuovo conto creato! IBAN: " + nuovoConto.getContoCorrente());

                // Aggiorna il menu conti per mostrare il nuovo conto
                aggiornaMenuConti(
                        (JMenu) ((JFrame) SwingUtilities.getWindowAncestor(textAreaOutput)).getJMenuBar().getMenu(1));
            } else {
                textAreaOutput.append("Errore: Creazione del conto fallita.\n");
            }
        } catch (NumberFormatException ex) {
            textAreaOutput.append("Errore: Saldo iniziale non valido.\n");
        }
    }

}