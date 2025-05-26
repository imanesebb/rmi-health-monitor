package medbd;
import java.io.Serializable;

import java.time.LocalDateTime;

public class InfoClient implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String prenom;
    private String nom;
    private LocalDateTime date;
    private float tension;
    private float temperature;
    private int frequenceCardiaque;

    public InfoClient(String prenom, String nom, LocalDateTime date, float tension, float temperature, int frequenceCardiaque) {
        this.prenom = prenom;
        this.nom = nom;
        this.date = date;
        this.tension = tension;
        this.temperature = temperature;
        this.frequenceCardiaque = frequenceCardiaque;
    }


	public String getPrenom() { return prenom; }
    public String getNom() { return nom; }
    public LocalDateTime getDate() { return date; }
    public float getTension() { return tension; }
    public float getTemperature() { return temperature; }
    public int getFrequenceCardiaque() { return frequenceCardiaque; }


	
}
