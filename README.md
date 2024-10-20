Progetto Gestione Banca

Descrizione Generale

Il progetto Banca è stato sviluppato in due versioni distinte:

	1.	Versione GUI: utilizza Swing per creare un’interfaccia grafica (GUI) che permette all’utente di eseguire le operazioni bancarie come la creazione di conti, depositi, prelievi, trasferimenti e visualizzazione del saldo.
	2.	Versione Web: utilizza Spring Boot con Thymeleaf per creare un’applicazione web che interagisce con un database MySQL. Questa versione permette di gestire i conti bancari attraverso un’interfaccia web moderna, con persistenza dei dati e funzionalità avanzate.

Funzionalità Implementate in Entrambe le Versioni:

	•	Creazione di nuovi conti correnti.
	•	Deposito di somme di denaro sui conti.
	•	Prelievo di denaro dai conti.
	•	Visualizzazione del saldo attuale di un conto.
	•	Trasferimento di denaro tra conti.
	•	Chiusura di conti.

Versione GUI (Swing)

Descrizione

Questa versione utilizza Swing per fornire una semplice interfaccia grafica che permette all’utente di interagire con il sistema bancario tramite pannelli e pulsanti. Non c’è persistenza dei dati, quindi le informazioni sui conti esistono solo durante l’esecuzione dell’applicazione.

Caratteristiche Principali

	•	Interfaccia Utente: Finestra principale con menu che permette di accedere alle varie operazioni bancarie.
	•	Pannelli: Ogni operazione ha il proprio pannello, come il pannello per la creazione di un conto, il deposito, il prelievo, il trasferimento e la chiusura di un conto.
	•	Simulazione Banca: I dati relativi ai conti sono gestiti in memoria, senza persistenza al termine dell’esecuzione.

Versione Web (Spring Boot + Thymeleaf)

Descrizione

Questa versione è stata progettata come un’applicazione web utilizzando Spring Boot per il backend, Thymeleaf per il frontend e MySQL per la persistenza dei dati. In questa versione, i dati sui conti sono memorizzati in un database MySQL, permettendo la conservazione e la gestione dei dati in modo persistente.

Caratteristiche Principali

	•	Interfaccia Web: Un sito web dove l’utente può accedere e gestire i propri conti bancari tramite pagine HTML generate con Thymeleaf.
	•	Persistenza dei Dati: Tutte le informazioni sui conti (intestatari, saldo, transazioni) sono memorizzate in un database MySQL, garantendo la persistenza tra le sessioni.
	•	REST API: Il backend è basato su Spring Boot, che fornisce un’architettura REST per gestire le operazioni bancarie.
	•	Gestione delle Transazioni: Le transazioni bancarie sono gestite in modo sicuro e robusto, con controlli sulle operazioni come la verifica del saldo prima di un prelievo.

Dettagli delle Funzionalità Implementate

Funzionalità Comune

	•	Creazione di Conti: L’utente può creare un nuovo conto inserendo il nome dell’intestatario. Un identificativo univoco viene generato automaticamente.
	•	Deposito di Denaro: È possibile depositare denaro su un conto esistente utilizzando il numero del conto.
	•	Prelievo di Denaro: Consente di prelevare denaro da un conto, con controlli per garantire che non vengano prelevati importi superiori al saldo.
	•	Trasferimento di Denaro: Permette di trasferire denaro tra conti esistenti.
	•	Chiusura di Conti: L’utente può chiudere un conto se il saldo è pari a zero.

Funzionalità Aggiuntive nella Versione Web

	•	Autenticazione (Opzionale): È possibile implementare un sistema di login per proteggere l’accesso ai conti bancari.
	•	Gestione delle Valute (Opzionale): Potenziale supporto per conti in più valute, con conversioni automatiche tra di esse.
	•	Reportistica: È possibile aggiungere funzionalità per generare report sui movimenti dei conti o per esportare i dati in formati come CSV o PDF.

Dipendenze e Configurazioni

Versione GUI (Swing)

	•	Java Version: 17
	•	Librerie: Nessuna libreria esterna richiesta (utilizzo di librerie standard di Swing).

Versione Web (Spring Boot)

	•	Java Version: 17
	•	Dipendenze Maven:
	•	Spring Boot Starter Data JPA: Per la gestione della persistenza dei dati.
	•	Spring Boot Starter Web: Per la costruzione di API RESTful e funzionalità web.
	•	MySQL Driver: Per connettere l’applicazione al database MySQL.
	•	Thymeleaf: Template engine per il rendering di pagine HTML.
	•	Spring Boot DevTools: Per migliorare lo sviluppo e il debugging dell’applicazione.
