/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex3.rmi;

import home03.NotasInterfaceRMI;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author mathe
 */
public class Servidor03RMI implements NotasInterfaceRMI {
    public boolean verificarAprovado(double n1, double n2, double n3) {
        double M = (n1 + n2) / 2;
        
        if (M >= 7.0) {
            return true;
        } else if (M > 3 && M < 7) {
            if ((M + n3) / 2 >= 5) {
                return true;
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        try {
            Servidor03RMI obj = new Servidor03RMI();
            NotasInterfaceRMI stub = (NotasInterfaceRMI) UnicastRemoteObject.exportObject(obj, 0);
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
