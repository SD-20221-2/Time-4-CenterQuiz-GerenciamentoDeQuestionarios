/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex3.socket;

import home03.Nota;
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
public class Servidor03Sockets {

    
    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Socket socket;
        try (ServerSocket ss = new ServerSocket(7777)) {
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
            double n1 = dataInputStream.readDouble();
            double n2 = dataInputStream.readDouble();
            double n3 = dataInputStream.readDouble();
            
            // Chamada ao método
            Nota nt = new Nota();
            boolean aprovado = nt.verificarAprovado(n1, n2, n3);
            // Saída de dados
            dataOutputStream.writeBoolean(aprovado);
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
