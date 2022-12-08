/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package home09;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author mathe
 */
public interface BaralhoInterfaceRMI extends Remote {

    public String nomeCarta(int valor, int naipe) throws RemoteException;

    public String obterNaipeCarta() throws RemoteException;

    public String obterValorCarta() throws RemoteException;

    public int getValor() throws RemoteException;

    public void setValor(int valor) throws RemoteException;

    public int getNaipe() throws RemoteException;

    public void setNaipe(int naipe) throws RemoteException;
}
