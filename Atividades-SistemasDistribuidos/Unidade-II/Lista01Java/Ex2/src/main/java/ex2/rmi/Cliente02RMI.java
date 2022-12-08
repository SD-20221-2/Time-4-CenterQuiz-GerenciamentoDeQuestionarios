/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import home02.MaioridadeInterfaceRMI;

/**
 *
 * @author mathe
 */
public class Cliente02RMI {
    
    public static void main(String[] args) {
        
        System.out.println("Conectado!");

        //double saldo = Double.parseDouble(saldoField.getText());
        MaioridadeInterfaceRMI obj = null;
        try {
            Registry registry = LocateRegistry.getRegistry();
            obj = (MaioridadeInterfaceRMI) registry.lookup("InterfaceRMI");
            
            String nome = JOptionPane.showInputDialog(
                    null,
                    "Digite o nome",
                    "Nome",
                    JOptionPane.QUESTION_MESSAGE
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
            
            int idade;
            idade = Integer.parseInt(JOptionPane.showInputDialog(
                    null,
                    "Digite a idade",
                    "Idade",
                    JOptionPane.QUESTION_MESSAGE
            ));
            
            JOptionPane.showMessageDialog(null, "Olá " + nome
                    + (obj.verificar(sexo, idade)
                    ? "\nvocê atingiu a maioridade!"
                    : "\nvocê não atingiu a maioridade!")
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
