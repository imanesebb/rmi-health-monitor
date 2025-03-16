package medbd;

import java.io.Serializable;

import java.rmi.RemoteException;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

public class ClientCallbackImpl implements ClientCallback, Serializable {
    private static final long serialVersionUID = 1L;
    private transient ClientGUI gui;

    public ClientCallbackImpl(ClientGUI gui) {
        this.gui = gui;
    }

    @Override
    public void receiveAlert(String alertMessage) throws RemoteException {
        SwingUtilities.invokeLater(() -> 
            JOptionPane.showMessageDialog(gui, alertMessage, "🚨 Medical Alert", JOptionPane.WARNING_MESSAGE)
        );
    }
}
