/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex7.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;
import home07.AposentadoriaInterfaceRMI;

/**
 *
 * @author mathe
 */
public class Cliente07RMI {

    public static void main(String[] args) {

        System.out.println("Conectado!");

        //double saldo = Double.parseDouble(saldoField.getText());
        AposentadoriaInterfaceRMI obj = null;
        try {
            Registry registry = LocateRegistry.getRegistry();
            obj = (AposentadoriaInterfaceRMI) registry.lookup("InterfaceRMI");

            String nome = JOptionPane.showInputDialog(
                    null,
                    "Digite o nome",
                    "Nome",
                    JOptionPane.QUESTION_MESSAGE
            );

            int idade;
            idade = Integer.parseInt(JOptionPane.showInputDialog(
                    null,
                    "Digite a idade",
                    "Idade",
                    JOptionPane.QUESTION_MESSAGE
            ));

            int tempoServico = Integer.parseInt(JOptionPane.showInputDialog(
                    null,
                    "Digite o tempo de serviço",
                    "Tempo de Serviço",
                    JOptionPane.QUESTION_MESSAGE
            ));

            boolean aposentar = obj.verificar(idade, tempoServico);
            JOptionPane.showMessageDialog(null, "Olá " + nome
                    + (aposentar
                            ? "\nvocê pode se aponsentar!"
                            : "\nvocê ainda não pode se aposentar!")
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
