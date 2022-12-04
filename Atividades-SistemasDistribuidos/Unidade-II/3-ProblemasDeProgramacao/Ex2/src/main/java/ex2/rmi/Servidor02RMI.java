/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import home02.MaioridadeInterfaceRMI;

/**
 *
 * @author mathe
 */
public class Servidor02RMI implements MaioridadeInterfaceRMI {
    public boolean verificar(String sexo, int idade) {
        if (sexo.equals("M")) {
            return idade >= 18;
        } else if (sexo.equals("F")) {
            return idade >= 21;
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        try {
            Servidor02RMI obj = new Servidor02RMI();
            MaioridadeInterfaceRMI stub = (MaioridadeInterfaceRMI) UnicastRemoteObject.exportObject(obj, 0);
            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("InterfaceRMI", stub);

            System.out.println("ServidorRMI vinculado no registro");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
