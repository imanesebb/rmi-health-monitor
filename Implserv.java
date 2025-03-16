package medbd;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Implserv extends UnicastRemoteObject implements Interserv {

	
	private static final long serialVersionUID = 1L;
	private Map<Integer, String> patientSessions = new HashMap<>(); 
	private Map<Integer, ClientCallback> patientCallbacks = new HashMap<>();  


    private final List<DoctorListener> doctorListeners = new ArrayList<>();
	protected Implserv() throws RemoteException {
        super();
    }

	@Override
	public Doctor loginDoctor(String username, String password) throws RemoteException {
	    Doctor doctor = null;

	    String query = "SELECT id_medecin, nom, prenom FROM Medecin WHERE loginm = ? AND password = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setString(1, username);
	        stmt.setString(2, password); 

	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            int doctorId = rs.getInt("id_medecin");
	            String nom = rs.getString("nom");
	            String prenom = rs.getString("prenom");

	            doctor = new Doctor(doctorId, nom, prenom);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return doctor; }
	
   
	public List<Integer> getAllDoctorsForPatient(int patientId) throws RemoteException {
	    List<Integer> doctorIds = new ArrayList<>();
	    String query = "SELECT DISTINCT id_medecin FROM Alerte WHERE id_patient = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setInt(1, patientId);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            doctorIds.add(rs.getInt("id_medecin")); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return doctorIds; 
	}


         @Override
            public boolean registerDoctor(String firstName, String lastName, String specialty, String phone, String email, String username, String password) throws RemoteException {
                String query = "INSERT INTO Medecin (nom, prenom, specialite, telephone, email, loginm, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
                
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(query)) {

                    stmt.setString(1, lastName);
                    stmt.setString(2, firstName);
                    stmt.setString(3, specialty);
                    stmt.setString(4, phone);
                    stmt.setString(5, email);
                    stmt.setString(6, username);
                    stmt.setString(7, password);  

                    int rowsInserted = stmt.executeUpdate();
                    return rowsInserted > 0; 

                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }


            
			@Override
			public boolean envoyerDonnees(int patientId, double tension, double temperature, int frequenceCardiaque) throws RemoteException {
			    String query = "INSERT INTO DonneesMedicales (id_patient, tension, temperature, frequence_cardiaque, date_heure) VALUES (?, ?, ?, ?, ?)";
			    
			    try (Connection conn = DatabaseConnection.getConnection();
			         PreparedStatement stmt = conn.prepareStatement(query)) {

			        stmt.setInt(1, patientId);
			        stmt.setDouble(2, tension);
			        stmt.setDouble(3, temperature);
			        stmt.setInt(4, frequenceCardiaque);
			        stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

			        return stmt.executeUpdate() > 0;
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return false;
			    }
			}

			@Override
			public boolean ajouterPatient(String nom, String prenom, String dateNaissance, String sexe, String adresse, String telephone, String email, String loginp, String password) throws RemoteException {
			    String query = "INSERT INTO Patient (nom, prenom, date_naissance, sexe, adresse, telephone, email, loginp, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			    try (Connection conn = DatabaseConnection.getConnection();
			         PreparedStatement stmt = conn.prepareStatement(query)) {

			        stmt.setString(1, nom);
			        stmt.setString(2, prenom);
			        stmt.setDate(3, Date.valueOf(dateNaissance)); 
			        stmt.setString(4, sexe);
			        stmt.setString(5, adresse);
			        stmt.setString(6, telephone);
			        stmt.setString(7, email);
			        stmt.setString(8, loginp);
			        stmt.setString(9, password);

			        int rowsInserted = stmt.executeUpdate();
			        if (rowsInserted > 0) {
			            System.out.println("✅ Patient ajouté avec succès: " + prenom + " " + nom);
			            return true;
			        } else {
			            System.out.println("❌ Erreur lors de l'ajout du patient.");
			            return false;
			        }

			    } catch (SQLException e) {
			        e.printStackTrace();
			        return false;
			    }
			}


		
		    @Override
		    public int getPatientId(String firstName, String lastName) throws RemoteException {
		        String query = "SELECT id_patient FROM Patient WHERE nom = ? AND prenom = ?";
		        
		        try (Connection conn = DatabaseConnection.getConnection();
		             PreparedStatement stmt = conn.prepareStatement(query)) {

		            stmt.setString(1, lastName);
		            stmt.setString(2, firstName);
		            
		            ResultSet rs = stmt.executeQuery();
		            if (rs.next()) {
		                return rs.getInt("id_patient"); 
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return -1; 
		    }
		    @Override
		    public List<InfoClient> getAllHealthData() throws RemoteException {
		        List<InfoClient> history = new ArrayList<>(); 

		        String query = "SELECT p.prenom, p.nom, d.date_heure, d.tension, d.temperature, d.frequence_cardiaque " +
		                       "FROM Patient p " +
		                       "JOIN DonneesMedicales d ON p.id_patient = d.id_patient " +
		                       "ORDER BY d.date_heure DESC";

		        try (Connection conn = DatabaseConnection.getConnection();
		             PreparedStatement stmt = conn.prepareStatement(query);
		             ResultSet rs = stmt.executeQuery()) {

		            while (rs.next()) {
		                history.add(new InfoClient( 
		                    rs.getString("prenom"),  
		                    rs.getString("nom"),    
		                    rs.getTimestamp("date_heure").toLocalDateTime(), 
		                    rs.getBigDecimal("tension").floatValue(),        
		                    rs.getBigDecimal("temperature").floatValue(),   
		                    rs.getInt("frequence_cardiaque")               
		                ));
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }

		        return history;
		    }

		    @Override
		    public List<InfoClient> searchHealthDataByLastName(String lastName) throws RemoteException {
		        List<InfoClient> history = new ArrayList<>();
		        String query = "SELECT p.prenom, p.nom, d.date_heure, d.tension, d.temperature, d.frequence_cardiaque " +
		                       "FROM Patient p " +
		                       "JOIN DonneesMedicales d ON p.id_patient = d.id_patient " +
		                       "WHERE p.nom LIKE ? " +
		                       "ORDER BY d.date_heure DESC";

		        try (Connection conn = DatabaseConnection.getConnection();
		             PreparedStatement stmt = conn.prepareStatement(query)) {

		            stmt.setString(1, "%" + lastName + "%"); 
		            ResultSet rs = stmt.executeQuery();

		            while (rs.next()) {
		            	history.add(new InfoClient(
		            		    rs.getString("prenom"),  
		            		    rs.getString("nom"),    
		            		    rs.getTimestamp("date_heure").toLocalDateTime(), 
		            		    rs.getBigDecimal("tension").floatValue(),       
		            		    rs.getBigDecimal("temperature").floatValue(),   
		            		    rs.getInt("frequence_cardiaque")       
		            		));

		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return history;
		    }
		    

		    @Override
		    public int performLogin(String username, String password) throws RemoteException {
		        String query = "SELECT id_patient, nom FROM Patient WHERE loginp = ? AND password = ?";

		        try (Connection conn = DatabaseConnection.getConnection();
		             PreparedStatement stmt = conn.prepareStatement(query)) {

		            stmt.setString(1, username);
		            stmt.setString(2, password);

		            ResultSet rs = stmt.executeQuery();
		            if (rs.next()) {
		                int patientId = rs.getInt("id_patient");
		                String patientName = rs.getString("nom");

		                patientSessions.put(patientId, patientName);
		                System.out.println("✅ Patient logged in: " + patientId + " (" + patientName + ")");

		                return patientId;
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return -1; 
		    }


		    public boolean isPatientLoggedIn(int patientId) throws RemoteException {
		        return patientSessions.containsKey(patientId);
		    }

		    public void logoutPatient(int patientId) throws RemoteException {
		        patientSessions.remove(patientId);
		        System.out.println("✅ Patient déconnecté !");
		    }
		  
		   
		    @Override
		    public boolean envoyerDonneesMedicales(int patientId, double tension, double temperature, int frequenceCardiaque) throws RemoteException {
		        String query = "INSERT INTO DonneesMedicales (id_patient, date_heure, tension, temperature, frequence_cardiaque) VALUES (?, ?, ?, ?, ?)";
		        boolean isCritical = false;
		        String alertMessage = "🚨 ALERT! Abnormal values detected for Patient " + patientId + ": ";

		        try (Connection conn = DatabaseConnection.getConnection();
		             PreparedStatement stmt = conn.prepareStatement(query)) {

		            stmt.setInt(1, patientId);
		            stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
		            stmt.setDouble(3, tension);
		            stmt.setDouble(4, temperature);
		            stmt.setInt(5, frequenceCardiaque);

		            int rowsInserted = stmt.executeUpdate();
		            if (rowsInserted > 0) {
		                System.out.println("✅ Données médicales ajoutées avec succès!");

		                if (tension < 90 || tension > 140) {
		                    alertMessage += "\n- Blood Pressure: " + tension + " mmHg";
		                    isCritical = true;
		                }
		                if (temperature < 35 || temperature > 39) {
		                    alertMessage += "\n- Temperature: " + temperature + "°C";
		                    isCritical = true;
		                }
		                if (frequenceCardiaque < 50 || frequenceCardiaque > 120) {
		                    alertMessage += "\n- Heart Rate: " + frequenceCardiaque + " bpm";
		                    isCritical = true;
		                }

		                if (isCritical) {
		                    System.out.println("⚠️ CRITICAL ALERT SENT TO DOCTOR & PATIENT!");
		                    notifyDoctorAndPatient(patientId, alertMessage);  
		                }

		                return true;
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return false;
		    }



		    @Override
		    public void registerDoctorListener(DoctorListener listener) throws RemoteException {
		        if (listener != null && !doctorListeners.contains(listener)) {
		            doctorListeners.add(listener);
		            System.out.println("✅ Doctor registered for real-time alerts!");
		        }
		    }

		   
		    @Override
		    public int loginPatient(String username, String password) throws RemoteException {
		        try (Connection conn = DatabaseConnection.getConnection()) {
		            String query = "SELECT id_patient, nom FROM Patient WHERE loginp = ? AND password = ?";
		            PreparedStatement stmt = conn.prepareStatement(query);
		            stmt.setString(1, username);
		            stmt.setString(2, password);
		            ResultSet rs = stmt.executeQuery();

		            if (rs.next()) {
		                int patientId = rs.getInt("id_patient");
		                String patientName = rs.getString("nom");

		                SessionManager.setPatient(patientId, patientName);
		                System.out.println("✅ Patient logged in: " + patientId + " (" + patientName + ")");
		                return patientId;
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return -1; 
		    }


               public List<InfoClient> getPatientHistory(int patientId) throws RemoteException {
		        List<InfoClient> history = new ArrayList<>();
		        String sql = "SELECT p.prenom, p.nom, d.date_heure, d.tension, d.temperature, d.frequence_cardiaque " +
		                     "FROM Patient p " +
		                     "JOIN DonneesMedicales d ON p.id_patient = d.id_patient " +
		                     "WHERE p.id_patient = ? " +
		                     "ORDER BY d.date_heure DESC";

		        try (Connection conn = DatabaseConnection.getConnection();
		             PreparedStatement stmt = conn.prepareStatement(sql)) {

		            stmt.setInt(1, patientId);
		            ResultSet rs = stmt.executeQuery();

		            while (rs.next()) {
		                history.add(new InfoClient(
		                    rs.getString("prenom"),  
		                    rs.getString("nom"),    
		                    rs.getTimestamp("date_heure").toLocalDateTime(),
		                    rs.getFloat("tension"), 
		                    rs.getFloat("temperature"),  
		                    rs.getInt("frequence_cardiaque") 
		                ));
		                System.out.println("✅ Found history: " + rs.getString("prenom") + " " + rs.getString("nom") + " at " + rs.getTimestamp("date_heure"));
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }

		        return history;
		    }

              


               @Override
               public void registerPatientForAlerts(int patientId, ClientCallback callback) throws RemoteException {
                   patientCallbacks.put(patientId, callback); 
                   System.out.println("✅ Patient " + patientId + " registered for real-time alerts!");
               }


               private void notifyDoctorAndPatient(int patientId, String alertMessage) throws RemoteException {
            	    for (DoctorListener listener : doctorListeners) {
            	        try {
            	            listener.receiveAlert(alertMessage);
            	        } catch (RemoteException e) {
            	            e.printStackTrace();
            	        }
            	    }
            	    
            	    if (patientCallbacks.containsKey(patientId)) {  
            	        ClientCallback patientCallback = patientCallbacks.get(patientId);
            	        if (patientCallback != null) {
            	            patientCallback.receiveAlert(alertMessage);
            	        }
            	    }

            	    System.out.println("✅ Alert sent to all doctors and Patient ID: " + patientId);
            	}




		    public static void main(String[] args) {
		        try {
		            Registry registry = LocateRegistry.createRegistry(1099);
		            System.out.println("✅ RMI Registry started on port 1099");

		            Implserv server = new Implserv();

		            registry.rebind("HealthMonitorService", server);
		            System.out.println("✅ Server is running and ready for requests!");

		        } catch (RemoteException e) {
		            e.printStackTrace();
		            System.out.println("❌ Error starting the RMI server: " + e.getMessage());
		        } catch (Exception e) {
		            e.printStackTrace();
		            System.out.println("❌ Unexpected error: " + e.getMessage());
		        }
		    }

			

			
		
           
        
    }

