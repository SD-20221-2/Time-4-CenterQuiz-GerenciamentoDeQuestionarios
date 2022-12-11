
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author mathe
 */
public class Cliente01SocketConcorrente {

    public static void main(String[] args) throws Exception {
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
