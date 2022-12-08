/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex6.sockets;

import home06.Salario;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author mathe
 */
public class Servidor06Sockets {

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Socket socket;
        try ( ServerSocket ss = new ServerSocket(7777)) {
            System.out.println("Servidor esperando por conexoes...");

            socket = ss.accept();
            System.out.println("Conexao de " + socket + "!");

            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(
                    outputStream
            );

            //Entrada de dados do cliente
            String nivel = dataInputStream.readUTF();
            double salarioBruto = dataInputStream.readDouble();
            int numeroDependentes = dataInputStream.readInt();

            // Chamada ao método
            Salario sal = new Salario();
            double salarioLiquido = sal.liquido(
                    nivel,
                    salarioBruto,
                    numeroDependentes
            );
            // Saída de dados
            dataOutputStream.writeDouble(salarioLiquido);
            dataOutputStream.flush();
            dataOutputStream.close();

            System.out.println("Fechando Sockets.");

            ss.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("err: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
