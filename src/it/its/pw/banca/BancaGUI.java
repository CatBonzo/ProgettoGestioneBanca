package it.its.pw.banca;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BancaGUI extends JFrame {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JTextField inputIntestatario, inputIBAN, inputImporto, inputIbanDestinatario;
    public BancaGUI() {
        // Configurazione finestra principale
        setTitle("Gestione Utenti");
        setSize(740, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creazione e visualizzazione della GUI
        createAndShowGUI();
    }

    private JMenuBar createBarraMenu() {
        // Creazione della barra del menu
        JMenuBar barraMenu = new JMenuBar();
        JMenu menuOperation = new JMenu("Operazioni");

        // Creazione delle voci di menu
        JMenuItem menuCreazioneConto = new JMenuItem("Creazione Conto");
        JMenuItem menuDepositaConto = new JMenuItem("Depositare denaro");
        JMenuItem menuPrelievoConto = new JMenuItem("Prelievo denaro");
        JMenuItem menuVisualizzazioneConto = new JMenuItem("Visualizzazione conto");
        JMenuItem menuTrasferisciDenaro= new JMenuItem("Trasferisci denaro");
        JMenuItem menuChisuraConto = new JMenuItem("Chiusura Conto");
        
        // Aggiunta delle voci al menu
        menuOperation.add(menuCreazioneConto);
        menuOperation.add(menuDepositaConto);
        menuOperation.add(menuPrelievoConto);
        menuOperation.add(menuVisualizzazioneConto);
        menuOperation.add(menuTrasferisciDenaro);
        menuOperation.add(menuChisuraConto);
        
        // Aggiunta del menu alla barra
        barraMenu.add(menuOperation);

        // Gestione delle azioni dei menu
        menuCreazioneConto.addActionListener(e -> cardLayout.show(mainPanel, "PanelContoCreate"));
        menuDepositaConto.addActionListener(e -> cardLayout.show(mainPanel, "PanelDeposita"));
        menuPrelievoConto.addActionListener(e -> cardLayout.show(mainPanel, "PanelPrelievo"));
        menuVisualizzazioneConto.addActionListener(e -> cardLayout.show(mainPanel, "PanelVisualizzazioneConto"));
        menuTrasferisciDenaro.addActionListener(e -> cardLayout.show(mainPanel, "PanelTrasferisci"));
        menuChisuraConto.addActionListener(e -> cardLayout.show(mainPanel, "PanelTChisuraConto"));
        return barraMenu;
    }

   

    private JPanel createConto() {
    	// Pannello creazione conto
        JPanel pnlAddUser = new JPanel(new BorderLayout());
        JPanel pnlLabelsAndTextFields = new JPanel(new BorderLayout());
        JPanel pnlButtons = new JPanel();

        // Etichette e campi di testo
        JLabel lblIntestatario = new JLabel("Intestatario: ");
        

        JTextField txtIntestatario = new JTextField(20);
  

        // Pannello per le etichette e i campi di testo
        JPanel pnlLabel = new JPanel(new GridLayout(4, 1));
        pnlLabel.add(lblIntestatario);
       

        JPanel pnlText = new JPanel(new GridLayout(4, 1));
        pnlText.add(txtIntestatario);
    

        pnlLabelsAndTextFields.add(pnlLabel, BorderLayout.WEST);
        pnlLabelsAndTextFields.add(pnlText, BorderLayout.CENTER);

        // Bottoni
        JButton btnInvia = new JButton("Invia");
        JButton btnAnnulla = new JButton("Annulla");

        pnlButtons.add(btnInvia);
        pnlButtons.add(btnAnnulla);

        pnlAddUser.add(pnlLabelsAndTextFields, BorderLayout.NORTH);
        pnlAddUser.add(pnlButtons, BorderLayout.CENTER);

        // Gestione delle azioni dei bottoni
        btnAnnulla.addActionListener(e -> System.out.println("Annulla"));
        btnInvia.addActionListener(e -> System.out.println("Utente aggiunto: " + txtIntestatario.getText()));

        return pnlAddUser;
    }
    
    private JPanel depositaDenaro() {
   	 JPanel operationPanel = new JPanel();
        JLabel labelIBAN = new JLabel("IBAN:");
        inputIBAN = new JTextField(15);
        JLabel labelImporto = new JLabel("Importo:");
        inputImporto = new JTextField(8);
        JButton btnDeposita = new JButton("Deposita");
        
        operationPanel.add(labelIBAN);
        operationPanel.add(inputIBAN);
        operationPanel.add(labelImporto);
        operationPanel.add(inputImporto);
        operationPanel.add(btnDeposita);
        
        return operationPanel;
   }
    
    private JPanel ritiraDenaro() {
      	 JPanel operationPanel = new JPanel();
           JLabel labelIBAN = new JLabel("IBAN:");
           inputIBAN = new JTextField(15);
           JLabel labelImporto = new JLabel("Importo:");
           inputImporto = new JTextField(8);
           JButton btnDeposita = new JButton("Ritira");
           
           operationPanel.add(labelIBAN);
           operationPanel.add(inputIBAN);
           operationPanel.add(labelImporto);
           operationPanel.add(inputImporto);
           operationPanel.add(btnDeposita);
           
           return operationPanel;
      }
    
    private JPanel visualizzazioneDenaro() {
    	 JPanel operationPanel = new JPanel();
          return operationPanel;
    }
    
    private JPanel trasferisciDenaro() {
    	 JPanel transferPanel = new JPanel();
    	 JLabel labelIBAN = new JLabel("IBAN:");
         inputIBAN = new JTextField(15);
         JLabel labelImporto = new JLabel("Importo:");
         inputImporto = new JTextField(8);
         JLabel labelIbanDestinatario = new JLabel("IBAN Destinatario:");
         inputIbanDestinatario = new JTextField(15);
         JButton btnTrasferisci = new JButton("Trasferisci");
         transferPanel.add(labelIBAN);
         transferPanel.add(inputIBAN);
         transferPanel.add(labelIbanDestinatario);
         transferPanel.add(inputIbanDestinatario);
         transferPanel.add(labelImporto);
         transferPanel.add(inputImporto);
         transferPanel.add(btnTrasferisci);
         return transferPanel;
     }
    
    
    private JPanel chiusuraConto() {
    	JPanel transferPanel = new JPanel();
   	 	JLabel labelIBAN = new JLabel("IBAN:");
        inputIBAN = new JTextField(15);
       
        JButton btnTrasferisci = new JButton("Chiusura");
        
        transferPanel.add(labelIBAN);
        transferPanel.add(inputIBAN);
        transferPanel.add(btnTrasferisci);
        return transferPanel;
    }
    
    private void createAndShowGUI() {
        // Creazione del layout principale
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Creazione e aggiunta dei pannelli al CardLayout
        mainPanel.add(new JLabel("Benvenuto!"), "PanelMain");
        mainPanel.add(createConto(), "PanelContoCreate");
        mainPanel.add(depositaDenaro(), "PanelDeposita");
        mainPanel.add(ritiraDenaro(), "PanelPrelievo");
        mainPanel.add(visualizzazioneDenaro(), "PanelVisualizzazioneConto");
        mainPanel.add(trasferisciDenaro(), "PanelTrasferisci");
        mainPanel.add(chiusuraConto(), "PanelTChisuraConto");

        // Aggiunta dei componenti alla finestra
        setJMenuBar(createBarraMenu());
        add(mainPanel);

        // Visualizza il pannello principale all'inizio
        cardLayout.show(mainPanel, "PanelMain");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BancaGUI app = new BancaGUI();
            app.setVisible(true);
        });
    }
}
