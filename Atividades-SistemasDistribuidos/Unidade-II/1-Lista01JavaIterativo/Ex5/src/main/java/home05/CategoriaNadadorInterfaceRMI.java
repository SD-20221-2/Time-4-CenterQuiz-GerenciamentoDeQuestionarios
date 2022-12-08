/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package home05;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author mathe
 */
public interface CategoriaNadadorInterfaceRMI extends Remote {
    public String obterCategoria(int idade) throws RemoteException;
}
