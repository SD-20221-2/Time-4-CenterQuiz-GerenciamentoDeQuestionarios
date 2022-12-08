/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex9.rmi;

import home09.BaralhoInterfaceRMI;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author mathe
 */
public class Cliente09RMI {

    public static void main(String[] args) {

        System.out.println("Conectado!");

        //double saldo = Double.parseDouble(saldoField.getText());
        BaralhoInterfaceRMI obj = null;
        try {
            Registry registry = LocateRegistry.getRegistry();
            obj = (BaralhoInterfaceRMI) registry.lookup("InterfaceRMI");

            int valor;
            valor = Integer.parseInt(JOptionPane.showInputDialog(
                    null,
                    "Digite o valor da carta",
                    "Valor",
                    JOptionPane.QUESTION_MESSAGE
            ));

            int naipe;
            naipe = Integer.parseInt(JOptionPane.showInputDialog(
                    null,
                    "Digite o naipe da carta",
                    "Naipe",
                    JOptionPane.QUESTION_MESSAGE
            ));

            String nomeCarta = obj.nomeCarta(valor, naipe);
            JOptionPane.showMessageDialog(null,
                    "Nome da carta: " + nomeCarta
            );

        } catch (Exception ex) {
            System.out.println("ClienteAplicativo exception: "
                    + ex.getMessage());
            ex.printStackTrace();
        }

        ////////////////////////////////////////////////////////////////////////
        System.out.println("Fechando RMI e Terminando o Programa.");
    }
}
