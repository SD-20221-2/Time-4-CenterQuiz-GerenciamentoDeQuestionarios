/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex8.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import home08.BancoInterfaceRMI;

/**
 *
 * @author mathe
 */
public class Servidor08RMI implements BancoInterfaceRMI {
    public String credito(double saldo) {
        String resposta;
        if (saldo <= 200) {
            resposta = "Saldo Medio: " + saldo + "\nNenhum Credito";
        } else if (saldo <= 400) {
            resposta = "Saldo Medio: " + saldo + "\nValor do Credito: "
                    + saldo * 0.2;
        } else if (saldo <= 600) {
            resposta = "Saldo Medio: " + saldo + "\nValor do Credito: "
                    + saldo * 0.3;
        } else {
            resposta = "Saldo Medio: " + saldo + "\nValor do Credito: "
                    + saldo * 0.4;
        }
        return resposta;
    }
    
    public static void main(String[] args) {
        try {
            Servidor08RMI obj = new Servidor08RMI();
            BancoInterfaceRMI stub = (BancoInterfaceRMI) UnicastRemoteObject.exportObject(obj, 0);
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
