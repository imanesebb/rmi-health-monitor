package medbd;

import java.io.Serializable;
import java.rmi.RemoteException;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

public class DoctorListenerImpl implements DoctorListener, Serializable {
    private static final long serialVersionUID = 1L;
    private transient DoctorGUI gui; 

    public DoctorListenerImpl(DoctorGUI gui) {
        this.gui = gui;
    }

    @Override
    public void receiveAlert(String alertMessage) throws RemoteException {
        SwingUtilities.invokeLater(() -> 
            JOptionPane.showMessageDialog(gui, "🚨 Alerte reçue :\n" + alertMessage, 
                                          "Alerte Médicale", JOptionPane.WARNING_MESSAGE)
        );
    }
}
