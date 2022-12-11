/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author mathe
 */
public class PesoIdeal {

    public double calcular(double altura, String sexo) {
        return sexo.equals("M")
                ? (72.7 * altura) - 58
                : (62.1 * altura) - 44.7;
    }
}
