package medbd;

import java.io.Serializable;

public class Doctor implements Serializable {
  
	private static final long serialVersionUID = 1L;
	private int id;
    private String nom;
    private String prenom;

    public Doctor(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
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
}
