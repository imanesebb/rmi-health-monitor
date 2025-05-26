package medbd;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;


public class ClientGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel panelMain;
    private JPanel panelLogin, panelAccueil, panelPatient, panelHistorique;
    private JTextField txtUsernameLogin;
    private JPasswordField txtPasswordLogin;
    private JMenuBar menuBar;
    private boolean isLoggedIn = false;
    JTextField txtFrequenceCardiaque,txtIdPatient,txtTension,txtTemperature;
    private Interserv service;

    public ClientGUI() {
    	
        setTitle("APPLICATION PATIENT:D");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createLoginPanel();
        createAccueilPanel();
        createPatientPanel();
        createHistoriquePanel();
      

        try {
            service = (Interserv) java.rmi.Naming.lookup("HealthMonitorService");
            System.out.println("✅ Connected to RMI server!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Cannot connect to server.", "Error", JOptionPane.ERROR_MESSAGE);
        }

       
        panelMain = new JPanel(new CardLayout());
        panelMain.add(panelLogin, "Login");
        panelMain.add(panelAccueil, "Accueil");
        panelMain.add(panelPatient, "Patient");
        panelMain.add(panelHistorique, "Historique");
     
        menuBar = createMenuBar();
        setJMenuBar(menuBar);
        toggleMenuBar(false);
        showPanel("Login");
        getContentPane().add(panelMain, BorderLayout.CENTER);
    }
    public void receiveAlert(String alertMessage) throws RemoteException {
        SwingUtilities.invokeLater(() -> 
            JOptionPane.showMessageDialog(this, alertMessage, "🚨 Medical Alert", JOptionPane.WARNING_MESSAGE)
        );
    }

    private void createLoginPanel() {
        panelLogin = new JPanel(new GridBagLayout());
        panelLogin.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitle = new JLabel("interface patient");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelLogin.add(lblTitle, gbc);

        JLabel lblSubtitle = new JLabel("Entrer vos informations");
        lblSubtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        panelLogin.add(lblSubtitle, gbc);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelLogin.add(lblUsername, gbc);

        txtUsernameLogin = new JTextField(15);
        txtUsernameLogin.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelLogin.add(txtUsernameLogin, gbc);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelLogin.add(lblPassword, gbc);
        txtPasswordLogin = new JPasswordField(15);
        txtPasswordLogin.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelLogin.add(txtPasswordLogin, gbc);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setFocusPainted(false);
        btnLogin.addActionListener(this::performLogin);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelLogin.add(btnLogin, gbc);
    }

    private void createAccueilPanel() {
        panelAccueil = new JPanel(new BorderLayout()) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("background.jpg"); 
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        JLabel lblWelcome = new JLabel("Bienvenue dans l'interface patient!", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 30));
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setVerticalAlignment(SwingConstants.CENTER);

        panelAccueil.add(lblWelcome, BorderLayout.CENTER);
    }

    private void createPatientPanel() {
        panelPatient = new JPanel(new GridBagLayout());
        panelPatient.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitle = new JLabel("Gestion des Données Médicales");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPatient.add(lblTitle, gbc);


        JLabel lblTension = new JLabel("Tension Artérielle (mmHg):");
        lblTension.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelPatient.add(lblTension, gbc);

        txtTension = new JTextField(15);
        txtTension.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
       
        panelPatient.add(txtTension, gbc);

        JLabel lblTemperature = new JLabel("Température (°C):");
        lblTemperature.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelPatient.add(lblTemperature, gbc);

        txtTemperature = new JTextField(15);
        txtTemperature.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;

        panelPatient.add(txtTemperature, gbc);

        JLabel lblFrequenceCardiaque = new JLabel("Fréquence Cardiaque (bpm):");
        lblFrequenceCardiaque.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelPatient.add(lblFrequenceCardiaque, gbc);

        txtFrequenceCardiaque = new JTextField(15);
        txtFrequenceCardiaque.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;

        panelPatient.add(txtFrequenceCardiaque, gbc);

        JButton btnEnvoyer = new JButton("Envoyer");
        btnEnvoyer.setFont(new Font("Arial", Font.BOLD, 14));
        btnEnvoyer.setFocusPainted(false);
        btnEnvoyer.addActionListener(e -> envoyerDonneesMedicales());
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPatient.add(btnEnvoyer, gbc);
    }

    private void envoyerDonneesMedicales() {
        int patientId = SessionManager.getLoggedInPatientId();
        if (patientId == -1) {
            JOptionPane.showMessageDialog(this, "Erreur: Aucun patient connecté!D:", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String tension = txtTension.getText().trim();
        String temperature = txtTemperature.getText().trim();
        String frequenceCardiaque = txtFrequenceCardiaque.getText().trim();

        if (tension.isEmpty() || temperature.isEmpty() || frequenceCardiaque.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.:3", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double tensionValue = Double.parseDouble(tension);
            double temperatureValue = Double.parseDouble(temperature);
            int frequenceValue = Integer.parseInt(frequenceCardiaque);

            boolean success = service.envoyerDonneesMedicales(patientId, tensionValue, temperatureValue, frequenceValue);

            if (success) {
                JOptionPane.showMessageDialog(this, "Données enregistrées avec succès !");
                clearForm(txtTension, txtTemperature, txtFrequenceCardiaque); 
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement des données.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        } catch (RemoteException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erreur: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }



    private void clearForm(JTextField txtTension, JTextField txtTemperature, JTextField txtFrequenceCardiaque) {
       
        txtTension.setText("");
        txtTemperature.setText("");
        txtFrequenceCardiaque.setText("");
    }

    private void createHistoriquePanel() {
        panelHistorique = new JPanel(new BorderLayout());
        JLabel lblHistoriqueTitle = new JLabel("Historique des Données Médicales", SwingConstants.CENTER);
        lblHistoriqueTitle.setFont(new Font("Arial", Font.BOLD, 24));
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Date et Heure");
        model.addColumn("Tension (mmHg)");
        model.addColumn("Température (°C)");
        model.addColumn("Fréquence Cardiaque (bpm)");
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        panelHistorique.add(lblHistoriqueTitle, BorderLayout.NORTH);
        panelHistorique.add(scrollPane, BorderLayout.CENTER);
    }


    private void loadHistoricalData(DefaultTableModel model) {
        int patientId = SessionManager.getLoggedInPatientId();
        System.out.println("🔍 Loading history for patient ID: " + patientId); 

        if (patientId == -1) {
            JOptionPane.showMessageDialog(this, "❌ Erreur: Aucun patient connecté !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            List<InfoClient> history = service.getPatientHistory(patientId);
            model.setRowCount(0);

            boolean hasData = false;
            for (InfoClient data : history) {
                hasData = true;
                model.addRow(new Object[]{
                	    data.getDate(),
                	    data.getTension(),
                	    data.getTemperature(),
                	    data.getFrequenceCardiaque()
                	});

            }

            if (!hasData) {
                System.out.println("❌ No data found for patient.");
            }

            model.fireTableDataChanged(); 

        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "❌ Erreur: Impossible de récupérer les données historiques.", "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }




   

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu mnuAccueil = new JMenu("Accueil");
        mnuAccueil.setMnemonic('A');
        JMenuItem mnuAccueilItem = new JMenuItem("Aller à l'accueil");
        mnuAccueilItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.CTRL_DOWN_MASK));
        mnuAccueilItem.addActionListener(e -> showPanel("Accueil"));
        mnuAccueil.add(mnuAccueilItem);
        menuBar.add(mnuAccueil);

        JMenu mnuPatient = new JMenu("Patient");
        mnuPatient.setMnemonic('P');
        JMenuItem mnuPatientItem = new JMenuItem("Gérer les patients");
        mnuPatientItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.CTRL_DOWN_MASK));
        mnuPatientItem.addActionListener(e -> showPanel("Patient"));
        mnuPatient.add(mnuPatientItem);
        menuBar.add(mnuPatient);

        JMenu mnuHistorique = new JMenu("Historique");
        mnuHistorique.setMnemonic('H');
        JMenuItem mnuHistoriqueItem = new JMenuItem("Voir l'historique");
        mnuHistoriqueItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, KeyEvent.CTRL_DOWN_MASK));
        mnuHistoriqueItem.addActionListener(e -> showPanel("Historique"));
        mnuHistorique.add(mnuHistoriqueItem);
        menuBar.add(mnuHistorique);


              JMenuItem mnuQuitter = new JMenuItem("Quitter");
        mnuQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        mnuQuitter.addActionListener(e -> System.exit(0));
        menuBar.add(mnuQuitter);

        return menuBar;
    }

    private void showPanel(String name) {

        if (!isLoggedIn && !name.equals("Login")) {
            JOptionPane.showMessageDialog(this, "Veuillez vous connecter pour accéder à cette page.", "Accès refusé", JOptionPane.WARNING_MESSAGE);
            return;
        }
        CardLayout cl = (CardLayout) (panelMain.getLayout());
        cl.show(panelMain, name);
    }

    private void toggleMenuBar(boolean enabled) {
        if (menuBar == null) {
            return;
        }
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            if (menu != null) {
                menu.setEnabled(enabled);
            }
        }
    }

    private void updateHistoryPanel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Date et Heure");
        model.addColumn("Tension (mmHg)");
        model.addColumn("Température (°C)");
        model.addColumn("Fréquence Cardiaque (bpm)");

        loadHistoricalData(model); 

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        panelHistorique.removeAll(); 
        panelHistorique.add(new JLabel("Historique des Données Médicales", SwingConstants.CENTER), BorderLayout.NORTH);
        panelHistorique.add(scrollPane, BorderLayout.CENTER);

        panelHistorique.revalidate();
        panelHistorique.repaint(); 
    }


    private void performLogin(ActionEvent e) {
        try {
            String username = txtUsernameLogin.getText().trim();
            String password = new String(txtPasswordLogin.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs!", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int patientId = service.loginPatient(username, password);

            if (patientId != -1) {
                SessionManager.setPatient(patientId, username);
                isLoggedIn = true;  

                System.out.println("✅ Patient logged in! ID = " + patientId);

                try {
                    ClientCallback callback = new ClientCallbackImpl(this);
                    service.registerPatientForAlerts(patientId, callback);
                    System.out.println("✅ Patient " + patientId + " registered for real-time alerts!");
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }

                JOptionPane.showMessageDialog(this, "Connexion réussie!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                toggleMenuBar(true);
                showPanel("Accueil");
                updateHistoryPanel();
            } else {
                JOptionPane.showMessageDialog(this, "Nom d'utilisateur ou mot de passe incorrect!", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (RemoteException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur de connexion au serveur.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

  

    public static void main(String[] args) {
    	 try {
             UIManager.setLookAndFeel(new FlatDarkLaf()); 
         } catch (Exception e) {
             e.printStackTrace();
         }
        SwingUtilities.invokeLater(() -> new ClientGUI().setVisible(true));
    }
}
