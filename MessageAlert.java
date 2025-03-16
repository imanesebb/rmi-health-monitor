package medbd;

import java.sql.Timestamp;

public class MessageAlert {
    private int idAlerte;
    private int idPatient;
    private int idMedecin;
    private String message;
    private Timestamp dateHeure;

    public MessageAlert(int idAlerte, int idPatient, int idMedecin, String message, Timestamp dateHeure) {
        this.idAlerte = idAlerte;
        this.idPatient = idPatient;
        this.idMedecin = idMedecin;
        this.message = message;
        this.dateHeure = dateHeure;
    }

    public int getIdAlerte() { return idAlerte; }
    public int getIdPatient() { return idPatient; }
    public int getIdMedecin() { return idMedecin; }
    public String getMessage() { return message; }
    public Timestamp getDateHeure() { return dateHeure; }
}

