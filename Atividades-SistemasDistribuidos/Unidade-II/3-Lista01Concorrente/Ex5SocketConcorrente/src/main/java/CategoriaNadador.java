/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author mathe
 */
public class CategoriaNadador {
    public String obterCategoria(int idade) {
        if (idade >= 5 && idade <= 7) {
            return "infantil A";
        } else if (idade >= 8 && idade <= 10) {
            return "infantil B";
        } else if (idade >= 11 && idade <= 13) {
            return "juvenil A";
        } else if (idade >= 14 && idade <= 17) {
            return "juvenil B";
        } else if (idade >= 18) {
            return "adulta";
        } else {
            return "sem categoria";
        }
    }
}
