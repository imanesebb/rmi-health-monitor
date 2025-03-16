package medbd;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private static Integer loggedInPatientId = null;
    private static String loggedInPatientName = null;
    private static Integer loggedInDoctorId = null;
    private static String loggedInDoctorName = null;
    private static Map<Integer, Boolean> activeSessions = new HashMap<>();
    public static void setPatient(int patientId, String username) {
        loggedInPatientId = patientId;
        loggedInPatientName = username;
        activeSessions.put(patientId, true); 

        System.out.println("Session  du patient" + patientId);
    }


    public static int getLoggedInPatientId() {
        if (loggedInPatientId == null) {
            System.out.println("pas de patients connecté :((((");
            return -1; 
        }
        return loggedInPatientId;
    }

    public static boolean isPatientLoggedIn() {
        boolean status = loggedInPatientId != null && activeSessions.getOrDefault(loggedInPatientId, false);
        System.out.println(" test de connexion " + status);
        return status;
    }


    public static void logoutPatient() {
        if (loggedInPatientId != null) {
            activeSessions.remove(loggedInPatientId);
            System.out.println("déco:3 " + loggedInPatientId);
            loggedInPatientId = null;
            loggedInPatientName = null;
        }
    }
    public static void setDoctor(int doctorId, String doctorName) {
        loggedInDoctorId = doctorId;
        loggedInDoctorName = doctorName;
        activeSessions.put(doctorId, true);
    }


    public static boolean isDoctorLoggedIn() {
        return loggedInDoctorId != null && activeSessions.getOrDefault(loggedInDoctorId, false);
    }

    public static void logoutDoctor() {
        if (loggedInDoctorId != null) {
            activeSessions.remove(loggedInDoctorId);
            loggedInDoctorId = null;
            loggedInDoctorName = null;
        }
    }

    public static int getLoggedInDoctorId() {
        return loggedInDoctorId != null ? loggedInDoctorId : -1;
    }

    public static void logout() {
        logoutPatient();
        logoutDoctor();
    }

    public static boolean isLoggedIn() {
        return isPatientLoggedIn() || isDoctorLoggedIn();
    }
}
