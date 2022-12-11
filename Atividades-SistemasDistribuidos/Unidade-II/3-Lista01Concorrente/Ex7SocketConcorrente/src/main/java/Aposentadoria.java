/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author mathe
 */
public class Aposentadoria {
    public boolean verificar(int idade, int tempoServiço) {
        return idade >= 65
                && tempoServiço >= 30
                && idade >= 60 && tempoServiço >= 25;
    }
}
