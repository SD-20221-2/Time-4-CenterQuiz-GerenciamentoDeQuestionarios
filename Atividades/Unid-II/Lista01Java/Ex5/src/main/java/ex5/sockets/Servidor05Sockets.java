/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex5.sockets;

import home05.CategoriaNadador;
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
public class Servidor05Sockets {

    
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
            int idade = dataInputStream.readInt();
            
            // Chamada ao método
            CategoriaNadador cat = new CategoriaNadador();
            String categoriaNadador = cat.obterCategoria(idade);
            // Saída de dados
            dataOutputStream.writeUTF(categoriaNadador);
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
