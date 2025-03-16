package medbd;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Interserv extends Remote {
    Doctor loginDoctor(String username, String password) throws RemoteException;
    int getPatientId(String firstName, String lastName) throws RemoteException;
	boolean envoyerDonnees(int patientId, double tension, double temperature, int frequenceCardiaque)
			throws RemoteException;
	boolean ajouterPatient(String nom, String prenom, String dateNaissance, String sexe, String adresse,
			String telephone, String email, String loginp, String password) throws RemoteException;
	List<InfoClient> getAllHealthData() throws RemoteException;
	List<InfoClient> searchHealthDataByLastName(String lastName) throws RemoteException;
	boolean registerDoctor(String firstName, String lastName, String specialty, String phone, String email,
			String username, String password) throws RemoteException;
	boolean isPatientLoggedIn(int patientId) throws RemoteException;
	void logoutPatient(int patientId) throws RemoteException;
	int performLogin(String username, String password) throws RemoteException;
	boolean envoyerDonneesMedicales(int patientId, double tension, double temperature, int frequenceCardiaque)
			throws RemoteException;
	int loginPatient(String username, String password) throws RemoteException;
	List<InfoClient> getPatientHistory(int patientId) throws RemoteException;
    void registerDoctorListener(DoctorListener listener) throws RemoteException;
    void registerPatientForAlerts(int patientId, ClientCallback callback) throws RemoteException;
    public List<Integer> getAllDoctorsForPatient(int patientId) throws RemoteException;

}