/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex5.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import home05.CategoriaNadadorInterfaceRMI;

/**
 *
 * @author mathe
 */
public class Servidor05RMI implements CategoriaNadadorInterfaceRMI {
    public String obterCategoria(int idade) {
        if (idade >= 5 && idade <= 7) {
            return "infantil A";
        } else if (idade >= 8 && idade <= 10) {
            return "infantil B";
        } else if (idade >= 11 && idade <= 13) {
            return "juvenil A";
        } else if (idade >= 14 && idade <= 17) {
            return "juvenil B";
        } else if (idade >= 18) {
            return "adulta";
        } else {
            return "sem categoria";
        }
    }
    
    public static void main(String[] args) {
        try {
            Servidor05RMI obj = new Servidor05RMI();
            CategoriaNadadorInterfaceRMI stub = (CategoriaNadadorInterfaceRMI) UnicastRemoteObject.exportObject(obj, 0);
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
