package medbd;

import java.time.LocalDate;

public class Client {
    private int id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private char sexe;
    private String adresse;
    private String telephone;
    private String email;
    private String login;
    private String password; 

    public Client(int id, String nom, String prenom, LocalDate localDate, char sexe, String adresse, 
                  String telephone, String email, String login, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = localDate;
        this.sexe = sexe;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public char getSexe() {
        return sexe;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
