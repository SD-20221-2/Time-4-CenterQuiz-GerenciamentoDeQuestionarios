/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package ex6.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author mathe
 *
 */
public class Cliente06Sockets {

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
                    String[] niveis = {"A", "B", "C", "D"};
                    String nivel = (String) JOptionPane.showInputDialog(
                            null,
                            "Digite o nível",
                            "Nível",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            niveis,
                            niveis[0]
                    );
                    double salarioBruto = Double.parseDouble(JOptionPane.showInputDialog(
                            null,
                            "Digite o seu salário bruto",
                            "Salário bruto",
                            JOptionPane.QUESTION_MESSAGE
                    ));

                    int numeroDependentes = Integer.parseInt(JOptionPane.showInputDialog(
                            null,
                            "Digite o número de dependentes",
                            "Número de dependentes",
                            JOptionPane.QUESTION_MESSAGE
                    ));

                    dataOutputStream.writeUTF(nivel);
                    dataOutputStream.writeDouble(salarioBruto);
                    dataOutputStream.writeInt(numeroDependentes);
                    dataOutputStream.flush();

                    double salarioLiquido = dataInputStream.readDouble();
                    DecimalFormat df = new DecimalFormat("#.00");
                    JOptionPane.showMessageDialog(null, "Olá " + nome
                            + "\nVocê pertence ao nível \"" + nivel 
                            + "\" e seu salário líquido é = R$ " + df.format(salarioLiquido)
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
