/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package home06;

/**
 *
 * @author mathe
 */
public class Salario {
    public double liquido(
            String nivel,
            double salarioBruto,
            int numeroDependentes
    ) {
        if (nivel.equals("A")) {
            if (numeroDependentes == 0) {
                return salarioBruto * (1 - 0.03);
            } else {
                return salarioBruto * (1 - 0.08);
            }
        } else if (nivel.equals("B")) {
            if (numeroDependentes == 0) {
                return salarioBruto * (1 - 0.05);
            } else {
                return salarioBruto * (1 - 0.1);
            }
        } else if (nivel.equals("C")) {
            if (numeroDependentes == 0) {
                return salarioBruto * (1 - 0.08);
            } else {
                return salarioBruto * (1 - 0.15);
            }
        } else if (nivel.equals("D")) {
            if (numeroDependentes == 0) {
                return salarioBruto * (1 - 0.1);
            } else {
                return salarioBruto * (1 - 0.17);
            }
        } else {
            return salarioBruto;
        }
    }
}
