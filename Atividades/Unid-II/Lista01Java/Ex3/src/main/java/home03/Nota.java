/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package home03;

import java.util.ArrayList;

/**
 *
 * @author mathe
 */
public class Nota {
    public boolean verificarAprovado(double n1, double n2, double n3) {
        double M = (n1 + n2) / 2;
        
        if (M >= 7.0) {
            return true;
        } else if (M > 3 && M < 7) {
            if ((M + n3) / 2 >= 5) {
                return true;
            }
        }
        
        return false;
    }
}
