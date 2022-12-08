/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package ex5.sockets;

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
public class Cliente05Sockets {

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

                    int idade;
                    idade = Integer.parseInt(JOptionPane.showInputDialog(
                            null,
                            "Digite a idade",
                            "Idade",
                            JOptionPane.QUESTION_MESSAGE
                    ));

                    dataOutputStream.writeInt(idade);
                    dataOutputStream.flush();

                    String categoriaNadador = dataInputStream.readUTF();
                    JOptionPane.showMessageDialog(
                            null,
                            "Olá " + nome
                            + "\nVocê se enquadra na categoria \"" + categoriaNadador
                            + "\" de nadadores"
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