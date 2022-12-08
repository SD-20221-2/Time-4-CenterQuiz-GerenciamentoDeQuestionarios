/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package home02;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author mathe
 */
public interface MaioridadeInterfaceRMI extends Remote{
    public boolean verificar(String sexo, int idade) throws RemoteException;
}
