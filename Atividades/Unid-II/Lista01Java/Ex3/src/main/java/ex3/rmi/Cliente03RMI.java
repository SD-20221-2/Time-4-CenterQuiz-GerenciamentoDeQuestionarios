/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex3.rmi;

import home03.NotasInterfaceRMI;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author mathe
 */
public class Cliente03RMI {

    public static void main(String[] args) {

        System.out.println("Conectado!");

        //double saldo = Double.parseDouble(saldoField.getText());
        NotasInterfaceRMI obj = null;
        try {
            Registry registry = LocateRegistry.getRegistry();
            obj = (NotasInterfaceRMI) registry.lookup("InterfaceRMI");

            String nome = JOptionPane.showInputDialog(
                    null,
                    "Digite o nome",
                    "Nome",
                    JOptionPane.QUESTION_MESSAGE
            );
            double n1 = Double.parseDouble(
                    JOptionPane.showInputDialog(
                            null,
                            "Digite a n1",
                            "Nota 1",
                            JOptionPane.QUESTION_MESSAGE
                    )
            );
            double n2 = Double.parseDouble(
                    JOptionPane.showInputDialog(
                            null,
                            "Digite a n2",
                            "Nota 2",
                            JOptionPane.QUESTION_MESSAGE
                    )
            );
            double n3 = Double.parseDouble(
                    JOptionPane.showInputDialog(
                            null,
                            "Digite a n3",
                            "Nota 3",
                            JOptionPane.QUESTION_MESSAGE
                    )
            );

            JOptionPane.showMessageDialog(null, "Olá " + nome
                    + (obj.verificarAprovado(n1, n2, n3)
                    ? "\nvocê está aprovado!"
                    : "\nvocê não está aprovado!")
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
