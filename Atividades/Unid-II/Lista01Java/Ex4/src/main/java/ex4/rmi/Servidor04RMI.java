/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex4.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import home04.PesoIdealInterfaceRMI;

/**
 *
 * @author mathe
 */
public class Servidor04RMI implements PesoIdealInterfaceRMI {
    public double calcular(double altura, String sexo) {
        return sexo.equals("M")
                ? (72.7 * altura) - 58
                : (62.1 * altura) - 44.7;
    }
    
    public static void main(String[] args) {
        try {
            Servidor04RMI obj = new Servidor04RMI();
            
            // O "stub" esconde a serialização dos parâmetros e toda a
            // comunicação a nível de rede, com o objetivo de simplificar
            // o mecanismo de realização da chamada.
            PesoIdealInterfaceRMI stub = (PesoIdealInterfaceRMI) UnicastRemoteObject.exportObject(obj, 0);
            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("pesoIdeal", stub);

            System.out.println("ServidorRMI vinculado no registro");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
