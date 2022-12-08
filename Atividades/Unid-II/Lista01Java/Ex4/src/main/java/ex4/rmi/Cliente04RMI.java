/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex4.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;
import home04.PesoIdealInterfaceRMI;
import java.text.DecimalFormat;

/**
 *
 * @author mathe
 */
public class Cliente04RMI {

    public static void main(String[] args) {

        System.out.println("Conectado!");

        //double saldo = Double.parseDouble(saldoField.getText());
        PesoIdealInterfaceRMI obj = null;
        try {
            Registry registry = LocateRegistry.getRegistry();
            obj = (PesoIdealInterfaceRMI) registry.lookup("pesoIdeal");

            String nome = JOptionPane.showInputDialog(
                    null,
                    "Digite o nome",
                    "Nome",
                    JOptionPane.QUESTION_MESSAGE
            );

            double altura = Double.parseDouble(
                    JOptionPane.showInputDialog(
                            null,
                            "Digite sua altura",
                            "Altura",
                            JOptionPane.QUESTION_MESSAGE
                    )
            );

            String[] opcoes = {"M", "F"};
            String sexo = (String) JOptionPane.showInputDialog(
                    null,
                    "Digite o sexo",
                    "Sexo",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[1]
            );

            double pesoIdeal = obj.calcular(altura, sexo);
            DecimalFormat df = new DecimalFormat("#.000");
            JOptionPane.showMessageDialog(
                    null,
                    "Olá " + nome +
                    "\nSeu peso ideal é " + df.format(pesoIdeal)
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
