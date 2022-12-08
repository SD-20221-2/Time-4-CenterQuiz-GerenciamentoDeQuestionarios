/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex1.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import home01.SalarioInterfaceRMI;

/**
 *
 * @author mathe
 */
public class Cliente01RMI {

    public static void main(String[] args) {

        System.out.println("Conectado!");

        //double saldo = Double.parseDouble(saldoField.getText());
        SalarioInterfaceRMI obj = null;
        try {
            Registry registry = LocateRegistry.getRegistry();
            obj = (SalarioInterfaceRMI) registry.lookup("InterfaceRMI");

            String nome = JOptionPane.showInputDialog(
                    null,
                    "Digite o nome",
                    "Nome",
                    JOptionPane.QUESTION_MESSAGE
            );
            String cargo = JOptionPane.showInputDialog(
                    null,
                    "Digite o cargo",
                    "Cargo",
                    JOptionPane.QUESTION_MESSAGE
            );
            double salario = Double.parseDouble(
                    JOptionPane.showInputDialog(
                            null,
                            "Digite o salario",
                            "Salário",
                            JOptionPane.QUESTION_MESSAGE
                    )
            );

            JOptionPane.showMessageDialog(
                    null,
                    "Olá " + nome +
                    "\nSeu salário reajustado é R$ " + Double.toString(obj.reajustarSalario(cargo, salario))
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
