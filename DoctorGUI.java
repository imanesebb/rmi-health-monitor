package medbd;
import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class DoctorGUI extends JFrame implements Remote{
	private Interserv service;

	private static final long serialVersionUID = 1L;
	private JPanel panelMain;
    private JPanel panelLogin, panelSignUp, panelAccueil, panelPatient, panelHistorique, panelAjoutPatient;
    private JTextField txtUsernameLogin, txtUsernameSignUp, txtEmailSignUp,txtPrénomSignUp,txtNomSignUp,txtSpecialtySignUp,txtPhoneSignUp,txtTension;
    private JPasswordField txtPasswordLogin, txtPasswordSignUp, txtConfirmPasswordSignUp;
    private JTextField txtFirstName;
    private JTextField txtLastName;
 
    private JTextField txtLastNamee;
    private JTextField txtFirstNamee;
    private JTextField txtDateNaissance;
    private JTextField txtAdresse;
    private JTextField txtTelephone;
    private JTextField txtEmail;
    private JTextField txtLogin;
    private JPasswordField txtPassword;
    private JRadioButton rbMale;
    private JRadioButton rbFemale;
    private JTextField txtTemperature;
    private JTextField txtFrequenceCardiaque;
    private JMenuBar menuBar;
    public DoctorGUI() {
    	
    	try {
    	    service = (Interserv) Naming.lookup("HealthMonitorService");
    	    System.out.println("✅ Connecté au serveur:3");
    	} catch (Exception e) {
    	    JOptionPane.showMessageDialog(this, "❌ Impossible de se connecter au serveur!:(", "Erreur", JOptionPane.ERROR_MESSAGE);
    	    e.printStackTrace();
    	}

        setTitle("APPLICATION MEDECIN");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createLoginPanel();
        createSignUpPanel();
        createAccueilPanel();
        createPatientPanel();
        createHistoriquePanel();
        createaddpatientPanel();
        panelMain = new JPanel(new CardLayout());
        panelMain.add(panelLogin, "Login");
        panelMain.add(panelSignUp, "SignUp");
        panelMain.add(panelAccueil, "Accueil");
        panelMain.add(panelPatient, "Patient");
        panelMain.add(panelHistorique, "Historique");
        panelMain.add(panelAjoutPatient, "ajout patient");
        menuBar = createMenuBar();
        setJMenuBar(menuBar); 
        toggleMenuBar(false);
        showPanel("Login");
        getContentPane().add(panelMain, BorderLayout.CENTER);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu mnuAccueil = new JMenu("Accueil");
        mnuAccueil.setMnemonic('A');
        JMenuItem mnuAccueilItem = new JMenuItem("Aller à l'accueil");
        mnuAccueilItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.CTRL_DOWN_MASK));
        mnuAccueilItem.addActionListener(e -> showPanel("Accueil"));
        mnuAccueil.add(mnuAccueilItem);
        menuBar.add(mnuAccueil);
        JMenu mnuPatient = new JMenu("Patient");
        mnuPatient.setMnemonic('P');
        JMenuItem mnuPatientItem = new JMenuItem("Gérer les patients");
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
        JMenu mnuajoutpatient = new JMenu("ajout patient");
        mnuajoutpatient.setMnemonic('M');
        JMenuItem mnuMiseAjourItem = new JMenuItem("ajout d'un nouveau patient");
        mnuMiseAjourItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, KeyEvent.CTRL_DOWN_MASK));
        mnuMiseAjourItem.addActionListener(e -> showPanel("ajout patient"));
        mnuajoutpatient.add(mnuMiseAjourItem);
        menuBar.add(mnuajoutpatient);
        JButton btnLogout = new JButton("Déconnexion");
        btnLogout.addActionListener(e -> {
            SessionManager.logout();
            JOptionPane.showMessageDialog(this, "Vous avez été déconnecté:(", "Déconnexion", JOptionPane.INFORMATION_MESSAGE);
            new DoctorGUI().setVisible(true); 
            dispose();
        });


        return menuBar;
    }

    private void createLoginPanel() {
        panelLogin = new JPanel(new GridBagLayout());
        panelLogin.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        JLabel lblTitle = new JLabel("interface Medecin");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelLogin.add(lblTitle, gbc);
        JLabel lblSubtitle = new JLabel("connetez vous:3");
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
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.addActionListener(this::performLogin);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelLogin.add(btnLogin, gbc);
        JButton btnGoToSignUp = new JButton("Sign Up");
        btnGoToSignUp.setFont(new Font("Arial", Font.PLAIN, 12));
        btnGoToSignUp.setBorderPainted(false);
        btnGoToSignUp.setContentAreaFilled(false);
        btnGoToSignUp.addActionListener(e -> showPanel("SignUp"));
        gbc.gridy = 5;
        panelLogin.add(btnGoToSignUp, gbc);
    }
    private void createSignUpPanel() {
        panelSignUp = new JPanel(new GridBagLayout());
        panelSignUp.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        JLabel lblTitle = new JLabel("Create your account");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelSignUp.add(lblTitle, gbc);
        
        JLabel lblNom = new JLabel("Nom");
        lblNom.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelSignUp.add(lblNom, gbc);

        txtNomSignUp = new JTextField(15);
        txtNomSignUp.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelSignUp.add(txtNomSignUp, gbc);
        JLabel lblPrénom = new JLabel("prénom");
        lblPrénom.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelSignUp.add(lblPrénom, gbc);

        txtPrénomSignUp = new JTextField(15);
        txtPrénomSignUp.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelSignUp.add(txtPrénomSignUp, gbc);
        
        
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelSignUp.add(lblUsername, gbc);

        txtUsernameSignUp = new JTextField(15);
        txtUsernameSignUp.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelSignUp.add(txtUsernameSignUp, gbc);

       
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelSignUp.add(lblEmail, gbc);

        txtEmailSignUp = new JTextField(15);
        txtEmailSignUp.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelSignUp.add(txtEmailSignUp, gbc);
        
        JLabel lblspeciality = new JLabel("specialité");
        lblspeciality.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelSignUp.add(lblspeciality, gbc);

        txtSpecialtySignUp = new JTextField(15);
        txtSpecialtySignUp.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelSignUp.add(txtSpecialtySignUp, gbc);
        JLabel lblphone = new JLabel("phone");
        lblphone.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelSignUp.add(lblphone, gbc);

        txtPhoneSignUp = new JTextField(15);
        txtPhoneSignUp.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelSignUp.add(txtPhoneSignUp, gbc);

       
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelSignUp.add(lblPassword, gbc);

        txtPasswordSignUp = new JPasswordField(15);
        txtPasswordSignUp.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelSignUp.add(txtPasswordSignUp, gbc);

        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelSignUp.add(lblConfirmPassword, gbc);

        txtConfirmPasswordSignUp = new JPasswordField(15);
        txtConfirmPasswordSignUp.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelSignUp.add(txtConfirmPasswordSignUp, gbc);

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.setFont(new Font("Arial", Font.BOLD, 14));
        btnSignUp.setForeground(Color.WHITE);
        btnSignUp.setFocusPainted(false);
        btnSignUp.addActionListener(this::performSignUp); 
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelSignUp.add(btnSignUp, gbc);

        JButton btnGoToLogin = new JButton("Login");
        btnGoToLogin.setFont(new Font("Arial", Font.PLAIN, 12));
        btnGoToLogin.setBorderPainted(false);
        btnGoToLogin.setContentAreaFilled(false);
        btnGoToLogin.addActionListener(e -> showPanel("Login"));  
        gbc.gridy = 6;
        panelSignUp.add(btnGoToLogin, gbc);
        
    }
    private void performSignUp(ActionEvent e) {
        String firstName = txtPrénomSignUp.getText().trim();
        String lastName = txtNomSignUp.getText().trim();
        String specialty = txtSpecialtySignUp.getText().trim();
        String phone = txtPhoneSignUp.getText().trim();
        String email = txtEmailSignUp.getText().trim();
        String username = txtUsernameSignUp.getText().trim();
        String password = new String(txtPasswordSignUp.getPassword()).trim();

        if (firstName.isEmpty() || lastName.isEmpty() || specialty.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs obligatoires!", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Interserv service = (Interserv) java.rmi.Naming.lookup("HealthMonitorService");
            boolean success = service.registerDoctor(firstName, lastName, specialty, phone, email, username, password);

            if (success) {
                JOptionPane.showMessageDialog(this, "Inscription réussie! Vous pouvez maintenant vous connecter.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "L'inscription a échoué.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur de connexion au serveur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
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

        JLabel lblWelcome = new JLabel("Bienvenue dans notre application !", SwingConstants.CENTER);
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

        JLabel lblTitle = new JLabel("Gestion des Patients à Distance");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPatient.add(lblTitle, gbc);
        
        JLabel lblFirstName = new JLabel("Prénom:");
        txtFirstName = new JTextField(15);
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelPatient.add(lblFirstName, gbc);
        
        gbc.gridx = 1; gbc.gridy = 1; panelPatient.add(txtFirstName, gbc);
        txtFirstName.setFont(new Font("Arial", Font.PLAIN, 14));
        lblFirstName.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblLastName = new JLabel("Nom:");
        txtLastName = new JTextField(15);
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelPatient.add(lblLastName, gbc);
        
        gbc.gridx = 1; gbc.gridy = 2; panelPatient.add(txtLastName, gbc);
        txtLastName.setFont(new Font("Arial", Font.PLAIN, 14));
        lblLastName.setFont(new Font("Arial", Font.PLAIN, 14));


        JLabel lblTension = new JLabel("Tension Artérielle (mmHg):");
        lblTension.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelPatient.add(lblTension, gbc);

        txtTension = new JTextField(15);
        txtTension.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelPatient.add(txtTension, gbc);

        JLabel lblTemperature = new JLabel("Température (°C):");
        lblTemperature.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelPatient.add(lblTemperature, gbc);

        txtTemperature = new JTextField(15);
        txtTemperature.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1; gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelPatient.add(txtTemperature, gbc);

        JLabel lblFrequenceCardiaque = new JLabel("Fréquence Cardiaque (bpm):");
        lblFrequenceCardiaque.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelPatient.add(lblFrequenceCardiaque, gbc);

        txtFrequenceCardiaque = new JTextField(15);
        txtFrequenceCardiaque.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelPatient.add(txtFrequenceCardiaque, gbc);
        
        

        JButton btnEnvoyer = new JButton("Envoyer");
        btnEnvoyer.setFont(new Font("Arial", Font.BOLD, 14));
        btnEnvoyer.setFocusPainted(false);
        btnEnvoyer.addActionListener(e -> addMedicalData());
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPatient.add(btnEnvoyer, gbc);
    }
    private JTable historyTable;
    private DefaultTableModel tableModel;

    private void createHistoriquePanel() {
        panelHistorique = new JPanel(new BorderLayout());

        String[] columnNames = {"Prénom", "Nom", "Date", "Tension", "Température", "Fréquence Cardiaque"};
        tableModel = new DefaultTableModel(columnNames, 0);
        historyTable = new JTable(tableModel);

        TableCellRenderer renderer = new MedicalDataCellRenderer();
        historyTable.getColumnModel().getColumn(3).setCellRenderer(renderer); 
        historyTable.getColumnModel().getColumn(4).setCellRenderer(renderer); 
        historyTable.getColumnModel().getColumn(5).setCellRenderer(renderer); 

        JScrollPane scrollPane = new JScrollPane(historyTable);

        JPanel searchPanel = new JPanel();
        JTextField txtSearchLastName = new JTextField(10);
        JButton btnSearch = new JButton("Rechercher");
        btnSearch.addActionListener(e -> searchPatientHistory(txtSearchLastName.getText().trim()));

        searchPanel.add(new JLabel("Nom:"));
        searchPanel.add(txtSearchLastName);
        searchPanel.add(btnSearch);

        panelHistorique.add(searchPanel, BorderLayout.NORTH);
        panelHistorique.add(scrollPane, BorderLayout.CENTER);
        loadAllPatientHistory();
    }


    private void createaddpatientPanel() {
        panelAjoutPatient = new JPanel(new GridBagLayout());
        panelAjoutPatient.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        
        JLabel lblTitle = new JLabel("Ajout d'un nouveau patient", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        panelAjoutPatient.add(lblTitle, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        
        addFormField("Nom:", txtLastNamee = new JTextField(15), gbc);
        addFormField("Prénom:", txtFirstNamee = new JTextField(15), gbc);
        addFormField("Date de Naissance (YYYY-MM-DD):", txtDateNaissance = new JTextField(15), gbc);
        addFormField("Adresse:", txtAdresse = new JTextField(15), gbc);
        addFormField("Téléphone:", txtTelephone = new JTextField(15), gbc);
        addFormField("Email:", txtEmail = new JTextField(15), gbc);
        addFormField("Nom d'utilisateur:", txtLogin = new JTextField(15), gbc);
        addFormField("Mot de passe:", txtPassword = new JPasswordField(15), gbc);
        
        JLabel lblSexe = new JLabel("Sexe:");
        gbc.gridx = 0;
        gbc.gridy++;
        panelAjoutPatient.add(lblSexe, gbc);
        
        rbMale = new JRadioButton("M");
        rbFemale = new JRadioButton("F");
        ButtonGroup groupSexe = new ButtonGroup();
        groupSexe.add(rbMale);
        groupSexe.add(rbFemale);
        JPanel panelSexe = new JPanel();
        panelSexe.add(rbMale);
        panelSexe.add(rbFemale);
        
        gbc.gridx = 1;
        panelAjoutPatient.add(panelSexe, gbc);
        
        JButton btnEnvoyer = new JButton("Envoyer");
        btnEnvoyer.setFont(new Font("Arial", Font.BOLD, 14));
        btnEnvoyer.setFocusPainted(false);
        btnEnvoyer.addActionListener(e -> addNewPatient());
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panelAjoutPatient.add(btnEnvoyer, gbc);
    }

    private void addFormField(String labelText, JTextField textField, GridBagConstraints gbc) {
        JLabel label = new JLabel(labelText, SwingConstants.RIGHT);
        gbc.gridx = 0;
        panelAjoutPatient.add(label, gbc);
        
        gbc.gridx = 1;
        panelAjoutPatient.add(textField, gbc);
        
        gbc.gridy++;
    }
    private void addNewPatient() {
        try {
            String nom = txtLastNamee.getText().trim();
            String prenom = txtFirstNamee.getText().trim();
            String dateNaissance = txtDateNaissance.getText().trim(); 
            String sexe = (rbMale.isSelected()) ? "M" : "F"; 
            String adresse = txtAdresse.getText().trim();
            String telephone = txtTelephone.getText().trim();
            String email = txtEmail.getText().trim();
            String loginp = txtLogin.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();
            if (nom.isEmpty() || prenom.isEmpty() || dateNaissance.isEmpty() || adresse.isEmpty() || telephone.isEmpty() || email.isEmpty() || loginp.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "❌ Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean success = service.ajouterPatient(nom, prenom, dateNaissance, sexe, adresse, telephone, email, loginp, password);

            if (success) {
                JOptionPane.showMessageDialog(this, "✅ Patient ajouté avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "❌ Erreur lors de l'ajout du patient.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "❌ Erreur de connexion au serveur.", "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void loadAllPatientHistory() {
        new SwingWorker<Void, Object[]>() {
            @Override
            protected Void doInBackground() throws Exception {
                System.out.println("🔄 Chargement de l'historique en arrière-plan...");

                String query = "SELECT p.prenom, p.nom, d.date_heure, d.tension, d.temperature, d.frequence_cardiaque " +
                               "FROM Patient p " +
                               "JOIN DonneesMedicales d ON p.id_patient = d.id_patient " +
                               "ORDER BY d.date_heure DESC";

                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(query);
                     ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        Object[] rowData = {
                            rs.getString("prenom"),
                            rs.getString("nom"),
                            rs.getTimestamp("date_heure"),
                            rs.getDouble("tension"),
                            rs.getDouble("temperature"),
                            rs.getInt("frequence_cardiaque")
                        };
                        publish(rowData); 
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void process(List<Object[]> chunks) {
                tableModel.setRowCount(0); 
                for (Object[] rowData : chunks) {
                    tableModel.addRow(rowData);
                }
                System.out.println("✅ Historique mis à jour !");
            }
        }.execute();
    }




    private void searchPatientHistory(String lastName) {
        try {
            List<InfoClient> filteredData = service.searchHealthDataByLastName(lastName);  

            tableModel.setRowCount(0);

            for (InfoClient data : filteredData) {
                tableModel.addRow(new Object[]{
                    data.getPrenom(),
                    data.getNom(),
                    data.getDate(),
                    data.getTension(),
                    data.getTemperature(),
                    data.getFrequenceCardiaque()
                });
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "❌ Erreur lors de la recherche.", "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

  

    private void showPanel(String name) {
        if (!SessionManager.isLoggedIn() && !name.equals("Login") && !name.equals("SignUp")) {
            JOptionPane.showMessageDialog(this, "Vous devez être connecté pour accéder à cette section.", "Accès refusé", JOptionPane.WARNING_MESSAGE);
            return;
        }

        System.out.println("✅ Switching to panel: " + name);
        CardLayout layout = (CardLayout) panelMain.getLayout();
        layout.show(panelMain, name);
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
    private void performLogin(ActionEvent e) {
        String username = txtUsernameLogin.getText().trim();
        String password = new String(txtPasswordLogin.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs!", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Interserv service = (Interserv) java.rmi.Naming.lookup("HealthMonitorService");
            Doctor doctor = service.loginDoctor(username, password);

            if (doctor != null) {
                SessionManager.setDoctor(doctor.getId(), doctor.getNom());

                JOptionPane.showMessageDialog(this, "Bienvenue Dr. " + doctor.getNom(), "Connexion réussie", JOptionPane.INFORMATION_MESSAGE);
                
                try {
                    DoctorListener listener = new DoctorListenerImpl(this); 
                    service.registerDoctorListener(listener);
                    System.out.println("✅ Doctor registered for real-time alerts!");
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }

                toggleMenuBar(true);
                showPanel("Accueil");
            } else {
                JOptionPane.showMessageDialog(this, "Nom d'utilisateur ou mot de passe incorrect!", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur de connexion au serveur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }


 
    private void addMedicalData() {
        new Thread(() -> {
            try {
                Interserv service = (Interserv) java.rmi.Naming.lookup("HealthMonitorService");

                String firstName = txtFirstName.getText().trim();
                String lastName = txtLastName.getText().trim();
                double tension = Double.parseDouble(txtTension.getText().trim());
                double temperature = Double.parseDouble(txtTemperature.getText().trim());
                int frequenceCardiaque = Integer.parseInt(txtFrequenceCardiaque.getText().trim());

                int patientId = service.getPatientId(firstName, lastName);
                if (patientId == -1) {
                    JOptionPane.showMessageDialog(this, "Patient introuvable!", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean success = service.envoyerDonnees(patientId, tension, temperature, frequenceCardiaque);
                if (success) {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(this, "Données médicales ajoutées avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                        loadAllPatientHistory(); 
                    });
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout des données.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() -> 
                    JOptionPane.showMessageDialog(this, "Erreur: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE)
                );
            }
        }).start();
    }
    public void receiveAlert(String message) throws RemoteException {
        SwingUtilities.invokeLater(() -> 
            JOptionPane.showMessageDialog(this, "🚨 Alerte reçue :\n" + message, 
                                          "Alerte Médicale", JOptionPane.WARNING_MESSAGE)
        );
    }

    
    public static void main(String[] args) {
    	 try {
    		 UIManager.setLookAndFeel(new FlatDarkLaf()); 
         } catch (Exception e) {
             e.printStackTrace();
         }
    	 
        SwingUtilities.invokeLater(() -> new DoctorGUI().setVisible(true));
    }

    }
    class MedicalDataCellRenderer extends DefaultTableCellRenderer {
    	  
			private static final long serialVersionUID = 1L;

			@Override
    	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    	        cell.setForeground(Color.BLACK);
    	        String columnName = table.getColumnName(column);
    	        if (value instanceof Number) {
    	            double numValue = ((Number) value).doubleValue();
    	            if ((columnName.equals("Tension") && numValue > 140) ||
    	                (columnName.equals("Température") && numValue > 38.0) ||
    	                (columnName.equals("Fréquence Cardiaque") && numValue > 100)||
    	                (columnName.equals("Température") && numValue <27) ||
    	                (columnName.equals("Fréquence Cardiaque") && numValue > 50)){
    	                cell.setForeground(Color.RED);
    	            }
    	        }
    	       return cell;
    	    }
    	




    
}
