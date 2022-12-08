/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package ex1.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author mathe
 *
 * Enunciado: Analise cada enunciado e separe o cliente do servidor. Por
 * exemplo, para o exercício no. 1, crie um programa cliente que realize a
 * entrada de dados ( nome, cargo e salário de um funcionário ) e os envie para
 * um programa servidor, o qual deverá então realizar a operação desejada, ou
 * seja, o cálculo do reajuste salarial. O programa servidor deve, então, enviar
 * o resultado para o cliente. * 1. Faça um programa que leia o nome, o cargo e
 * o salário de um funcionário e escreva seu salário reajustado. Se o cargo do
 * funcionário for operador, ele deverá receber um reajuste de 20%, se for
 * programador, ele deverá receber um reajuste de 18%. O programa deve escrever
 * o nome do funcionário e seu salário reajustado.
 */
public class Cliente01Sockets {

    public static void main(String[] args) throws IOException {
        try ( Socket socket = new Socket("localhost", 7777)) {
            System.out.println("Conectado!");

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream
                    = new DataOutputStream(outputStream);

            InputStream inputStream
                    = socket.getInputStream();

            DataInputStream dataInputStream
                    = new DataInputStream(inputStream);
            ////////////////////////////////////////////////////////////////////////

            while (true) {
                try {

                    String nome = JOptionPane.showInputDialog(
                            null,
                            "Digite o nome",
                            "Nome",
                            JOptionPane.QUESTION_MESSAGE
                    );
                    String cargo = JOptionPane.showInputDialog(
                            null,
                            "Digite o cargo",
                            "Cargo",
                            JOptionPane.QUESTION_MESSAGE
                    );

                    double salario;
                    salario = Double.parseDouble(JOptionPane.showInputDialog(
                            null,
                            "Digite o salario",
                            "Salário",
                            JOptionPane.QUESTION_MESSAGE
                    ));

                    dataOutputStream.writeUTF(cargo);
                    dataOutputStream.writeDouble(salario);
                    dataOutputStream.flush();

                    double valor = dataInputStream.readDouble();
                    JOptionPane.showMessageDialog(null, "Olá " + nome
                            + "\nSeu salário reajustado é R$ " + Double.toString(valor));
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Formato Errado! Tente Novamente\n(Ex: 200)",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }

            dataOutputStream.close();

            System.out.println("Fechando Socket e Terminando o Programa.");
        } catch (Exception e) {
            System.out.println("err: " + e.getMessage());
            e.printStackTrace();;
        }

    }
}
