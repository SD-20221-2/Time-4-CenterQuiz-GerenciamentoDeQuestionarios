/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package home03;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author mathe
 */
public interface NotasInterfaceRMI extends Remote{
        public boolean verificarAprovado(double n1, double n2, double n3) throws RemoteException;

}
