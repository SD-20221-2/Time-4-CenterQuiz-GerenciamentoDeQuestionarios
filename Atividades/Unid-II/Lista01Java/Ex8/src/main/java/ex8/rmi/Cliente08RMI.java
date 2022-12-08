/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex8.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;
import home08.BancoInterfaceRMI;

/**
 *
 * @author mathe
 */
public class Cliente08RMI {

    public static void main(String[] args) {

        System.out.println("Conectado!");

        //double saldo = Double.parseDouble(saldoField.getText());
        BancoInterfaceRMI obj = null;
        try {
            Registry registry = LocateRegistry.getRegistry();
            obj = (BancoInterfaceRMI) registry.lookup("InterfaceRMI");

            double saldo;
            saldo = Double.parseDouble(
                    JOptionPane.showInputDialog(null,
                            "Digite o Saldo Medio:",
                            "Saldo",
                            JOptionPane.QUESTION_MESSAGE
                    )
            );

            String valor = obj.credito(saldo);
            JOptionPane.showMessageDialog(null, valor);


        } catch (Exception ex) {
            System.out.println("ClienteAplicativo exception: "
                    + ex.getMessage());
            ex.printStackTrace();
        }

        ////////////////////////////////////////////////////////////////////////
        System.out.println("Fechando RMI e Terminando o Programa.");
    }
}
