package it.its.pw.bank.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDb {

    private static final String URL = "jdbc:mysql://localhost:3306/gestioneBanca";
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 

    public static Connection connectDatabase() {
        Connection connection = null;
        try {
            // Carica il driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Stabilisce la connessione
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connessione al database avvenuta con successo!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver non trovato: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Errore nella connessione al database: " + e.getMessage());
        }
        return connection;
    }
}