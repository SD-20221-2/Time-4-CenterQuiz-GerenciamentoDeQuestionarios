/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex1.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import home01.SalarioInterfaceRMI;

/**
 *
 * @author mathe
 */
public class Servidor01RMI implements SalarioInterfaceRMI {
    public double reajustarSalario(String cargo, double salario) {
        if (cargo.toLowerCase().equals("operador")) {
            return salario * 1.2;
        } else if (cargo.toLowerCase().equals("programador")) {
            return salario * 1.18;
        } else {
            return salario;
        }
    }
    
    public static void main(String[] args) {
        try {
            Servidor01RMI obj = new Servidor01RMI();
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
