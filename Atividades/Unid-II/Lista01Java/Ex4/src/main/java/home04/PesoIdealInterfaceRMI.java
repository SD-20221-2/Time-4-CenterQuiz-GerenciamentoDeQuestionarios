package home04;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author mathe
 */
public interface PesoIdealInterfaceRMI extends Remote {

    public double calcular(double altura, String sexo) throws RemoteException;
}
