/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex6.rmi;

import home06.SalarioInterfaceRMI;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author mathe
 */
public class Cliente06RMI {

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
            String[] niveis = {"A", "B", "C", "D"};
            String nivel = (String) JOptionPane.showInputDialog(
                    null,
                    "Digite o nível",
                    "Nível",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    niveis,
                    niveis[0]
            );
            double salarioBruto = Double.parseDouble(JOptionPane.showInputDialog(
                    null,
                    "Digite o seu salário bruto",
                    "Salário bruto",
                    JOptionPane.QUESTION_MESSAGE
            ));

            int numeroDependentes = Integer.parseInt(JOptionPane.showInputDialog(
                    null,
                    "Digite o número de dependentes",
                    "Número de dependentes",
                    JOptionPane.QUESTION_MESSAGE
            ));

            double salarioLiquido = obj.liquido(nivel, salarioBruto, numeroDependentes);
            DecimalFormat df = new DecimalFormat("#.00");
            JOptionPane.showMessageDialog(null, "Olá " + nome
                    + "\nVocê pertence ao nível \"" + nivel
                    + "\" e seu salário líquido é = R$ " + df.format(salarioLiquido)
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
