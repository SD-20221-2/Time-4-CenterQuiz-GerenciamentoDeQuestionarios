/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package home08;

/**
 *
 * @author mathe
 */
public class Banco {
    
    public String credito(double saldo) {
        String resposta;
        if (saldo <= 200) {
            resposta = "Saldo Medio: " + saldo + "\nNenhum Credito";
        } else if (saldo <= 400) {
            resposta = "Saldo Medio: " + saldo + "\nValor do Credito: "
                    + saldo * 0.2;
        } else if (saldo <= 600) {
            resposta = "Saldo Medio: " + saldo + "\nValor do Credito: "
                    + saldo * 0.3;
        } else {
            resposta = "Saldo Medio: " + saldo + "\nValor do Credito: "
                    + saldo * 0.4;
        }
        return resposta;
    }
}
