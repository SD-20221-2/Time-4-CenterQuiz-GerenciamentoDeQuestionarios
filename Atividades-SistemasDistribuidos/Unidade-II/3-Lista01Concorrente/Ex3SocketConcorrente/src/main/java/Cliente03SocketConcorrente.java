/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */


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
 *
 */
public class Cliente03SocketConcorrente {

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
                    double n1 = Double.parseDouble(
                            JOptionPane.showInputDialog(
                                    null,
                                    "Digite a n1",
                                    "Nota 1",
                                    JOptionPane.QUESTION_MESSAGE
                            )
                    );
                    double n2 = Double.parseDouble(
                            JOptionPane.showInputDialog(
                                    null,
                                    "Digite a n2",
                                    "Nota 2",
                                    JOptionPane.QUESTION_MESSAGE
                            )
                    );
                    double n3 = Double.parseDouble(
                            JOptionPane.showInputDialog(
                                    null,
                                    "Digite a n3",
                                    "Nota 3",
                                    JOptionPane.QUESTION_MESSAGE
                            )
                    );

                    dataOutputStream.writeDouble(n1);
                    dataOutputStream.writeDouble(n2);
                    dataOutputStream.writeDouble(n3);
                    dataOutputStream.flush();

                    boolean aprovado = dataInputStream.readBoolean();
                    JOptionPane.showMessageDialog(null, "Olá " + nome
                            + (aprovado
                                    ? "\nvocê está aprovado!"
                                    : "\nvocê não está aprovado!")
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
            e.printStackTrace();;
        }

    }
}
