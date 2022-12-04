/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex6.rmi;

import home06.SalarioInterfaceRMI;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author mathe
 */
public class Servidor06RMI implements SalarioInterfaceRMI {
    public double liquido(
            String nivel,
            double salarioBruto,
            int numeroDependentes
    ) {
        if (nivel.equals("A")) {
            if (numeroDependentes == 0) {
                return salarioBruto * (1 - 0.03);
            } else {
                return salarioBruto * (1 - 0.08);
            }
        } else if (nivel.equals("B")) {
            if (numeroDependentes == 0) {
                return salarioBruto * (1 - 0.05);
            } else {
                return salarioBruto * (1 - 0.1);
            }
        } else if (nivel.equals("C")) {
            if (numeroDependentes == 0) {
                return salarioBruto * (1 - 0.08);
            } else {
                return salarioBruto * (1 - 0.15);
            }
        } else if (nivel.equals("D")) {
            if (numeroDependentes == 0) {
                return salarioBruto * (1 - 0.1);
            } else {
                return salarioBruto * (1 - 0.17);
            }
        } else {
            return salarioBruto;
        }
    }
    
    public static void main(String[] args) {
        try {
            Servidor06RMI obj = new Servidor06RMI();
            SalarioInterfaceRMI stub = (SalarioInterfaceRMI) UnicastRemoteObject.exportObject(obj, 0);
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
