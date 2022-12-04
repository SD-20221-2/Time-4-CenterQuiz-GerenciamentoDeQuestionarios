/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex7.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import home07.AposentadoriaInterfaceRMI;

/**
 *
 * @author mathe
 */
public class Servidor07RMI implements AposentadoriaInterfaceRMI {
    public boolean verificar(int idade, int tempoServiço) {
        return idade >= 65
                && tempoServiço >= 30
                && idade >= 60 && tempoServiço >= 25;
    }
    
    public static void main(String[] args) {
        try {
            Servidor07RMI obj = new Servidor07RMI();
            AposentadoriaInterfaceRMI stub = (AposentadoriaInterfaceRMI) UnicastRemoteObject.exportObject(obj, 0);
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
