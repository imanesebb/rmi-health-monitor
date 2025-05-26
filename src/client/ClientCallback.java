package medbd;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote {
    void receiveAlert(String alertMessage) throws RemoteException;
}
