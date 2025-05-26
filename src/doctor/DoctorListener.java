package medbd;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DoctorListener extends Remote {
    void receiveAlert(String message) throws RemoteException;
}
