/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package home06;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author mathe
 */
public interface SalarioInterfaceRMI extends Remote {
    public double liquido(
            String nivel,
            double salarioBruto,
            int numeroDependentes
    ) throws RemoteException;
}
