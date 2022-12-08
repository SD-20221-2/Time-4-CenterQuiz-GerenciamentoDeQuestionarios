/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package ex2.sockets;

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
 */
public class Cliente02Sockets {

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
                    String[] opcoes = {"M", "F"};
                    String sexo = (String) JOptionPane.showInputDialog(
                            null,
                            "Digite o sexo",
                            "Sexo",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            opcoes,
                            opcoes[1]
                    );

                    int idade;
                    idade = Integer.parseInt(JOptionPane.showInputDialog(
                            null,
                            "Digite a idade",
                            "Idade",
                            JOptionPane.QUESTION_MESSAGE
                    ));

                    dataOutputStream.writeUTF(sexo);
                    dataOutputStream.writeInt(idade);
                    dataOutputStream.flush();

                    boolean maioridade = dataInputStream.readBoolean();
                    JOptionPane.showMessageDialog(null, "Olá " + nome
                            + (maioridade
                                    ? "\nvocê atingiu a maioridade!"
                                    : "\nvocê não atingiu a maioridade!")
                    );
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
            e.printStackTrace();
        }

    }
}