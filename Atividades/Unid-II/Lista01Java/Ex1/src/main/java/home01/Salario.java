package home01;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author mathe
 */
public class Salario {
    
    public double reajustar(String cargo, double salario) {
        if (cargo.toLowerCase().equals("operador")) {
            return salario * 1.2;
        } else if (cargo.toLowerCase().equals("programador")) {
            return salario * 1.18;
        } else {
            return salario;
        }
    }
}
