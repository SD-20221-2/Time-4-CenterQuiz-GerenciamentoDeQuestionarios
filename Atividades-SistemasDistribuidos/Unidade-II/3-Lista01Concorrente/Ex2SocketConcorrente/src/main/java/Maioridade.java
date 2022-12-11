/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author mathe
 */
public class Maioridade {
    public boolean verificar(String sexo, int idade) {
        if (sexo.equals("M")) {
            return idade >= 18;
        } else if (sexo.equals("F")) {
            return idade >= 21;
        }
        
        return false;
    }
}
